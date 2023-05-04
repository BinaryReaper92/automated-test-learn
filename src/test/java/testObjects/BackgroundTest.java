package testObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import pageObjects.DashboardPage;
import pageObjects.LessonsPage;
import pageObjects.LoginPage;
import utilities.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class BackgroundTest {

    private static final LoginPage loginPage = page(LoginPage.class);
    private static final DashboardPage dashboardPage = page(DashboardPage.class);
    private static final LessonsPage lessonsPage = page(LessonsPage.class);

    public static void login() throws Exception {
        String website = ConfigReader.getAppUrl();
        Selenide.open(website);
        SelenideElement emailInput = loginPage.getEmailInput().shouldBe(visible);
        MySendKeys.mySendKeys(emailInput, ConfigReader.getUsername());
        SelenideElement passwordInput = loginPage.getPasswordInput().shouldBe(visible);
        MySendKeys.mySendKeys(passwordInput, ConfigReader.getPassword());
        SelenideElement loginButton = loginPage.getLoginButton().shouldBe(visible);
        MyClick.myClick(loginButton);
        SelenideElement element = dashboardPage.getDashboardPageTitle();
        CustomFluentWait.fluentWait(element, Condition.visible, 20, 500);
    }

    public static void clickOnLessonsMenuItem() throws Exception {
        SelenideElement lessonsMenuItem = lessonsPage.getLessonsMenuItem().shouldBe(visible);
        MyClick.myClick(lessonsMenuItem);
    }
    public static void checkLessonsPageTitle(String title) {
        Log4j.info("Checking page title");
        SelenideElement element = lessonsPage.getLessonsPageTitle();
        CustomFluentWait.fluentWait(element, Condition.visible, 10, 500);
        element.shouldHave(Condition.text(title));
    }
}
