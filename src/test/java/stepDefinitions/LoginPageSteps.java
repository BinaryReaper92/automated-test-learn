package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import testObjects.LoginPageTest;
import utilities.ConfigReader;
import utilities.Helper;
import utilities.Log4j;


public class LoginPageSteps {

    @Given("Admin opens URL {string}")
    public void adminOpensURL(String url) {
        LoginPageTest.openWebsite(url);
    }

    @And("Admin enters existing valid Email")
    public void adminEntersExistingValidEmail() throws Exception {
        Log4j.info("Enter email");
        LoginPageTest.setUserName(ConfigReader.getUsername());

    }
    @And("Admin enters existing valid Password")
    public void adminEntersExistingValidPassword() throws Exception {
        Log4j.info("Enter password");
        LoginPageTest.setPassword(ConfigReader.getPassword());
    }
    @And("Admin click on Login")
    public void adminClickOnLogin() throws Exception {
        Log4j.info("Click on login button");
        LoginPageTest.clickLogin();
    }

    @And("Admin leaves Email as blank")
    public void adminLeavesEmailAsBlank() throws Exception {
        Log4j.info("Click in email field and leave blank");
        LoginPageTest.clickEmailField();
        
    }

    @And("Admin leaves Password as blank")
    public void adminLeavesPasswordAsBlank() throws Exception {
        Log4j.info("Click in password field and leave blank");
        LoginPageTest.clickPasswordField();
        
    }

    @Then("Email and Password error message should display as {string}")
    public void emailAndPasswordErrorMessageShouldDisplayAs(String expectedEmailAndPasswordError) {
        Log4j.info("Error messages are visible for Email and Password fields");
        Helper.compareText(LoginPageTest.emptyEmail(), expectedEmailAndPasswordError);
        Helper.compareText(LoginPageTest.emptyPassword(), expectedEmailAndPasswordError);

    }

    @Then("Email error message should display as {string}")
    public void emailErrorMessageShouldDisplayAs(String expectedEmptyEmailError) {
        Log4j.info("Error message is visible for Email field");
        Helper.compareText(LoginPageTest.emptyEmail(), expectedEmptyEmailError);
        
    }

    @Then("Password error message should display as {string}")
    public void passwordErrorMessageShouldDisplayAs(String expectedEmptyPasswordError) {
        Log4j.info("Error message is visible for Password field");
        Helper.compareText(LoginPageTest.emptyPassword(), expectedEmptyPasswordError);
        
    }
    @Then("Missing Email Error message should display as {string}")
    public void missingEmailErrorMessageShouldDisplayAs(String wrongEmailFormat) {
        Log4j.info("Error message is visible for Email field as wrong format");
        Helper.compareText(LoginPageTest.wrongEmailFormat(), wrongEmailFormat);
    }


    @And("Admin enters wrong Email")
    public void adminEntersWrongEmailAs() throws Exception {
        Log4j.info("Enter wrong email");
        LoginPageTest.setUserName(ConfigReader.getWrongUser());
        
    }

    @And("Admin enters wrong Password")
    public void adminEntersWrongPasswordAs() throws Exception {
        Log4j.info("Enter wrong password");
        LoginPageTest.setPassword(ConfigReader.getWrongPassword());
        
    }
    @Then("Error message should display as {string}")
    public void errorMessageShouldDisplayAs(String generalLoginErrorMessage) {
        Helper.compareText(LoginPageTest.generalInvalidError(), generalLoginErrorMessage);
    }

    @And("Admin enters non existing Email")
    public void adminEntersNonExistingEmailAs() throws Exception {
        Log4j.info("Enter non existing email");
        LoginPageTest.setUserName(ConfigReader.getNonExistUser());
        
    }

    @And("Admin enters non existing Password")
    public void adminEntersNonExistingPasswordAs() throws Exception {
        Log4j.info("Enter non existing password");
        LoginPageTest.setPassword(ConfigReader.getNonExistPassword());
        
    }

    @And("Admin Hoover on Login button")
    public void adminHooverOnLoginButton() {
        Log4j.info("Hover on login button");
        LoginPageTest.hoversOnLoginButton();

        
    }

    @Then("Login button's color should change to yellow")
    public void loginButtonSColorShouldChangeToYellow() {
        Log4j.info("Login button change color");
        LoginPageTest.loginButtonsColorShouldChangeToYellow();
        
    }

    @And("Admin click on Eye icon")
    public void adminClickOnEyeIcon() throws Exception {
        Log4j.info("Click on Eye button");
        LoginPageTest.clickEyeButton();
        
    }

    @Then("Password should be visible")
    public void passwordShouldBeVisible() {
        Log4j.info("Password is visible");
        LoginPageTest.getVisiblePassword();
        
    }

    @And("Admin click on Forgot password")
    public void adminClickOnForgotPassword() throws Exception {
        Log4j.info("Click on Forgot password button");
        LoginPageTest.clickForgotPasswordButton();
        
    }



}
