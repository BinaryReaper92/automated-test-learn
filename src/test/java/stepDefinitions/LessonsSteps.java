package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testObjects.LessonsTest;

public class LessonsSteps {

    @When("admin clicks on the Add Lesson button")
    public void adminClicksOnTheAddLessonButton() throws Exception {
        LessonsTest.clickAddLessonButton();
        
    }

    @Then("the add new lesson form is opened with the {string} tab active")
    public void theAddNewLessonFormIsOpenedWithTheTabActive(String tab) {
        LessonsTest.checkTabTitle(tab);
    }

    @And("admin enter title as {string}")
    public void adminEnterTitleAs(String lessonTitle) throws Exception {
        LessonsTest.enterLessonTitle(lessonTitle);

    }

    @And("admin choose Company as {string}")
    public void adminChooseCompanyAs(String lessonCompany) throws Exception {
        LessonsTest.chooseCompany(lessonCompany);
    }

    @And("admin choose Subsidiary as {string}")
    public void adminChooseSubsidiaryAs(String lessonSubsidiary) throws Exception {
        LessonsTest.chooseSubsidiary(lessonSubsidiary);
    }

    @And("admin choose image")
    public void adminChooseImage() throws Exception {
        LessonsTest.choosePicture();
    }

    @And("admin choose Tag as {string}")
    public void adminChooseTagAs(String lessonTag) throws Exception {
        LessonsTest.chooseTag(lessonTag);
    }

    @And("admin switch lesson to {string}")
    public void adminSwitchLessonTo(String labelText) throws Exception {
        LessonsTest.changeSwitchButton(labelText);
    }

    @And("admin enter price as {int}")
    public void adminEnterPriceAs(int lessonPrice) throws Exception {
        LessonsTest.enterPriceValue(lessonPrice);
    }

    @And("admin change Acceptance Rate to {int}")
    public void adminChangeAcceptanceRateTo(int lessonAcceptanceRate) throws Exception {
        LessonsTest.enterAcceptanceRateValue(lessonAcceptanceRate);
    }

    @And("admin enter Need to pass in day as {int}")
    public void adminEnterNeedToPassInDayAs(int lessonNeedToPassInDay) throws Exception {
        LessonsTest.enterNeedToPassInDayValue(lessonNeedToPassInDay);
    }

    @And("admin enter Lesson Type as {string}")
    public void adminEnterLessonTypeAs(String lessonType) throws Exception {
        LessonsTest.enterLessonType(lessonType);
    }

    @And("admin enter Description as {string}")
    public void adminEnterDescriptionAs(String lessonDescription) throws Exception {
        LessonsTest.enterLessonDescription(lessonDescription);
    }

    @And("admin enter Requirements as {string}")
    public void adminEnterRequirementsAs(String lessonRequirements) throws Exception {
        LessonsTest.enterLessonRequirement(lessonRequirements);
    }

    @And("admin click on add new Specific button")
    public void adminClickOnAddNewSpecificButton() throws Exception {
        LessonsTest.clickAddNewSpecific();
    }

    @Then("new Specific is added to the Lesson as {string}")
    public void newSpecificIsAddedToTheLessonAs(String lessonSpecificName) {
        LessonsTest.checkAddedSpecific(lessonSpecificName);
    }

    @And("admin enter Specific name as {string}")
    public void adminEnterSpecificNameAs(String lessonSpecificName) throws Exception {
        LessonsTest.enterSpecificName(lessonSpecificName);
    }

    @And("admin click on Finish editing button")
    public void adminClickOnFinishEditingButton() throws Exception {
        LessonsTest.clickFinish();
    }

    @Then("Success modal should be displayed as {string}")
    public void successModalShouldBeDisplayedAs(String successTitle) {
        LessonsTest.checkSuccess(successTitle);
    }

    @And("admin click on Success OK")
    public void adminClickOnSuccessOK() throws Exception {
        LessonsTest.clickSuccessOk();
    }
}
