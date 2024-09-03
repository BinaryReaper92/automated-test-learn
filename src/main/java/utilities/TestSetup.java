package utilities;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class TestSetup {

    @Before
    public void setup() {

        Log4j.log4jSetup();
        Log4j.startLog("Test is starting");

        String browser = ConfigReader.getBrowser();
        Configuration.browser = browser;
        //Configuration.browserSize = "maximize";
        Configuration.headless = false;
        Configuration.timeout = 15000;
        System.setProperty("selenide.timeout", "15000");
        Configuration.holdBrowserOpen = false;

        EmailUtil.init();
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {

        if (scenario.isFailed()) {
            String testName = scenario.getName();
            String screenshotName = "screenshot_" + System.currentTimeMillis() + ".png";
            String screenshotPath = TakeScreenshot.takeScreenshot(screenshotName);
            Log4j.info("Test failed with the following test: " + testName + " \n Screenshot taken to: " + screenshotPath);

            if (ConfigReader.getJiraCreate()) {
                JiraUtils.createBugTicket(scenario,screenshotPath);
            }

            File reportFile = new File("./test-output/emailable-report.html");
            EmailUtil.sendReportEmail(scenario, reportFile);

            Log4j.endLog("Test is ending.");
            closeWebDriver();
        }

    }

}
