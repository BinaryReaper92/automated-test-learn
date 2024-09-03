package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.CustomResponseModel;
import org.testng.Assert;
import testObjects.HttpRequestTest;

import java.io.IOException;

public class HttpRequestSteps {

    HttpRequestTest httpRequestTest = new HttpRequestTest();

    @Given("Get URL from config")
    public void get_url_from_config() {
        httpRequestTest.setRequestBaseURI();
    }

    @When("Calling the POST {string} endpoint with the given body: {string} while {string}")
    public void calling_the_post_request(String requestUrl, String bodyJson, String isLoggedIn) throws IOException {
        CustomResponseModel response = httpRequestTest.postRequest(requestUrl, bodyJson, isLoggedIn);
        httpRequestTest.setResponse(response);
    }

    @Then("Receiving {int}")
    public void receiving(int expectedStatusCode) {
        Assert.assertEquals(httpRequestTest.getResponseStatusCode(), expectedStatusCode);
        System.out.println(expectedStatusCode);
        System.out.println(httpRequestTest.getResponseStatusCode());
    }

    @When("Calling the GET {string} endpoint")
    public void calling_the_get_endpoint(String requestUrl) {
        CustomResponseModel response = httpRequestTest.getRequest(requestUrl);
        httpRequestTest.setResponse(response);
        httpRequestTest.printResponseBodyIfStatusOk();
    }

    @When("Calling the PUT {string} endpoint with the given body: {string}")
    public void calling_the_put_endpoint(String requestUrl, String bodyJson) {
        CustomResponseModel response = httpRequestTest.putRequest(requestUrl, bodyJson);
        httpRequestTest.setResponse(response);
    }

    @When("Calling the DELETE {string} endpoint")
    public void calling_the_delete_endpoint(String requestUrl) {
        CustomResponseModel response = httpRequestTest.deleteRequest(requestUrl);
        httpRequestTest.setResponse(response);
    }

    @Then("Receiving id {int}")
    public void receivingId(int expectedId) {
        int actualId = httpRequestTest.getResponseId();
        Assert.assertEquals(expectedId, actualId);
    }

}
