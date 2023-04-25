package utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log4j {

    private static Logger log = Logger.getLogger(Log4j.class.getName());
    static String logFileName = "./Log4j/log4j-application.log";

    public static void log4jSetup(){
        String log4jConfPath = System.getProperty("user.dir")+"//src//main//resources//log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
    }

    public static void startLog (String testClassName) {
        log.info("Test is Starting...");
    }

    public static void endLog (String testClassName){

        log.info("Test is Ending...");
    }

    public static void info (String message) {

        log.info(message);
    }
    public static void error (String message) {

        log.error(message);
    }

    static {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        System.setProperty("testRunTimestamp", timestamp);
        Log4j.logFileName = "./Log4j/log4j-application_" + System.getProperty("testRunTimestamp") + ".log";
    }

    public static String readLogFile() {
        try {
            return new String(Files.readAllBytes(Paths.get(Log4j.logFileName)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
