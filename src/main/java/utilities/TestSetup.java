package utilities;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

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
    public void tearDown(io.cucumber.java.Scenario scenario) throws IOException, ParseException, InterruptedException, ParserConfigurationException, SAXException {

        if (scenario.isFailed()) {

            String testName = scenario.getName();
            String screenshotName = "screenshot_" + System.currentTimeMillis() + ".png";
            String screenshotPath = TakeScreenshot.takeScreenshot(screenshotName);
            TakeScreenshot.takeScreenshot(screenshotName);
            Log4j.info("Test failed with the following test: " + testName + " \n Screenshot taken to: "+ screenshotPath);

            String bugCreation = ConfigLoader.getJiraCreate();
            if (bugCreation.equalsIgnoreCase("yes")){
                String issueS = "Automation Test Failed - "+scenario.getName();
                String issueD = CucumberEventListener.EventMessages;//Log4j.readLogFile();
                System.out.println(issueD);

                try {
                    JiraUtils jiraU = new JiraUtils(issueS, issueD);

                    try {
                        jiraU.addAttachmentToJiraIssue(screenshotPath);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    try {
                        String htmlReportPath = "./Reports/emailable-report.html";
                        jiraU.addAttachmentToJiraIssue(htmlReportPath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {

                        String logPath = "./" + Log4j.logFileName;
                        jiraU.addAttachmentToJiraIssue(logPath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e1){
                    e1.printStackTrace();
                }

            }
        }

        File reportFile = new File("./Reports/emailable-report.html");
        EmailUtil.sendReportEmail(scenario, reportFile);

        Log4j.endLog("Test is ending.");
        closeWebDriver();
    }

}
