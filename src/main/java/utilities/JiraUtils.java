package utilities;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;

public class JiraUtils {

    String jiraURL = ConfigReader.getJiraURL();
    String jiraUserName = ConfigReader.getJiraUser();
    String jiraAccessKey = ConfigReader.getJiraKey();

    String issueSummary;
    String issueDescription;
    String issueId = null;

    public JiraUtils(String issueSummary, String issueDescription) throws IOException {
        this.issueSummary = issueSummary;
        this.issueDescription = issueDescription.replaceAll("\\r?\\n", "\n");

        this.createJiraIssue();
    }

    public String createJiraIssue() throws IOException {
        String urlString = jiraURL + "/rest/api/3/issue";
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");

        String encoding = Base64.getEncoder().encodeToString((jiraUserName + ":" + jiraAccessKey).getBytes());
        connection.setRequestProperty("Authorization", "Basic " + encoding);

        connection.setDoOutput(true);
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = createPayloadForCreateJiraIssue().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        StringBuilder response;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }

        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(response.toString(), JsonObject.class);

        issueId = jsonResponse.get("key").getAsString();

        return issueId;
    }

    private String createPayloadForCreateJiraIssue() {
        JsonObject payload = new JsonObject();
        JsonObject fields = new JsonObject();
        JsonObject project = new JsonObject();
        JsonObject description = new JsonObject();
        JsonObject issueType = new JsonObject();

        project.addProperty("key", "AT");
        project.addProperty("name", "automated-test");

        description.addProperty("type", "doc");
        description.addProperty("version", 1);
        description.add("content", createContentArray(issueDescription));

        issueType.addProperty("name", "Bug");

        fields.add("project", project);
        fields.addProperty("summary", issueSummary);
        fields.add("description", description);
        fields.add("issuetype", issueType);

        payload.add("fields", fields);

        Gson gson = new Gson();
        return gson.toJson(payload);
    }

    private JsonArray createContentArray(String issueDescription) {
        JsonObject content = new JsonObject();
        JsonObject text = new JsonObject();
        JsonArray contentArray = new JsonArray();
        JsonArray innerContentArray = new JsonArray();

        text.addProperty("text", issueDescription);
        text.addProperty("type", "text");

        innerContentArray.add(text);

        content.addProperty("type", "paragraph");
        content.add("content", innerContentArray);

        contentArray.add(content);

        return contentArray;
    }

    public void addAttachmentToJiraIssue(String filePath) throws IOException {
        File fileUpload = new File(filePath);
        String boundary = "----JiraAttachmentBoundary" + System.currentTimeMillis();

        String url = jiraURL + "/rest/api/3/issue/" + issueId + "/attachments";
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        String encoding = Base64.getEncoder().encodeToString((jiraUserName + ":" + jiraAccessKey).getBytes());
        connection.setRequestProperty("Authorization", "Basic " + encoding);
        connection.setRequestProperty("X-Atlassian-Token", "nocheck");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        try (OutputStream os = connection.getOutputStream(); BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os))) {
            writer.write("--" + boundary);
            writer.newLine();

            writer.write("Content-Disposition: form-data; name=\"file\"; filename=\"" + fileUpload.getName() + "\"");
            writer.newLine();
            writer.write("Content-Type: " + Files.probeContentType(fileUpload.toPath()));
            writer.newLine();
            writer.newLine();

            writer.flush();
            Files.copy(fileUpload.toPath(), os);
            os.flush();
            writer.newLine();

            writer.write("--" + boundary + "--");
            writer.newLine();
        }

        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("Attachment uploaded");
        } else {
            System.out.println("Attachment not uploaded");
        }
    }
}
