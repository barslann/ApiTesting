package StepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.product.ProductCreateRequest;
import model.product.ProductResponse;
import org.junit.Assert;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ProductStepDefinitions {

    private RequestSpecification request;
    private Response response;
    private Long productId;
    private ProductCreateRequest productCreateRequest;
    private ProductResponse productResponse;
  //  private final String  bearerToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huX2RvZTE1IiwiZXhwIjoxNjg1MDY4MDAxLCJpYXQiOjE2ODUwNjQzNzJ9.zVW1SBKK9ma83WCZKhSL3Tc4QT3wfNG78SeZDQoAenluGVH7YSsBoFkx-Bx7DXaiB4Z3Rb6U0WT-I-J418uP0Q";


    @Given("a product creation request with valid data")
    public void aProductCreationRequestWithValidData() {
        // Prepare the request body with valid product data
        productCreateRequest = new ProductCreateRequest();
        productCreateRequest.setProductName("Example Product7");
        productCreateRequest.setDescription("Example Description7");
        productCreateRequest.setAuthorName("John Doe7");
        productCreateRequest.setPrice(9.99);
        productCreateRequest.setNumberOfPages(200);
        productCreateRequest.setCategory(Arrays.asList(1L, 2L, 3L));
        productCreateRequest.setPublisher("Example Publisher");
        productCreateRequest.setLanguage("English");
        productCreateRequest.setStock(100);
        productCreateRequest.setISBN("978-0-123456-78-9");
        productCreateRequest.setImageUrl("https://example.com/product-image.jpg");
        productCreateRequest.setPublishedDate(2023);
        // Set other necessary fields in the productCreateRequest object
    }

    @When("the request is made to the \\/addProduct endpoint")
    public void theRequestIsMadeToTheAddProductEndpoint() {
        // Make the POST request to the addProduct endpoint with the productCreateRequest body
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(productCreateRequest)
                .post("http://localhost:8080/products/addProduct");
    }

    @Then("4the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        // Assert the response status code
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @And("the response body should contain the created product details")
    public void theResponseBodyShouldContainTheCreatedProductDetails() {

//        productResponse = response.as(ProductResponse.class);
//        assertNotNull(productResponse);
//        assertEquals(productId, productResponse.getId());
//        assertEquals(productCreateRequest.getProductName(), productResponse.getProductName());
//        assertEquals(productCreateRequest, productResponse.getAuthorName());
//        assertEquals(productCreateRequest, productResponse.getDescription());
//        assertEquals(productCreateRequest, productResponse.getPrice(), 0.01); // Using delta for double comparison
//        assertEquals(productCreateRequest, productResponse.getStock());
//        assertEquals(productCreateRequest, productResponse.getImageUrl());
//        assertEquals(productCreateRequest, productResponse.getISBN());
//        assertEquals(productCreateRequest, productResponse.getNumberOfPages());
//        assertEquals(productCreateRequest, productResponse.getNumberOfSales());
//        assertEquals(productCreateRequest, productResponse.getPublisher());
//        assertEquals(productCreateRequest, productResponse.getLanguage());
//        assertEquals(productCreateRequest, productResponse.isAvailable());
//        assertEquals(productCreateRequest, productResponse.getPublishedDate());
//        assertEquals(productCreateRequest, productResponse.getCreatedAt());
//        assertEquals(productCreateRequest, productResponse.getNumberOfReviews());
//        assertEquals(productCreateRequest, productResponse.getAverageRating());
//        assertEquals(productCreateRequest, productResponse.getCategories());
    }



    //Scenario2 - get product by id

    @Given("a valid product ID")
    public void aValidProductId() {
        // Set a valid product ID
       // productId = productResponse.getId(); // Replace with an actual valid product ID
        productId = 9L; // Replace with an actual valid product ID
    }

    @When("the request is made to the productID endpoint")
    public void theRequestIsMadeToTheGetProductByIdEndpoint() {
        // Make the GET request to the getProductById endpoint with the productId path parameter
        response = RestAssured.given().contentType(ContentType.JSON).log().all().get("http://localhost:8080/products/getProductById/{productId}",productId);
        response.prettyPrint();

    }

    @Then("the response body should contain the product details")
    public void theResponseBodyShouldContainTheProductDetails() {
        // Assert that the response body contains the expected product details
        productResponse = response.as(ProductResponse.class);
        Assert.assertNotNull(productResponse);
        assertEquals(productId, productResponse.getId());
        assertEquals(productCreateRequest.getProductName(),productResponse.getProductName());
        // Add assertions for other relevant fields
    }

//    @Given("an existing product")
//    public void anExistingProduct() {
//        // Create an existing product in the system and obtain its product ID
//        // Set the productId variable with the ID of the existing product
//    }
//
//    @When("the request is made to the /updateProduct/{productId} endpoint")
//    public void theRequestIsMadeToTheUpdateProductEndpoint() {
//        // Prepare the request body with updated product data
//        ProductCreateRequest updatedRequest = new ProductCreateRequest();
//        updatedRequest.setDescription("Updated Description");
//        updatedRequest.setPrice(19.99);
//        // Set other necessary fields in the updatedRequest object
//
//        // Make the PUT request to
//        response = RestAssured.given()
//                .contentType(ContentType.JSON)
//                .header("Authorization", "Bearer " + bearerToken)
//                .body(updatedRequest)
//                .put("http://localhost:8080/products/updateProduct/{productId}",productId );
//    }
//
//    @And("the response body should contain the success message")
//    public void theResponseBodyShouldContainTheSuccessMessage() {
//        // Assert that the response body contains the success message
//      //  String responseBody = response.getBody().asString();
//       // assertEquals("Product updated successfully", responseBody);
//    }
//
//    @Given("an existing product ID")
//    public void anExistingProductId() {
//        // Set an existing product ID for deletion
//      //  productId = 123; // Replace with an actual existing product ID
//    }
//
//    @When("the request is made to the /deleteProduct/{productId} endpoint")
//    public void theRequestIsMadeToTheDeleteProductEndpoint() {
//        // Make the DELETE request to the deleteProduct endpoint with the productId path parameter
//        response = RestAssured.given().header("Authorization", "Bearer " + bearerToken).delete("http://localhost:8080/products/deleteProduct/{productId}", productId);
//    }


}