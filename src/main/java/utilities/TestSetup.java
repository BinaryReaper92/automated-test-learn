package utilities;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class TestSetup {

    JiraUtils jiraU = new JiraUtils();

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
    public void tearDown(io.cucumber.java.Scenario scenario) throws IOException, ParseException, InterruptedException {

        if (scenario.isFailed()) {

            String testName = scenario.getName();
            String screenshotName = "screenshot_" + System.currentTimeMillis() + ".png";
            String screenshotPath = TakeScreenshot.takeScreenshot(screenshotName);
            TakeScreenshot.takeScreenshot(screenshotName);
            Log4j.info("Test failed with the following test: " + testName + " \n Screenshot taken to: "+ screenshotPath);
            String bugCreation = ConfigLoader.getJiraCreate();
            if (bugCreation.equalsIgnoreCase("yes")){
                String logData = Log4j.readLogFile();
                String formattedLogData = logData.replaceAll("\\r?\\n", " ");
                String issueS = "Automation Test Failed - "+scenario.getName();
                String issueD = formattedLogData;
                System.out.println(issueD);
                String issueId = null;

                try {
                    issueId = jiraU.createJiraIssue(issueS, issueD);
                } catch (Exception e1){
                    e1.printStackTrace();
                }
                try {
                    jiraU.addAttachmentToJiraIssue(issueId, screenshotPath);
                }catch (Exception e){
                    e.printStackTrace();
                }
                String htmlReportPath = "D:/Learn/automated-test-skeleton/Reports/emailable-report.html";
                try {
                    jiraU.addAttachmentToJiraIssue(issueId, htmlReportPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        File reportFile = new File("D:/Learn/automated-test-skeleton/Reports/emailable-report.html");
        EmailUtil.sendReportEmail(scenario, reportFile);

        Log4j.endLog("Test is ending.");
        closeWebDriver();
    }
}
