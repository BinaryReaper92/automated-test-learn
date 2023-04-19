package utilities;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.util.HashMap;

public class JSONReader {
    JsonParser parser = new JsonParser();
    HashMap<?,?> mappedData;



    public HashMap<?,?> reader (String FilePath){
        Gson gson = new Gson();

        try {
            JsonElement JSONData = parser.parse(new FileReader(System.getProperty("user.dir")+ FilePath));
            mappedData = gson.fromJson(JSONData,HashMap.class);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return mappedData;
    }

    public static String getRequestBody(String url) {

        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(url))) {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
            {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }

    public static void bearerTokenWriter(String input){
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("src/main/resources/bearerToken.txt"));
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


    /*
    public UserModel getUser(String role) {
        String path = "//Properties//users.json";
        String username = ((LinkedTreeMap<?, ?>) reader(path).get(role)).get("email").toString();
        String password = ((LinkedTreeMap<?, ?>) reader(path).get(role)).get("password").toString();
        UserModel model = new UserModel(username,password);


        return model;
    }

    public PageAddress getAddress(String url) {
        String path = "//Properties//environments.json";
        String address = ((LinkedTreeMap<?, ?>) reader(path).get(url)).get("address").toString();
        PageAddress pageAddress = new PageAddress(address);


        return pageAddress;
    }

     */
}
