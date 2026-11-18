package com.allahditta.api.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class NegativeTest extends BaseApiTest {

    /** The first value is the test case ID (TC_NEGATIVE_01 .. TC_NEGATIVE_04), read by the report listener. */
    @DataProvider(name = "missingResources")
    public Object[][] missingResources() {
        return new Object[][]{
                {"TC_NEGATIVE_01", "/posts/9999"},
                {"TC_NEGATIVE_02", "/users/9999"},
                {"TC_NEGATIVE_03", "/comments/9999"},
                {"TC_NEGATIVE_04", "/albums/9999"},
        };
    }

    @Test(dataProvider = "missingResources", description = "TC_NEGATIVE_01..04 - Unknown ids return 404 and an empty object")
    public void unknownIdReturns404(String testCaseId, String path) {
        given().spec(req).when().get(path)
                .then().statusCode(404)
                .body("size()", equalTo(0));
    }

    @Test(description = "TC_NEGATIVE_05 - Unknown route returns 404")
    public void unknownRoute() {
        given().spec(req).when().get("/does-not-exist")
                .then().statusCode(404);
    }

    @Test(description = "TC_NEGATIVE_06 - Filtering with no match returns status 200 and an empty list")
    public void emptyFilterResult() {
        given().spec(req).queryParam("userId", 9999)
                .when().get("/posts")
                .then().statusCode(200)
                .body("size()", equalTo(0));
    }

    @Test(description = "TC_NEGATIVE_07 - A non-numeric id returns 404")
    public void nonNumericId() {
        given().spec(req).when().get("/posts/abc")
                .then().statusCode(404);
    }
}
