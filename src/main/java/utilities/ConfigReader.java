package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try {
            InputStream is = ConfigReader.class.getResourceAsStream("/config.properties");
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Could not load config file", e);
        }
    }

    public static String getBrowser(){
        return properties.getProperty("browser");
    }

    public static String getAppUrl() {
        return properties.getProperty("appUrl");
    }

    public static String getValidUsername() {
        return properties.getProperty("username");
    }

    public static String getValidPassword() {
        return EncrypterDecrypter.decrypt(properties.getProperty("password"));
    }
    public static String getAppURI() {
        return properties.getProperty("appURI");
    }

    public static String getJiraURL(){
        return properties.getProperty("jiraURL");
    }
    public static String getJiraUser(){
        return properties.getProperty("jiraUserName");
    }
    public static String getJiraKey(){
        return properties.getProperty("jiraKey");
    }
    public static boolean getJiraCreate(){
        return Boolean.parseBoolean(properties.getProperty("createJiraTicket"));
    }

    public static String getMailSmtpHost(){
        return properties.getProperty("mail.smtp.host");
    }
    public static String getMailSmtpPort(){
        return properties.getProperty("mail.smtp.port");
    }
    public static String getMailSmtpAuth(){
        return properties.getProperty("mail.smtp.auth");
    }
    public static String getMailSmtpStarttls(){
        return properties.getProperty("mail.smtp.starttls.enable");
    }

    public static String getFromEmail(){
        return properties.getProperty("from.email");
    }
    public static String getFromPassword(){
        return properties.getProperty("from.password");
    }
    public static String getToEmail(){
        return properties.getProperty("to.email");
    }

    public static String getDBUrl(){
        return properties.getProperty("db.url");
    }
    public static String getDBUser(){
        return properties.getProperty("db.username");
    }
    public static String getDBPassword(){
        return properties.getProperty("db.password");
    }
    public static String getWrongUser(){
        return properties.getProperty("wrong.username");
    }
    public static String getWrongPassword(){
        return properties.getProperty("wrong.password");
    }
    public static String getNonExistUser(){
        return properties.getProperty("nonExist.username");
    }
    public static String getNonExistPassword(){
        return properties.getProperty("nonExist.password");
    }
    public static String getTestPicture(){
        return properties.getProperty("picturePath");
    }


}

