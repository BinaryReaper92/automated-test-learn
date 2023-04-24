package stepDefinitions;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import testObjects.LoginPageTest;
import utilities.ConfigLoader;
import utilities.Log4j;


public class LoginPageSteps {


    @Given("Admin opens URL {string}")
    public void admin_opens_url(String url) {
        String website = ConfigLoader.getAppUrl() + url;
        Log4j.info("Opening the website:" + website);
        Selenide.open(website);
    }


    @And("Admin enters Email as {string}")
    public void admin_enters_email_as(String admin) throws Exception {
        Log4j.info("Enter email");
        LoginPageTest.setUserName(ConfigLoader.getUsername());

    }


    @And("Admin enters Password as {string}")
    public void admin_enters_password_as(String admin) throws Exception {
        Log4j.info("Enter password");
        LoginPageTest.setPassword(ConfigLoader.getPassword());

    }


    @And("Admin click on Login")
    public void admin_click_on_login() throws Exception {
        Log4j.info("Click on login button");
        LoginPageTest.clickLogin();

    }
}
