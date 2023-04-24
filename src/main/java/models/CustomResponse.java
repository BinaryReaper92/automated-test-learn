package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomResponse {
    private int statusCode;
    private String responseString;
    private HashMap<String, Object> responseMap;
    private List<Map<String, Object>> responseList;

    public CustomResponse(int statusCode, String responseString) {
        this.statusCode = statusCode;
        this.responseString = responseString;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponseString() {
        return responseString;
    }

    public HashMap<String, Object> getResponseMap() {
        return responseMap;
    }

    public void setResponseMap(HashMap<String, Object> responseMap) {
        this.responseMap = responseMap;
    }
    @Override
    public String toString() {
        return "CustomResponse{" +
                "statusCode=" + statusCode +
                ", responseString='" + responseString + '\'' +
                ", responseMap=" + responseMap +
                '}';
    }

    public List<Map<String, Object>> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<Map<String, Object>> responseList) {
        this.responseList = responseList;
    }

    public int getResponseCode() {
        return statusCode;
    }
    public void setStatusCode(int responseCode) {
        this.statusCode = responseCode;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }




}
