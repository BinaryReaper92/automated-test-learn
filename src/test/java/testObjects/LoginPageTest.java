package testObjects;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;
import pageObjects.LoginPage;
import utilities.ConfigLoader;
import utilities.Log4j;
import utilities.OwnClick;
import utilities.OwnSendKeys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;



public class LoginPageTest{
    private static final LoginPage loginPage = page(LoginPage.class);
    private static String initialButtonColor;

    public static void openWebsite(String url){
        String website = ConfigLoader.getAppUrl() + url;
        Log4j.info("Opening the website:" + website);
        Selenide.open(website);
    }

    public static void setUserName(String username) throws Exception {

        SelenideElement emailInput = loginPage.getEmailInput().shouldBe(visible);
        OwnSendKeys.mySendKeys(emailInput, username);
    }

    public static void setPassword(String password) throws Exception {

        SelenideElement passwordInput = loginPage.getPasswordInput().shouldBe(visible);
        OwnSendKeys.mySendKeys(passwordInput, password);
    }

    public static void clickLogin() throws Exception {

        SelenideElement loginButton = loginPage.getLoginButton().shouldBe(visible);
        OwnClick.baseClick(loginButton);
    }
    public static void clickEmailField() throws Exception {

        SelenideElement emailField = loginPage.getEmailInput().shouldBe(visible);
        OwnClick.baseClick(emailField);
    }
    public static void clickPasswordField() throws Exception {

        SelenideElement passwordField = loginPage.getPasswordInput().shouldBe(visible);
        OwnClick.baseClick(passwordField);
    }
    public static SelenideElement emptyEmail() {

        SelenideElement emptyEmailError = loginPage.getEmailEmptyError().shouldBe(visible);
        return emptyEmailError;
    }
    public static SelenideElement emptyPassword() {

        SelenideElement emptyPasswordError = loginPage.getPasswordEmptyError().shouldBe(visible);
        return emptyPasswordError;
    }
    public static SelenideElement wrongEmailFormat(){

        SelenideElement wrongEmailFormat = loginPage.getEmailInvalidError().shouldBe(visible);
        return wrongEmailFormat;
    }
    public static SelenideElement generalInvalidError(){

        SelenideElement generalInvalidError = loginPage.getGeneralIncorrectError();
        return generalInvalidError;
    }
    public static void clickEyeButton() throws Exception {

        SelenideElement eyeButton = loginPage.getEyeButton().shouldBe(visible);
        OwnClick.baseClick(eyeButton);
    }
    public static void clickForgotPasswordButton() throws Exception {

        SelenideElement forgotPassWordButton = loginPage.getForgotPasswordButton().shouldBe(visible);
        OwnClick.baseClick(forgotPassWordButton);
    }
    public static void hoversOnLoginButton() {
        SelenideElement loginButton = loginPage.getLoginButton().shouldBe(visible);
        initialButtonColor = loginButton.getCssValue("background-color");
        loginButton.hover();
    }

    public static void loginButtonsColorShouldChangeToYellow() {
        SelenideElement loginButton = loginPage.getLoginButton().shouldBe(visible);
        String expectedColor = "rgba(245, 216, 14, 0.996)";
        String hoverColor = loginButton.getCssValue("background-color");

        Assert.assertNotEquals(initialButtonColor, hoverColor, "Button color did not change on hover");
        Assert.assertEquals(hoverColor, expectedColor, "Button color is not yellow on hover");
    }

    public static void getVisiblePassword(){
        SelenideElement passwordInput = loginPage.getPasswordInput().shouldBe(visible);
        passwordInput.shouldHave(attribute("type","text"));


    }

}
