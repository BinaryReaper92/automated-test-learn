package pageObjects;

import com.codeborne.selenide.SelenideElement;
import utilities.ElementFinder;

import static com.codeborne.selenide.Selenide.$x;

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
    public SelenideElement getEmailEmptyError(){
        return ElementFinder.myFindElement("//span[contains(text(),'This field is required!')]");
    }

    public SelenideElement getPasswordEmptyError(){
        return ElementFinder.myFindElement("//body/div[@id='root']/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/span[2]/div[1]/span[1]");
    }

    public SelenideElement getEmailInvalidError(){
        return ElementFinder.myFindElement("//span[contains(text(),'Please provide valid email address!')]");
    }

    public SelenideElement getGeneralIncorrectError(){
        return ElementFinder.myFindElement("Incorrect Email or Password!");
    }
    public SelenideElement getEyeButton(){
        return ElementFinder.myFindElement("//body/div[@id='root']/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/span[1]/button[1]/i[1]");
    }
    public SelenideElement getForgotPasswordButton(){
        return ElementFinder.myFindElement("//div[contains(text(),'Forgot password')]");
    }
}
