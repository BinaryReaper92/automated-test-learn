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

    @And("admin enters title as {string}")
    public void adminEntersTitleAs(String lessonTitle) throws Exception {
        LessonsTest.enterLessonTitle(lessonTitle);

    }

    @And("admin chooses Company as {string}")
    public void adminChoosesCompanyAs(String lessonCompany) throws Exception {
        LessonsTest.chooseCompany(lessonCompany);
    }

    @And("admin chooses Subsidiary as {string}")
    public void adminChoosesSubsidiaryAs(String lessonSubsidiary) throws Exception {
        LessonsTest.chooseSubsidiary(lessonSubsidiary);
    }

    @And("admin chooses image")
    public void adminChoosesImage() throws Exception {
        LessonsTest.choosePicture();
    }

    @And("admin chooses Tag as {string}")
    public void adminChoosesTagAs(String lessonTag) throws Exception {
        LessonsTest.chooseTag(lessonTag);
    }

    @And("admin switches lesson to {string}")
    public void adminSwitchesLessonTo(String labelText) throws Exception {
        LessonsTest.changeSwitchButton(labelText);
    }

    @And("admin enters price as {int}")
    public void adminEntersPriceAs(int lessonPrice) throws Exception {
        LessonsTest.enterPriceValue(lessonPrice);
    }

    @And("admin changes Acceptance Rate to {int}")
    public void adminChangesAcceptanceRateTo(int lessonAcceptanceRate) throws Exception {
        LessonsTest.enterAcceptanceRateValue(lessonAcceptanceRate);
    }

    @And("admin enters Need to pass in day as {int}")
    public void adminEntersNeedToPassInDayAs(int lessonNeedToPassInDay) throws Exception {
        LessonsTest.enterNeedToPassInDayValue(lessonNeedToPassInDay);
    }

    @And("admin enters Lesson Type as {string}")
    public void adminEntersLessonTypeAs(String lessonType) throws Exception {
        LessonsTest.enterLessonType(lessonType);
    }

    @And("admin enters Description as {string}")
    public void adminEntersDescriptionAs(String lessonDescription) throws Exception {
        LessonsTest.enterLessonDescription(lessonDescription);
    }

    @And("admin enters Requirements as {string}")
    public void adminEntersRequirementsAs(String lessonRequirements) throws Exception {
        LessonsTest.enterLessonRequirement(lessonRequirements);
    }

    @And("admin clicks on add new Specific button")
    public void adminClicksOnAddNewSpecificButton() throws Exception {
        LessonsTest.clickAddNewSpecific();
    }

    @Then("new Specific is added to the Lesson as {string}")
    public void newSpecificIsAddedToTheLessonAs(String lessonSpecificName) {
        LessonsTest.checkAddedSpecific(lessonSpecificName);
    }

    @And("admin enters Specific name as {string}")
    public void adminEntersSpecificNameAs(String lessonSpecificName) throws Exception {
        LessonsTest.enterSpecificName(lessonSpecificName);
    }

    @And("admin clicks on Finish editing button")
    public void adminClicksOnFinishEditingButton() throws Exception {
        LessonsTest.clickFinish();
    }

    @Then("Success modal should be displayed as {string}")
    public void successModalShouldBeDisplayedAs(String successTitle) {
        LessonsTest.checkSuccess(successTitle);
    }

    @And("admin clicks on Success OK")
    public void adminClicksOnSuccessOK() throws Exception {
        LessonsTest.clickSuccessOk();
    }
}
