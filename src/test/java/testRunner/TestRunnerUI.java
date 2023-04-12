package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



public class TestRunnerUI {


    @CucumberOptions
            (
                    features = "src\\test\\java\\resources\\features\\",
                    glue = {"stepDefinitions","utilities"},
                    tags = "@SmokeTest",
                    dryRun = false,
                    plugin = {"pretty","html:target/cucumber-reports","json:Reports/Report.json","junit:Reports/Report.xml"}

            )

    public class TestRunner extends AbstractTestNGCucumberTests {
    }
}
