package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;
import utilities.ConfigLoader;
import utilities.HttpRequests;
import utilities.JSONReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;


public class HttpRequestSteps {

    String requestBaseURI = null;
    Response response = null;

    @Given("Get URL from config")
    public void get_url_from_config() {
       requestBaseURI = ConfigLoader.getAppURI();
    }
    @When("Calling the {string} endpoint with the given body: {string} while {string}")
    public void calling_the_request(String requestUrl, String bodyJson, String isLoggedIn) {
       response = HttpRequests.PostAPI(requestBaseURI,isLoggedIn, JSONReader.getRequestBody(bodyJson),requestUrl);


    }
    @Then("Receiving {int}")
    public void receiving(int int1) {
        Assert.assertEquals(int1,response.getStatusCode());
        System.out.println(response.getStatusCode());

    }
    @When("Calling the {string} endpoint while {string}")
    public void calling_the_endpoint_while(String requestUrl, String isLoggedIn) {
        response = HttpRequests.GetAPI(requestBaseURI, requestUrl);

        if (response.statusCode() == 200) {
            String responseBody = response.getBody().asString();
            System.out.println(responseBody);
            JSONReader.getAPIWriter(responseBody);
        } else {
            System.out.println("Failed to get data, status code: " + response.statusCode());
        }

    }

    @When("Calling the {string} endpoint with the given body: {string}")
    public void callingTheEndpoint(String requestUrl, String bodyJson) {
        response = HttpRequests.PutAPI(requestBaseURI,JSONReader.getRequestBody(bodyJson), requestUrl);

    }

    @Then("Receiving id {int}")
    public void receivingId(int expectedId) {
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, Integer> responseMap = objectMapper.readValue(responseBody, Map.class);
            int actualId = responseMap.get("id");
            Assert.assertEquals(expectedId, actualId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
