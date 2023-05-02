package testObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import pageObjects.LessonsPage;
import utilities.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LessonsTest {

    private static final LessonsPage lessonsPage = page(LessonsPage.class);

    public static void clickAddLessonButton() throws Exception {
        SelenideElement addLessonButton = lessonsPage.getAddLessonButton().shouldBe(visible);
        OwnClick.baseClick(addLessonButton);
    }
    public static void checkTabTitle(String tab) {
        Log4j.info("Checking tab title");
        SelenideElement baseDataTab = lessonsPage.getBaseDataTab().shouldBe(visible);
        CustomFluentWait.fluentWait(baseDataTab, Condition.visible, 6, 500);
        baseDataTab.shouldHave(Condition.text(tab));
    }
    public static void enterLessonTitle(String lessonTitle) throws Exception {
        Log4j.info("Entering Lesson Title");
        SelenideElement lessonTitleField = lessonsPage.getLessonTitle().shouldBe(visible);
        OwnSendKeys.mySendKeys(lessonTitleField,lessonTitle);
    }
    public static void chooseCompany(String lessonCompany) throws Exception {
        Log4j.info("Choosing Company from dropdown");
        SelenideElement lessonCompanyDropdown = lessonsPage.getCompanyDropdown().shouldBe(visible);
        OwnClick.baseClick(lessonCompanyDropdown);
        SelenideElement optionToSelect = lessonsPage.getCompanyTest();
        OwnClick.baseClick(optionToSelect);

    }
    public static void chooseSubsidiary(String lessonSubsidiary) throws Exception {
        Log4j.info("Choosing Subsidiary from dropdown");
        SelenideElement lessonSubsidiaryDropdown = lessonsPage.getSubsidiaryDropdown().shouldBe(visible);
        OwnClick.baseClick(lessonSubsidiaryDropdown);
        SelenideElement optionToSelect = lessonsPage.getSubsidiaryTest();
        OwnClick.baseClick(optionToSelect);
    }
    public static void choosePicture() throws Exception {
        Log4j.info("Adding picture");
        String picturePath = "D:\\Learn\\automated-test-skeleton\\src\\main\\resources\\test.jpg";
        SelenideElement dropOrBrowse = lessonsPage.getDropOrBrowse();
        OwnClick.baseClick(dropOrBrowse);
        Helper.setClipboardContent(picturePath);
        Helper.windowsHelper();

    }
    public static void chooseTag(String lessonTag) throws Exception {
        Log4j.info("Choosing Company from dropdown");
        SelenideElement lessonTagDropdown = lessonsPage.getTagsDropdown().shouldBe(visible);
        OwnClick.baseClick(lessonTagDropdown);
        SelenideElement optionToSelect = lessonsPage.getTagsTest();
        OwnClick.baseClick(optionToSelect);
    }
    public static void changeSwitchButton(String expectedLabelText) throws Exception {
        Log4j.info("Switch button to public");
        SelenideElement lessonSwitchButton = lessonsPage.getSwitchButton().shouldBe(visible);
        OwnClick.baseClick(lessonSwitchButton);
        SelenideElement lessonSwitchButtonLabel = lessonsPage.getSwitchButtonLabelText();
        lessonSwitchButtonLabel.shouldHave(Condition.text(expectedLabelText));
    }
    public static void enterPriceValue(int lessonPrice) throws Exception {
        Log4j.info("Entering Lesson Price");
        SelenideElement priceField = lessonsPage.getPriceField().shouldBe(visible);
        priceField.sendKeys(Keys.chord(Keys.SHIFT, Keys.END));
        OwnSendKeys.mySendKeys(priceField, String.valueOf(lessonPrice));
    }
    public static void enterAcceptanceRateValue(int lessonAcceptanceRate) throws Exception {
        Log4j.info("Entering Acceptance Rate Value");
        SelenideElement acceptanceRateField = lessonsPage.getAcceptanceRate().shouldBe(visible);
        acceptanceRateField.sendKeys(Keys.chord(Keys.SHIFT, Keys.END));
        OwnSendKeys.mySendKeys(acceptanceRateField, String.valueOf(lessonAcceptanceRate));
    }
    public static void enterNeedToPassInDayValue(int lessonNeedToPassInDay) throws Exception {
        Log4j.info("Entering Need To Pass In Day value");
        SelenideElement needToPassInDayField = lessonsPage.getNeedToPassInDay().shouldBe(visible);
        needToPassInDayField.sendKeys(Keys.chord(Keys.SHIFT, Keys.END));
        OwnSendKeys.mySendKeys(needToPassInDayField, String.valueOf(lessonNeedToPassInDay));
    }
    public static void enterLessonType(String lessonType) throws Exception {
        Log4j.info("Entering Lesson Type");
        SelenideElement lessonTypeField = lessonsPage.getLessonType().shouldBe(visible);
        OwnSendKeys.mySendKeys(lessonTypeField,lessonType);
    }

    public static void enterLessonDescription(String lessonDescription) throws Exception {
        Log4j.info("Entering Lesson Description");
        SelenideElement lessonDescriptionField = lessonsPage.getDescription().shouldBe(visible);
        OwnSendKeys.mySendKeys(lessonDescriptionField, lessonDescription);
    }
    public static void enterLessonRequirement(String lessonRequirement) throws Exception {
        Log4j.info("Entering Lesson Requirement");
        SelenideElement lessonRequirementField = lessonsPage.getRequirements().shouldBe(visible);
        OwnSendKeys.mySendKeys(lessonRequirementField, lessonRequirement);
    }
    public static void clickAddNewSpecific() throws Exception {
        Log4j.info("Click on Add New Specific button");
        SelenideElement addNewSpecificButton = lessonsPage.getAddNewSpecificsButton().shouldBe(visible);
        OwnClick.baseClick(addNewSpecificButton);
    }
    public static void checkAddedSpecific(String lessonSpecificName){
        Log4j.info("Checking specific name");
        SelenideElement firstSpecificName = lessonsPage.getFirstSpecificNameField().shouldBe(visible);
        CustomFluentWait.fluentWait(firstSpecificName, Condition.visible, 6, 500);
        firstSpecificName.shouldHave(Condition.text(lessonSpecificName));
    }
    public static void enterSpecificName(String lessonSpecificName) throws Exception {
        Log4j.info("Entering Lesson Title");
        SelenideElement lessonSpecificField = lessonsPage.getFirstSpecificField();
        OwnClick.baseClick(lessonSpecificField);
        OwnSendKeys.mySendKeys(lessonSpecificField,lessonSpecificName);
    }
    public static void clickFinish() throws Exception {
        Log4j.info("Click on Add New Specific button");
        SelenideElement finishEditingButton = lessonsPage.getFinishEditingButton().shouldBe(visible);
        OwnClick.baseClick(finishEditingButton);
    }
    public static void checkSuccess(String successTitle){
        Log4j.info("Checking success title");
        SelenideElement successTitleElement = lessonsPage.getSuccessTitle().shouldBe(visible);
        CustomFluentWait.fluentWait(successTitleElement, Condition.visible, 6, 500);
        successTitleElement.shouldHave(Condition.text(successTitle));
    }
    public static void clickSuccessOk() throws Exception {
        Log4j.info("Click on Success OK button");
        SelenideElement successOkButton = lessonsPage.getSuccessOkButton().shouldBe(visible);
        OwnClick.baseClick(successOkButton);
    }

}
