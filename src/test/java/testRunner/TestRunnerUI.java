package testRunner;

import io.cucumber.testng.CucumberOptions;

public class TestRunnerUI {

    @CucumberOptions
            (
                    features = "src\\test\\java\\resources\\features\\",
                    glue = {"stepDefinitions","utilities"},
                    tags = "@SmokeTest",
                    dryRun = false,
                    plugin = {"pretty","html:Reports/Report.html","json:Reports/Report.json","junit:Reports/Report.xml"}

            )
    public class TestRun {
    }
}
