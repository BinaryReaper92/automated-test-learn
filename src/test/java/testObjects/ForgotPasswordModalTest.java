package testObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pageObjects.ForgotPasswordModal;
import utilities.CustomFluentWait;
import utilities.Log4j;
import utilities.TakeScreenshot;

import static com.codeborne.selenide.Selenide.page;

public class ForgotPasswordModalTest {

    private static final ForgotPasswordModal forgotPasswordModal = page(ForgotPasswordModal.class);

    public static void checkForgotPasswordModalTitle(String title) {
        Log4j.info("Checking modal title");
        SelenideElement element = forgotPasswordModal.getForgotPasswordModalTitle();
        CustomFluentWait.fluentWait(element, Condition.visible, 6, 500);
        element.shouldHave(Condition.text(title));
    }
}
