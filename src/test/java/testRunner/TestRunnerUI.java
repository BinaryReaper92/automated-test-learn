package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.CucumberEventListener;

public class TestRunnerUI {


    @CucumberOptions
            (
                    features = "src\\test\\java\\resources\\features\\",
                    glue = {"stepDefinitions","utilities"},
                    tags = "@SmokeTest",
                    dryRun = false,
                    plugin = {"pretty", "html:Reports/Report.html","json:Reports/Report.json","junit:Reports/Report.xml","utilities.CucumberEventListener"}

            )
    @Test
    public class TestRunner extends AbstractTestNGCucumberTests {

    }
}
