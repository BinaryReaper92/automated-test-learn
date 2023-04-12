package testObjects;

import com.codeborne.selenide.Condition;
import pageObjects.DashboardPage;
import utilities.Helper;

import static com.codeborne.selenide.Selenide.page;

public class DashboardPageTest {

    public static void checkIfPageTitleIs (String title){
        DashboardPage dashboardPage = page(DashboardPage.class);
        Helper.waitForElementToAppear(dashboardPage.getPageTitle(), 10000);
        dashboardPage.getPageTitle().shouldHave(Condition.text(title));
    }
}
