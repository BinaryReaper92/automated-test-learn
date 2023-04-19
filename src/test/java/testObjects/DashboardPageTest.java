package testObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pageObjects.DashboardPage;
import utilities.Helper;
import utilities.Log4j;
import utilities.TakeScreenshot;

import static com.codeborne.selenide.Selenide.page;

public class DashboardPageTest {

    public static void checkIfPageTitleIs(String title) {
        Log4j.info("Checking page title");
        DashboardPage dashboardPage = page(DashboardPage.class);
        SelenideElement element = dashboardPage.getPageTitle();
        Helper.waitForElementToAppear(element, 10000);
        element.shouldHave(Condition.text(title));
        String screenshotName = "screenshotElement_" + System.currentTimeMillis() + ".png";
        TakeScreenshot.takeElementScreenshot(element, screenshotName);
        Log4j.info("Create screenshot of page title element");
    }
}
