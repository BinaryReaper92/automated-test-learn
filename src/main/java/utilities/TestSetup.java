package utilities;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.File;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class TestSetup {

    @Before
    public void setup() {

        Log4j.log4jSetup();
        Log4j.startLog("Test is starting");

        Configuration.screenshots = true;
        System.setProperty("selenide.reportsFolder", "./Selenide/Screenshots");
        System.setProperty("selenide.browser", "chrome");
        Configuration.browser = "chrome";
        //      System.setProperty("selenide.browser", "firefox");
        //      Configuration.browser = "firefox";
               Configuration.browserSize = "maximize";
        Configuration.headless = false;
        Configuration.timeout = 15000;
        System.setProperty("selenide.timeout", "15000");
        Configuration.holdBrowserOpen=false;
    }

    @After
    public void tearDown(Scenario scenario)  {

        if (scenario.isFailed()) {

            String testName = scenario.getName();
            String screenshotName = "screenshot_" + System.currentTimeMillis() + ".png";
            String screenshotPath = TakeScreenshot.takeScreenshot(screenshotName);
            TakeScreenshot.takeScreenshot(screenshotName);
            Log4j.info("Test failed with the following test: " + testName + " \n Screenshot taken to: "+ screenshotPath);

            String bugCreation = ConfigReader.getJiraCreate();
            if (bugCreation.equalsIgnoreCase("yes")){
                String issueSummary = "Automation Test Failed - "+scenario.getName();
                String issueDescription = CucumberEventListener.EventMessages;
                System.out.println(issueDescription);

                try {
                    JiraUtils jiraUtils = new JiraUtils(issueSummary, issueDescription);

                    try {
                        jiraUtils.addAttachmentToJiraIssue(screenshotPath);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    try {
                        String htmlReportPath = "./test-output/emailable-report.html";
                        jiraUtils.addAttachmentToJiraIssue(htmlReportPath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {

                        String logPath = "./" + Log4j.logFileName;
                        jiraUtils.addAttachmentToJiraIssue(logPath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e1){
                    e1.printStackTrace();
                }

            }
        }

        File reportFile = new File("./test-output/emailable-report.html");
        EmailUtil.sendReportEmail(scenario, reportFile);

        Log4j.endLog("Test is ending.");
        closeWebDriver();
    }

}
