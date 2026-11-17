package com.allahditta.api.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class NegativeTest extends BaseApiTest {

    @DataProvider(name = "missingResources")
    public Object[][] missingResources() {
        return new Object[][]{
                {"/posts/9999"},
                {"/users/9999"},
                {"/comments/9999"},
                {"/albums/9999"},
        };
    }

    @Test(dataProvider = "missingResources", description = "Unknown ids return 404 and an empty object")
    public void unknownIdReturns404(String path) {
        given().spec(req).when().get(path)
                .then().statusCode(404)
                .body("size()", equalTo(0));
    }

    @Test(description = "Unknown route returns 404")
    public void unknownRoute() {
        given().spec(req).when().get("/does-not-exist")
                .then().statusCode(404);
    }

    @Test(description = "Filtering with no match returns an empty list, not an error")
    public void emptyFilterResult() {
        given().spec(req).queryParam("userId", 9999)
                .when().get("/posts")
                .then().statusCode(200)
                .body("size()", equalTo(0));
    }

    @Test(description = "Non-numeric id is not found")
    public void nonNumericId() {
        given().spec(req).when().get("/posts/abc")
                .then().statusCode(404);
    }
}
