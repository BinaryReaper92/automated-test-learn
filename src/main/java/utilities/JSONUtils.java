package utilities;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class JSONUtils {

    public static HashMap<String, Object> parseJson(String jsonString) {
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, Object>>() {}.getType();
        return gson.fromJson(jsonString, type);
    }

    public static void bearerTokenWriter(String bearerToken) {
        try (FileWriter fileWriter = new FileWriter("src/main/resources/bearerToken.txt")) {
            fileWriter.write(bearerToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static HashMap<String, Object> parseJsonFromString(String jsonString) {
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, Object>>() {}.getType();
        return gson.fromJson(jsonString, type);
    }
    public static void getAPIWriter(String input){
        BufferedWriter writer = null;
        try {

            writer = new BufferedWriter(new FileWriter("src/main/resources/requestsBodyJsons/getCompanies.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.write(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static List<Map<String, Object>> parseJsonArrayFromString(String jsonString) {
        Gson gson = new Gson();
        Type type;

        if (jsonString.trim().startsWith("[")) {
            type = new TypeToken<List<Map<String, Object>>>() {}.getType();
        } else {
            type = new TypeToken<Map<String, Object>>() {}.getType();
        }

        Object data = gson.fromJson(jsonString, type);

        if (data instanceof List) {
            return (List<Map<String, Object>>) data;
        } else {
            List<Map<String, Object>> list = new ArrayList<>();
            list.add((Map<String, Object>) data);
            return list;
        }
    }
    public static String getRequestBody(String filePath) {
        StringBuilder jsonString = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
        }
        return jsonString.toString();
    }

}
