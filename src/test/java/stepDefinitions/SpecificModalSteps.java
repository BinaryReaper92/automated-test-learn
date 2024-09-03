package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import testObjects.SpecificModalTest;

public class SpecificModalSteps {

    @Then("Add New Specific Modal is displayed as {string}")
    public void addNewSpecificModalIsDisplayedAs(String specificModalTitle) {
        SpecificModalTest.checkSpecificModalTitle(specificModalTitle);
    }

    @And("admin enters Key as {string}")
    public void adminEntersKeyAs(String specificKey) throws Exception {
        SpecificModalTest.enterKeyName(specificKey);
    }

    @And("admin selects Type as {string}")
    public void adminSelectsTypeAs(String specificType) throws Exception {
        SpecificModalTest.chooseSpecificType(specificType);
    }

    @And("admin unchecks Add it in general block")
    public void adminUnchecksAddItInGeneralBlock() throws Exception {
        SpecificModalTest.uncheckAddItInGeneralBlockCheckbox();

    }

    @And("admin clicks on Where is coming from the [KEY] of the [PRODUCT]? question")
    public void adminClicksOnWhereIsComingFromTheKEYOfThePRODUCTQuestion() throws Exception {
        SpecificModalTest.clickOnWhereIsComingFromTheKEYOfThePRODUCTQuestion();
    }

    @And("admin clicks on OK")
    public void adminClicksOnOK() throws Exception {
        SpecificModalTest.clickOkButton();
    }



}
