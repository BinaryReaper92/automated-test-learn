package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import testObjects.HttpRequestTest;

public class HttpRequestSteps {

    HttpRequestTest httpRequestTest = new HttpRequestTest();

    @Given("Get URL from config")
    public void get_url_from_config() {
        httpRequestTest.setRequestBaseURI();
    }

    @When("Calling the {string} endpoint with the given body: {string} while {string}")
    public void calling_the_request(String requestUrl, String bodyJson, String isLoggedIn) {
        Response response = httpRequestTest.postRequest(requestUrl, bodyJson, isLoggedIn);
        httpRequestTest.setResponse(response);
    }

    @Then("Receiving {int}")
    public void receiving(int statusCode) {
        Assert.assertEquals(statusCode, httpRequestTest.getResponseStatusCode());
        System.out.println(httpRequestTest.getResponseStatusCode());
    }

    @When("Calling the {string} endpoint while {string}")
    public void calling_the_endpoint_while(String requestUrl, String isLoggedIn) {
        Response response = httpRequestTest.getRequest(requestUrl, isLoggedIn);
        httpRequestTest.setResponse(response);
        httpRequestTest.printResponseBodyIfStatusOk();
    }

    @When("Calling the {string} endpoint with the given body: {string}")
    public void callingTheEndpoint(String requestUrl, String bodyJson) {
        Response response = httpRequestTest.putRequest(requestUrl, bodyJson);
        httpRequestTest.setResponse(response);
    }

    @Then("Receiving id {int}")
    public void receivingId(int expectedId) {
        int actualId = httpRequestTest.getResponseId();
        Assert.assertEquals(expectedId, actualId);
    }

}
