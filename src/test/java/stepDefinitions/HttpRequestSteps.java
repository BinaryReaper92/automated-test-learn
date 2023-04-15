package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import testObjects.DashboardPageTest;
import utilities.ConfigLoader;
import utilities.HttpRequests;
import utilities.JSONReader;

import java.io.IOException;
import java.sql.SQLOutput;

public class HttpRequestSteps {

    String requestBaseURI = null;
    Response loginResponse = null;

    @Given("Get URL from config")
    public void get_url_from_config() {
       requestBaseURI = ConfigLoader.getAppURI();
    }
    @When("Calling the {string} endpoint with the given body: {string} while {string}")
    public void calling_the_request(String requestUrl, String bodyJson, String isLoggedIn) {
       loginResponse = HttpRequests.PostAPI(requestBaseURI,isLoggedIn, JSONReader.getRequestBody(bodyJson),requestUrl);


    }
    @Then("Receiving {int}")
    public void receiving(int int1) {
        Assert.assertEquals(int1,loginResponse.getStatusCode());
        System.out.println(loginResponse.getStatusCode());

    }

}
