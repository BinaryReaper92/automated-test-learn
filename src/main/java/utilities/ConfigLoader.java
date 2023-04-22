package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;


public class ConfigLoader {
    private static final Properties props = new Properties();

    static {
        try {
            InputStream is = ConfigLoader.class.getResourceAsStream("/config.properties");
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Could not load config file", e);
        }
    }

    public static String getAppUrl() {
        return props.getProperty("appUrl");
    }

    public static String getUsername() {
        return props.getProperty("username");
    }

    public static String getPassword() {
        return EncrypterDecrypter.decrypt(props.getProperty("password"));
    }
    public static String getAppURI() {
        return props.getProperty("appURI");
    }

    public static String getJiraURL(){
        return props.getProperty("jiraURL");
    }
    public static String getJiraUser(){
        return props.getProperty("jiraUserName");
    }
    public static String getJiraKey(){
        return props.getProperty("jiraKey");
    }
    public static String getJiraCreate(){
        return props.getProperty("createJiraTicket");
    }

}

