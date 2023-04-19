package utilities;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class TestSetup {

    @Before
    public void setup() {

        Log4j.log4jSetup();
        Log4j.startLog("Test is starting");

        Configuration.screenshots = true;
        System.setProperty("selenide.reportsFolder", "D:/Learn/automated-test-skeleton/Selenide/Screenshots");
        System.setProperty("selenide.browser", "chrome");
        Configuration.browser = "chrome";
        //      System.setProperty("selenide.browser", "firefox");
        //      Configuration.browser = "firefox";
        //       Configuration.browserSize = "maximize";
        Configuration.headless = false;
        Configuration.timeout = 15000;
        System.setProperty("selenide.timeout", "15000");
        //Configuration.holdBrowserOpen=true;
    }

    @After
    public void tearDown(io.cucumber.java.Scenario scenario) {

        if (scenario.isFailed()) {
            String testName = scenario.getName();
            String screenshotName = "screenshot_" + System.currentTimeMillis() + ".png";
            String screenshotPath = TakeScreenshot.takeScreenshot(screenshotName);
            TakeScreenshot.takeScreenshot(screenshotName);
            Log4j.info("Test failed with the following test: " + testName + " \n Screenshot taken to: "+ screenshotPath);
        }

        Log4j.endLog("Test is ending.");
        closeWebDriver();
    }
}
