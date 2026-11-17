package com.allahditta.api.tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class UsersTest extends BaseApiTest {

    @Test(description = "GET /users returns 10 users")
    public void listUsers() {
        given().spec(req).when().get("/users")
                .then().statusCode(200)
                .body("size()", equalTo(10))
                .body("id", hasItems(1, 10));
    }

    @Test(description = "GET /users/1 matches the JSON schema")
    public void userSchema() {
        given().spec(req).when().get("/users/1")
                .then().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"))
                .body("username", equalTo("Bret"))
                .body("address.geo.lat", notNullValue());
    }

    @Test(description = "GET /users/1/todos returns that user's todos only")
    public void userTodos() {
        given().spec(req).when().get("/users/1/todos")
                .then().statusCode(200)
                .body("userId", everyItem(equalTo(1)))
                .body("completed", everyItem(instanceOf(Boolean.class)));
    }
}
