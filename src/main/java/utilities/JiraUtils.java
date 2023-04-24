package utilities;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class JiraUtils {

    String jiraURL = ConfigLoader.getJiraURL();
    String jiraUserName = ConfigLoader.getJiraUser();
    String jiraAccessKey = ConfigLoader.getJiraKey();


    public String createJiraIssue(String issueSummary, String issueDescription) throws ClientProtocolException, IOException, ParseException {


        String issueId = null; //to store issue / bug id.

        HttpClient httpClient = HttpClientBuilder.create().build();
        String url = jiraURL+"/rest/api/3/issue";
        HttpPost postRequest = new HttpPost(url);
        postRequest.addHeader("content-type", "application/json");

        String encoding = Base64.getEncoder().encodeToString((jiraUserName+":"+jiraAccessKey).getBytes());
        postRequest.setHeader("Authorization", "Basic " +  encoding);

        StringEntity params = new StringEntity(createPayloadForCreateJiraIssue(issueSummary, issueDescription));
        postRequest.setEntity(params);
        HttpResponse response = httpClient.execute(postRequest);

        //convert httpresponse to string
        String jsonString = EntityUtils.toString(response.getEntity());

        //convert sring to Json
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(jsonString);

        //extract issuekey from Json
        issueId = (String) json.get("key");

        return issueId;

    }
    private static String createPayloadForCreateJiraIssue(String issueSummary, String issueDescription) {
        return "{\n" +
                "\t\"fields\": {\n" +
                "\t\t\"project\": {\n" +
                "\t\t\t\"key\": \"AT\",\n" +
                "\t\t\t\"name\": \"automated-test\"\n" +
                "\t\t},\n" +
                "\t\t\"summary\": \""+issueSummary+"\",\n" +
                "\t\t\"description\": {\n" +
                "\t\t\t\"type\": \"doc\",\n" +
                "\t\t\t\"version\": 1,\n" +
                "\t\t\t\"content\": [{\n" +
                "\t\t\t\t\"type\": \"paragraph\",\n" +
                "\t\t\t\t\"content\": [{\n" +
                "\t\t\t\t\t\"text\": \""+issueDescription+"\",\n" +
                "\t\t\t\t\t\"type\": \"text\"\n" +
                "\t\t\t\t}]\n" +
                "\t\t\t}]\n" +
                "\t\t},\n" +
                "\t\t\"issuetype\": {\n" +
                "\t\t\t\"name\": \"Bug\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";
    }

    public void addAttachmentToJiraIssue(String issueId, String filePath) throws ClientProtocolException, IOException
    {
        String pathname= filePath;
        File fileUpload = new File(pathname);

        HttpClient httpClient = HttpClientBuilder.create().build();
        String url = jiraURL+"/rest/api/3/issue/"+issueId+"/attachments";
        HttpPost postRequest = new HttpPost(url);

        String encoding = Base64.getEncoder().encodeToString((jiraUserName+":"+jiraAccessKey).getBytes());

        postRequest.setHeader("Authorization", "Basic " + encoding);
        postRequest.setHeader("X-Atlassian-Token","nocheck");

        MultipartEntityBuilder entity=MultipartEntityBuilder.create();
        entity.addPart("file", new FileBody(fileUpload));
        postRequest.setEntity( entity.build());
        HttpResponse response = httpClient.execute(postRequest);
        System.out.println(response.getStatusLine());

        if(response.getStatusLine().toString().contains("200 OK")){
            System.out.println("Attachment uploaded");
        } else{
            System.out.println("Attachment not uploaded");
        }
    }

}
