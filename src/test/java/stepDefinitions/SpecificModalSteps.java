package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import testObjects.SpecificModalTest;

public class SpecificModalSteps {

    @Then("Add New Specific Modal is displayed as {string}")
    public void addNewSpecificModalIsDisplayedAs(String specificModalTitle) {
        SpecificModalTest.checkSpecificModalTitle(specificModalTitle);
    }

    @And("admin enter Key as {string}")
    public void adminEnterKeyAs(String specificKey) throws Exception {
        SpecificModalTest.enterKeyName(specificKey);
    }

    @And("admin select Type as {string}")
    public void adminSelectTypeAs(String specificType) throws Exception {
        SpecificModalTest.chooseSpecificType(specificType);
    }

    @And("admin uncheck Add it in general block")
    public void adminUncheckAddItInGeneralBlock() throws Exception {
        SpecificModalTest.uncheckAddItInGeneralBlockCheckbox();

    }

    @And("admin click on Where is coming from the [KEY] of the [PRODUCT]? question")
    public void adminClickOnWhereIsComingFromTheKEYOfThePRODUCTQuestion() throws Exception {
        SpecificModalTest.clickOnWhereIsComingFromTheKEYOfThePRODUCTQuestion();
    }

    @And("admin click on OK")
    public void adminClickOnOK() throws Exception {
        SpecificModalTest.clickOkButton();
    }



}
