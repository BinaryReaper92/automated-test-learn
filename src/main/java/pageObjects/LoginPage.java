package pageObjects;

import com.codeborne.selenide.SelenideElement;
import utilities.ElementFinder;

public class LoginPage {

    public SelenideElement getEmailInput() {
        return ElementFinder.myFindElement("(//form//input)[1]");
    }

    public SelenideElement getPasswordInput() {
        return ElementFinder.myFindElement("(//form//input)[2]");
    }

    public SelenideElement getLoginButton() {
        return ElementFinder.myFindElement("//button[contains(@class,'login-form__button-container__button')]");
    }
}
