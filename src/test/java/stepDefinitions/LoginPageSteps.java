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
        LoginPageTest.setUserName(ConfigReader.getValidUsername());

    }
    @And("Admin enters existing valid Password")
    public void adminEntersExistingValidPassword() throws Exception {
        Log4j.info("Enter password");
        LoginPageTest.setPassword(ConfigReader.getValidPassword());
    }
    @And("Admin clicks on Login")
    public void adminClicksOnLogin() throws Exception {
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

    @And("Admin hoovers on Login button")
    public void adminHooversOnLoginButton() {
        Log4j.info("Hover on login button");
        LoginPageTest.hoversOnLoginButton();

        
    }

    @Then("Login button's color should change to yellow")
    public void loginButtonSColorShouldChangeToYellow() {
        Log4j.info("Login button change color");
        LoginPageTest.loginButtonsColorShouldChangeToYellow();
        
    }

    @And("Admin clicks on Eye icon")
    public void adminClicksOnEyeIcon() throws Exception {
        Log4j.info("Click on Eye button");
        LoginPageTest.clickEyeButton();
        
    }

    @Then("Password should be visible")
    public void passwordShouldBeVisible() {
        Log4j.info("Password is visible");
        LoginPageTest.getVisiblePassword();
        
    }

    @And("Admin clicks on Forgot password")
    public void adminClicksOnForgotPassword() throws Exception {
        Log4j.info("Click on Forgot password button");
        LoginPageTest.clickForgotPasswordButton();
        
    }



}
