package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

public class TestRunnerUI {


    @CucumberOptions
            (
                    features = "src\\test\\java\\resources\\features\\",
                    glue = {"stepDefinitions","utilities"},
                    tags = "@RegressionTest",
                    dryRun = false,
                    plugin = {"pretty", "html:Reports/Report.html","json:Reports/Report.json","junit:Reports/Report.xml","utilities.CucumberEventListener"}

            )
    @Test
    public class TestRunner extends AbstractTestNGCucumberTests {

    }
}
