package StepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.LoginRequestModel;
import model.LoginResponseModel;
import model.LoginWrongResponseModel;
import org.junit.jupiter.api.Assertions;

public class AuthStepDefs {

    private String baseUrl;
    private String username;
    private String password;
    private String authToken;


    LoginResponseModel responseModel;
    LoginWrongResponseModel loginWrongResponseModel;


    @Given("the API base URL is {string}")
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        RestAssured.baseURI = baseUrl;
    }

    @Given("the credentials are {string} and {string}")
    public void setCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @When("a POST request is made to {string}")
    public void sendPostRequest(String endpoint) {

//        String body = "{\n" +
//                "    \"username\":\"test2\",\n" +
//                "    \"password\":\"test2\"\n" +
//                "}";

        LoginRequestModel body = new LoginRequestModel(username,password);


         responseModel = RestAssured.given().body(body)
                .when()
                .contentType(ContentType.JSON)
                .log().all()
                .post(endpoint).then().extract().as(LoginResponseModel.class);


        authToken = responseModel.getMessage();

       // Assertions.assertEquals(200, response.getStatusCode());
       // authToken = response.jsonPath().getString("message");
    }

    @Then("the response body contains an authentication token")
    public void verifyTokenPresent() {
        System.out.println("Token : " + authToken);
        Assertions.assertNotNull(authToken);
    }

    @When("a POST request is made to {string} with wrong credentials")
    public void aPOSTRequestIsMadeToWithWrongCredentials(String endpoint) {

        LoginRequestModel body = new LoginRequestModel(username,password);


        loginWrongResponseModel = RestAssured.given().body(body)
                .when()
                .contentType(ContentType.JSON)
                .log().all()
                .post(endpoint).then().extract().as(LoginWrongResponseModel.class);
    }

    @Then("Message should be {string}")
    public void messageShouldBe(String message) {

        final String expectedMessage = "The username or password you entered is incorrect";

        final String actualMessage =  loginWrongResponseModel.getMessage();

        Assertions.assertEquals(expectedMessage,actualMessage);

    }


}
