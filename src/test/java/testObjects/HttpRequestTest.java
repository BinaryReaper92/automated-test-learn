package testObjects;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.CustomResponseModel;
import utilities.ConfigReader;
import utilities.HttpRequests;
import utilities.JSONUtils;

import java.io.IOException;
import java.util.Map;

public class HttpRequestTest {

    private String requestBaseURI;
    private CustomResponseModel response;

    public void setRequestBaseURI() {
        requestBaseURI = ConfigReader.getAppURI();
    }

    public CustomResponseModel postRequest(String requestUrl, String bodyJson, String isLoggedIn) throws IOException {
        response = HttpRequests.PostAPI(requestBaseURI, isLoggedIn, bodyJson, requestUrl);
        System.out.println(response);
        return response;
    }

    public CustomResponseModel getRequest(String requestUrl) {
        response = HttpRequests.GetAPI(requestBaseURI, requestUrl);
        return response;
    }

    public CustomResponseModel putRequest(String requestUrl, String requestBody) {
        response = HttpRequests.PutAPI(requestBaseURI, requestBody, requestUrl);
        return response;
    }

    public CustomResponseModel deleteRequest(String requestUrl) {
        response = HttpRequests.DeleteAPI(requestBaseURI, null, requestUrl);
        return response;
    }

    public int getResponseStatusCode() {
        return response.getStatusCode();
    }

    public void setResponse(CustomResponseModel response) {
        this.response = response;
    }

    public CustomResponseModel getResponse() {
        return response;
    }

    public String getResponseBody() {
        return getResponse().getResponseString();
    }

    public void printResponseBodyIfStatusOk() {
        if (getResponse().getStatusCode() == 200) {
            String responseBody = getResponseBody();
            System.out.println(responseBody);
            JSONUtils.getAPIWriter(responseBody);
        } else {
            System.out.println("Failed to get data, status code: " + getResponse().getStatusCode());
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
