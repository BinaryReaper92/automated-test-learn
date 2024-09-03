package pageObjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ForgotPasswordModal {
    public SelenideElement getForgotPasswordModalTitle(){
        return $x("//h5[contains(text(),'Forgot password')]");
    }
}
