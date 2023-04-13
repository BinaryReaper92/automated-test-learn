package stepDefinitions;

import io.cucumber.java.en.Then;
import testObjects.DashboardPageTest;
import java.io.IOException;

public class DashboardPageSteps {


    @Then("Page Title should be {string}")
    public void page_title_should_be(String title) throws IOException {

        DashboardPageTest.checkIfPageTitleIs(title);

    }

}
