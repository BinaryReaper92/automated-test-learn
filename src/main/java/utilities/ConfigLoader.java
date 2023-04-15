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
        return props.getProperty("password");
    }
    public static String getAppURI() {
        return props.getProperty("appURI");
    }

}

