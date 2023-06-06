package StepDefs;

import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;


public class RequestResponseValidation {

    private RequestSpecification request;
    private Response response;
    private int userId;

    @Given("2a user registration request with valid data")
    public void aUserRegistrationRequestWithValidData() {
        // Prepare valid request data
        String validRequestData = "{\"firstname\":\"John\",\"lastname\":\"Doe\",\"username\":\"john_doe51\",\"password\":\"password123\",\"email\":\"john.doe51@example.com\",\"question\":\"Favorite color\",\"answer\":\"Blue\",\"phone\":\"1234567890\",\"address\":\"123 Main St\"}";

        // Set up the request
        request = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(validRequestData);
    }

    @When("2the request is made to the /register endpoint")
    public void theRequestIsMadeToTheRegisterEndpoint() {
        // Make the POST request to the /register endpoint
        response = request.post("http://localhost:8080/auth/register");
        userId = response.path("userId"); // Extract the userId from the response
    }

    @Then("2the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        // Assert the response status code
        Assert.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @And("2the response body should match the expected body")
    public void theResponseBodyShouldMatchTheExpectedBody() {
        // Set the expected response body with dynamic userId
        String expectedResponseBody = "{\"message\":\"Successfully registered!\",\"userId\":" + userId + ",\"role\":\"USER\"}";


        // Assert the response body against the expected body using JSON schema validation
        response.then().assertThat().body(matchesJsonSchemaInClasspath("response.json"));
        Assert.assertEquals(expectedResponseBody, response.getBody().asString());
    }
}

