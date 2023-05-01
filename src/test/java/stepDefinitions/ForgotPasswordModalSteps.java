package stepDefinitions;

import io.cucumber.java.en.Then;
import testObjects.ForgotPasswordModalTest;

public class ForgotPasswordModalSteps {

    @Then("Forgot password modal should appear with title {string}")
    public void forgotPasswordModalShouldAppearWithTitle(String modalTitle) {
        ForgotPasswordModalTest.checkForgotPasswordModalTitle(modalTitle);
    }
}
