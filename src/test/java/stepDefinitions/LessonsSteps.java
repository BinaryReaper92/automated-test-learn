package stepDefinitions;

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
}
