package testObjects;

import lombok.extern.slf4j.Slf4j;
import pageObjects.LoginPage;
import utilities.Log4j;
import utilities.OwnClick;
import utilities.OwnSendKeys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;


public class LoginPageTest{

    public static void setUserName(String username) throws Exception {

        LoginPage loginPage = page(LoginPage.class);
        loginPage.getEmailInput().shouldBe(visible);
        OwnSendKeys.mySendKeys(loginPage.getEmailInput(), username);
    }

    public static void setPassword(String password) throws Exception {

        LoginPage loginPage = page(LoginPage.class);
        loginPage.getPasswordInput().shouldBe(visible);
        OwnSendKeys.mySendKeys(loginPage.getPasswordInput(),password);


    }

    public static void clickLogin() throws Exception {

        LoginPage loginPage = page(LoginPage.class);
        loginPage.getLoginButton().shouldBe(visible);
        OwnClick.baseClick(loginPage.getLoginButton());


    }
}
