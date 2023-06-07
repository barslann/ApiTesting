package StepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.AuthResponse;
import model.UserCreateRequest;
import org.junit.Assert;

public class EnpointFunctionalityStepDefs {

    private RequestSpecification request;
    private Response response;

    @Given("a user registration request with valid data")
    public void aUserRegistrationRequestWithValidData() {
        // Prepare valid request data
       // String validRequestData = "{\"username\": \"john_doe\", \"password\": \"password123\"}";

        // body i hazirladik.


        System.out.println("calisiyorrr");


        int random = (int) (Math.random() * 100) ;

        UserCreateRequest validRequestData = new UserCreateRequest();
        validRequestData.setFirstname("John");
        validRequestData.setLastname("Doe");
        validRequestData.setUsername("john" + random);
        validRequestData.setPassword("password123");
        validRequestData.setEmail("john" + random + "@example.com");
        validRequestData.setQuestion("Favorite color");
        validRequestData.setAnswer("Blue");
        validRequestData.setPhone("1234567890");
        validRequestData.setAddress("123 Main St");

        // Set up the request
        request = RestAssured.given()
                .contentType("application/json")
                .body(validRequestData);


    }

    @Given("a user registration request with invalid data")
    public void aUserRegistrationRequestWithInvalidData() {
        // Prepare invalid request data
       // String invalidRequestData = "{\"username\": \"\", \"password\": \"password123\"}";

        UserCreateRequest invalidRequestData = new UserCreateRequest();
        invalidRequestData.setFirstname(""); // Empty first name
        invalidRequestData.setLastname("Doe");
        invalidRequestData.setUsername("john_doe3");
        invalidRequestData.setPassword("password123");
        invalidRequestData.setEmail("invalid-email"); // Invalid email format
        invalidRequestData.setQuestion("Favorite color");
        invalidRequestData.setAnswer("Blue");
        invalidRequestData.setPhone("1234567890");
        invalidRequestData.setAddress("123 Main St");

        // Set up the request
        request = RestAssured.given()
                .contentType("application/json")
                .body(invalidRequestData);
    }

    @When("the request is made to the register endpoint")
    public void theRequestIsMadeToTheRegisterEndpoint() {
        // Make the POST request to the /register endpoint
        response = request.log().all().post("http://18.220.255.46:8080/auth/register");

        response.prettyPrint();
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        // Assert the response status code
        Assert.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @And("the response body should contain the registration details")
    public void theResponseBodyShouldContainTheRegistrationDetails() {
        // Assert the presence of registration details in the response body
        // Adjust this assertion based on the actual response structure

        AuthResponse authResponse = response.as(AuthResponse.class);
        Assert.assertEquals("Successfully registered!", authResponse.getMessage());
    }

    @And("the response body should contain the error message")
    public void theResponseBodyShouldContainTheErrorMessage() {
        // Assert the presence of an error message in the response body
        // Adjust this assertion based on the actual response structure
        Assert.assertNotEquals("Successfully registered!", response.getBody().asString());
    }
}


