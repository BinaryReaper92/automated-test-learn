package utilities;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class JiraUtils {

    String jiraURL = ConfigLoader.getJiraURL();
    String jiraUserName = ConfigLoader.getJiraUser();
    String jiraAccessKey = ConfigLoader.getJiraKey();

    public String createJiraIssue(String issueSummary, String issueDescription) throws IOException {
        String issueId = null;

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
            byte[] input = createPayloadForCreateJiraIssue(issueSummary, issueDescription).getBytes(StandardCharsets.UTF_8);
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

    private static String createPayloadForCreateJiraIssue(String issueSummary, String issueDescription) {
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

    private static JsonArray createContentArray(String issueDescription) {
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

    public void addAttachmentToJiraIssue(String issueId, String filePath) throws ClientProtocolException, IOException {
        String pathname = filePath;
        File fileUpload = new File(pathname);

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String url = jiraURL + "/rest/api/3/issue/" + issueId + "/attachments";
        HttpPost postRequest = new HttpPost(url);

        String encoding = Base64.getEncoder().encodeToString((jiraUserName + ":" + jiraAccessKey).getBytes());

        postRequest.setHeader("Authorization", "Basic " + encoding);
        postRequest.setHeader("X-Atlassian-Token", "nocheck");

        MultipartEntityBuilder entity = MultipartEntityBuilder.create();
        entity.addPart("file", new FileBody(fileUpload));
        postRequest.setEntity(entity.build());
        HttpResponse response = httpClient.execute(postRequest);
        System.out.println(response.getStatusLine());

        if (response.getStatusLine().toString().contains("200 OK")) {
            System.out.println("Attachment uploaded");
        } else {
            System.out.println("Attachment not uploaded");
        }
    }
}
