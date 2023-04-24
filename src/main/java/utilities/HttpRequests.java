package utilities;

import models.CustomResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;


public class HttpRequests {

    public static CustomResponse PostAPI(String url, String isLogin, String requestBody, String requestUrl) {
        try {
            String bearerToken = isLogin.equals("logged out") ? null : JSONUtils.getRequestBody("src/main/resources/bearerToken.txt");
            CustomResponse response = sendRequest("POST", url + requestUrl, JSONUtils.getRequestBody(requestBody), bearerToken);

            if (isLogin.equals("logged out")) {
                HashMap<String, Object> responseMap = JSONUtils.parseJsonFromString(response.getResponseString());
                String accessToken = (String) responseMap.get("access_token");
                JSONUtils.bearerTokenWriter(accessToken);
            }

            response.setResponseMap(JSONUtils.parseJsonFromString(response.getResponseString()));
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static CustomResponse sendRequest(String method, String url, String requestBody, String bearerToken) throws IOException {
        URL endpoint = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) endpoint.openConnection();

        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/json");

        if (bearerToken != null) {
            connection.setRequestProperty("Authorization", "Bearer " + bearerToken);
        }

        connection.setDoOutput(true);
        if (requestBody != null) {
            try (OutputStream os = connection.getOutputStream()) {
                os.write(requestBody.getBytes(StandardCharsets.UTF_8));
            }
        }

        int responseCode = connection.getResponseCode();
        String responseString;

        InputStream inputStream;
        if (responseCode >= 200 && responseCode <= 299) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                responseBuilder.append(line);
            }
            responseString = responseBuilder.toString();
        }

        return new CustomResponse(responseCode, responseString);
    }

    public static CustomResponse GetAPI(String url, String requestUrl) {
        String bearerToken = JSONUtils.getRequestBody("src/main/resources/bearerToken.txt");
        try {
            CustomResponse response = sendRequest("GET", url + requestUrl, null, bearerToken);
            response.setResponseList(JSONUtils.parseJsonArrayFromString(response.getResponseString()));
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static CustomResponse PutAPI(String url, String requestBody, String requestUrl) {
        String bearerToken = JSONUtils.getRequestBody("src/main/resources/bearerToken.txt");
        try {
            CustomResponse response = sendRequest("PUT", url + requestUrl, JSONUtils.getRequestBody(requestBody), bearerToken);
            response.setResponseMap(JSONUtils.parseJsonFromString(response.getResponseString()));
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static CustomResponse DeleteAPI(String url, String requestBody, String requestUrl) {
        String bearerToken = JSONUtils.getRequestBody("src/main/resources/bearerToken.txt");
        try {
            CustomResponse response = sendRequest("DELETE", url + requestUrl, null, bearerToken);
            response.setResponseMap(JSONUtils.parseJsonFromString(response.getResponseString()));
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
