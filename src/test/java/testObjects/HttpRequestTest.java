package testObjects;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import utilities.ConfigLoader;
import utilities.HttpRequests;
import utilities.JSONReader;

import java.util.Map;

public class HttpRequestTest {

    private String requestBaseURI;
    private Response response;

    public void setRequestBaseURI() {
        requestBaseURI = ConfigLoader.getAppURI();
    }

    public Response postRequest(String requestUrl, String bodyJson, String isLoggedIn) {
        return HttpRequests.PostAPI(requestBaseURI, isLoggedIn, JSONReader.getRequestBody(bodyJson), requestUrl);
    }

    public Response getRequest(String requestUrl, String isLoggedIn) {
        return HttpRequests.GetAPI(requestBaseURI, requestUrl);
    }

    public Response putRequest(String requestUrl, String bodyJson) {
        return HttpRequests.PutAPI(requestBaseURI, JSONReader.getRequestBody(bodyJson), requestUrl);
    }

    public int getResponseStatusCode() {
        return response.getStatusCode();
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
    public String getResponseBody() {
        return getResponse().getBody().asString();
    }

    public void printResponseBodyIfStatusOk() {
        if (getResponse().statusCode() == 200) {
            String responseBody = getResponseBody();
            System.out.println(responseBody);
            JSONReader.getAPIWriter(responseBody);
        } else {
            System.out.println("Failed to get data, status code: " + getResponse().statusCode());
        }
    }

    public int getResponseId() {
        String responseBody = getResponseBody();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, Integer> responseMap = objectMapper.readValue(responseBody, Map.class);
            return responseMap.get("id");
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
