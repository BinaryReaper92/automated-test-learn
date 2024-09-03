package utilities;

import models.CustomResponseModel;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;


public class HttpRequests {

    public static CustomResponseModel PostAPI(String url, String isLogin, String requestBody, String requestUrl) {
        try {
            String bearerToken = isLogin.equals("logged out") ? null : JSONUtils.getRequestBody("./resources/bearerToken.txt");
            CustomResponseModel response = sendRequest("POST", url + requestUrl, JSONUtils.getRequestBody(requestBody), bearerToken);

            if (isLogin.equals("logged out")) {
                HashMap<String, Object> responseMap = JSONUtils.parseJsonFromString(response.getResponseString());
                String accessToken = (String) responseMap.get("access_token");
                JSONUtils.bearerTokenWriter(accessToken);
            }

            response.setResponseMap(JSONUtils.parseJsonFromString(response.getResponseString()));
            return response;
        } catch (IOException e) {
            Log4j.error(e.getMessage());
            return null;
        }
    }

    public static CustomResponseModel sendRequest(String method, String url, String requestBody, String bearerToken) throws IOException {
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

        return new CustomResponseModel(responseCode, responseString);
    }

    public static CustomResponseModel GetAPI(String url, String requestUrl) {
        String bearerToken = JSONUtils.getRequestBody("./resources/bearerToken.txt");
        try {
            CustomResponseModel response = sendRequest("GET", url + requestUrl, null, bearerToken);
            response.setResponseList(JSONUtils.parseJsonArrayFromString(response.getResponseString()));
            return response;
        } catch (IOException e) {
            Log4j.error(e.getMessage());
            return null;
        }
    }

    public static CustomResponseModel PutAPI(String url, String requestBody, String requestUrl) {
        String bearerToken = JSONUtils.getRequestBody("./resources/bearerToken.txt");
        try {
            CustomResponseModel response = sendRequest("PUT", url + requestUrl, JSONUtils.getRequestBody(requestBody), bearerToken);
            response.setResponseMap(JSONUtils.parseJsonFromString(response.getResponseString()));
            return response;
        } catch (IOException e) {
            Log4j.error(e.getMessage());
            return null;
        }
    }

    public static CustomResponseModel DeleteAPI(String url, String requestBody, String requestUrl) {
        String bearerToken = JSONUtils.getRequestBody("./resources/bearerToken.txt");
        try {
            CustomResponseModel response = sendRequest("DELETE", url + requestUrl, null, bearerToken);
            response.setResponseMap(JSONUtils.parseJsonFromString(response.getResponseString()));
            return response;
        } catch (IOException e) {
            Log4j.error(e.getMessage());
            return null;
        }
    }
    public static CustomResponseModel uploadFile(String method, String url, String filePath, String authHeader, String customHeaderKey, String customHeaderValue) throws IOException {
        URL endpoint = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) endpoint.openConnection();

        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "multipart/form-data");
        connection.setRequestProperty("Authorization", authHeader);
        connection.setRequestProperty(customHeaderKey, customHeaderValue);

        File file = new File(filePath);
        String boundary = "===" + System.currentTimeMillis() + "===";
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        connection.setDoOutput(true);
        connection.setDoInput(true);

        try (OutputStream outputStream = connection.getOutputStream();
             FileInputStream fileInputStream = new FileInputStream(file)) {

            outputStream.write(("--" + boundary + "\r\n").getBytes());
            outputStream.write(("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"\r\n").getBytes());
            outputStream.write(("Content-Type: " + URLConnection.guessContentTypeFromName(file.getName()) + "\r\n").getBytes());
            outputStream.write(("Content-Transfer-Encoding: binary\r\n\r\n").getBytes());

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
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

        return new CustomResponseModel(responseCode, responseString);
    }

}
