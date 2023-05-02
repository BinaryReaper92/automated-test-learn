package testObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pageObjects.LessonsPage;
import pageObjects.SpecificModal;
import utilities.CustomFluentWait;
import utilities.Log4j;
import utilities.OwnClick;
import utilities.OwnSendKeys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class SpecificModalTest {

    private static final SpecificModal specificModal = page(SpecificModal.class);
    private static final LessonsPage lessonsPage = page(LessonsPage.class);

    public static void checkSpecificModalTitle(String specificModalTitle) {
        Log4j.info("Checking Specific Modal title");
        SelenideElement newSpecificModal = specificModal.getNewSpecificTitle().shouldBe(visible);
        CustomFluentWait.fluentWait(newSpecificModal, Condition.visible, 6, 500);
        newSpecificModal.shouldHave(Condition.text(specificModalTitle));
    }
    public static void enterKeyName(String specificKey) throws Exception {
        Log4j.info("Entering Key Name");
        SelenideElement specificKeyField = specificModal.getKeyField().shouldBe(visible);
        OwnSendKeys.mySendKeys(specificKeyField,specificKey);
    }
    public static void chooseSpecificType(String specificType) throws Exception {
        Log4j.info("Choosing Specific Type from dropdown");
        SelenideElement specificTypeDropdown = specificModal.getTypeDropdown().shouldBe(visible);
        OwnClick.baseClick(specificTypeDropdown);
        SelenideElement specificTypeOption = specificModal.getTypeShortText();
        OwnClick.baseClick(specificTypeOption);
    }
    public static void uncheckAddItInGeneralBlockCheckbox() throws Exception {
        Log4j.info("Uncheck Add it in general block checkbox");
        SelenideElement addItInGeneralBlockCheckbox = specificModal.getAddItInGeneralBlockCheckbox().shouldBe(visible);
        OwnClick.baseClick(addItInGeneralBlockCheckbox);
    }
    public static void clickOnWhereIsComingFromTheKEYOfThePRODUCTQuestion() throws Exception {
        Log4j.info("Add Where is coming from the [KEY] of the [PRODUCT]? question to Question field");
        SelenideElement addQuestionToQuestionField = specificModal.getFirstQuestion();
        OwnClick.baseClick(addQuestionToQuestionField);
    }
    public static void clickOkButton() throws Exception {
        Log4j.info("Uncheck Add it in general block checkbox");
        SelenideElement okButton = specificModal.getOkButton().shouldBe(visible);
        OwnClick.baseClick(okButton);
    }
}
