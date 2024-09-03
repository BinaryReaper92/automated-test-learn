package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import testObjects.BackgroundTest;

public class BackgroundSteps {
    @Given("the admin is logged in")
    public void theAdminIsLoggedIn() throws Exception {
        BackgroundTest.login();
    }

    @And("the admin is on lessons page with title {string}")
    public void theAdminIsOnLessonsPageWithTitle(String title) throws Exception {
        BackgroundTest.clickOnLessonsMenuItem();
        BackgroundTest.checkLessonsPageTitle(title);
    }


}
