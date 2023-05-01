package testObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import pageObjects.LessonsPage;
import utilities.CustomFluentWait;
import utilities.Log4j;
import utilities.OwnClick;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LessonsTest {

    private static final LessonsPage lessonsPage = page(LessonsPage.class);

    public static void clickAddLessonButton() throws Exception {
        //SelenideElement button = $("button.IconButton.dark > i.lemon.green.icon-add");
        SelenideElement addLessonButton = lessonsPage.getAddLessonButton().shouldBe(visible);
        OwnClick.baseClick(addLessonButton);
        //OwnClick.baseClick(button);
    }
    public static void checkTabTitle(String tab) {
        Log4j.info("Checking tab title");
        SelenideElement baseDataTab = lessonsPage.getBaseDataTab().shouldBe(visible);
        CustomFluentWait.fluentWait(baseDataTab, Condition.visible, 6, 500);
        baseDataTab.shouldHave(Condition.text(tab));
    }
}
