package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class TakeScreenshot {
    public void captureScreen(WebDriver driver, String testName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        Date d = new Date();
        String FileName = testName + "(" + d.toString().replace(":", "_").replace(" ", "_")+")" + ".png";
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir") + "/Screenshots/" + FileName);
        FileUtils.copyFile(source, target);
        System.out.println("Screenshot taken");
    }
}
