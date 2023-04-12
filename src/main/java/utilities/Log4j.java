package utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4j {

    private static Logger log = Logger.getLogger(Log4j.class.getName());

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
}
