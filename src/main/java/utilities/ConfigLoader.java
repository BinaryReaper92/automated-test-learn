package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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

    public static String getDBUrl(){
        return props.getProperty("db.url");
    }
    public static String getDBUser(){
        return props.getProperty("db.username");
    }
    public static String getDBPassword(){
        return props.getProperty("db.password");
    }
    public static String getWrongUser(){
        return props.getProperty("wrong.username");
    }
    public static String getWrongPassword(){
        return props.getProperty("wrong.password");
    }
    public static String getNonExistUser(){
        return props.getProperty("nonExist.username");
    }
    public static String getNonExistPassword(){
        return props.getProperty("nonExist.password");
    }


}

