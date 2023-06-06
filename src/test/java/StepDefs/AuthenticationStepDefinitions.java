package StepDefs;//package StepDefs;
//
//import io.cucumber.java.en.*;
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import org.junit.Assert;
//
//public class AuthenticationStepDefinitions {
//
//    private RequestSpecification request;
//    private Response response;
//    private String bearerToken;
//
//    @Given("a user with valid credentials")
//    public void aUserWithValidCredentials() {
//        // Set up the request with valid user credentials
//        bearerToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huX2RvZTUwIiwiZXhwIjoxNjg1MDc0Mzc2LCJpYXQiOjE2ODUwNzA3NDh9.4BucbDFfgkMz70VZk-0wWSAJVY4sC6LRUnf0mat2p_phqHHuO6TnoKnXVqH6OA1J4DZATo_qOyFYmuUvgs9g5Q";
//    }
//
//    @When("the user attempts to delete a user by id")
//    public void theUserAttemptsToDeleteUserById() {
//        // Set up the request with the bearer token
//        request = RestAssured.given()
//                .contentType(ContentType.JSON)
//                .header("Authorization", "Bearer " + bearerToken);
//        // Make the DELETE request to the deleteUserById endpoint
//        response = request.delete("http://localhost:8080/users/deleteUserById/18"); // Replace {userId} with actual user ID
//    }
//
//    @Then("3the response status code should be {int}")
//    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
//        // Assert the response status code
//        Assert.assertEquals(expectedStatusCode, response.getStatusCode());
//    }
//
//    @And("the response body should contain the unauthorized error message")
//    public void theResponseBodyShouldContainTheUnauthorizedErrorMessage() {
//        // Assert the response body contains the unauthorized error message
////        String responseBody = response.getBody().asString();
////        Assert.assertTrue(responseBody.contains("Unauthorized"));
//    }
//}
