package utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.InputStream;


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
    /*
    private static Logger Log = (Logger) LogManager.getLogger(Log4j.class.getName());

    public static void log4jSetup(){
        try {
            InputStream inputStream = new FileInputStream("D:\\Learn\\automated-test-skeleton\\src\\main\\resources\\log4j2.xml");
            ConfigurationSource source = new ConfigurationSource(inputStream);
            Configurator.initialize(null, source);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void startLog (String testClassName) {
        Log.info("Test is Starting...");
    }

    public static void endLog (String testClassName){

        Log.info("Test is Ending...");
    }

    public static void info (String message) {

        Log.info(message);
    }*/
}
