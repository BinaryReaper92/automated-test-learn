package stepDefinitions;

import com.codeborne.selenide.Condition;
import io.cucumber.java.en.Then;
import pageObjects.DashboardPage;
import testObjects.DashboardPageTest;
import utilities.Helper;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.page;

public class DashboardPageSteps {

    @Then("Page Title should be {string}")
    public void page_title_should_be(String title) throws IOException {

        DashboardPageTest.checkIfPageTitleIs(title);

    }

}
