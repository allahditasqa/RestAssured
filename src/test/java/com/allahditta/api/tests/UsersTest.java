package com.allahditta.api.tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class UsersTest extends BaseApiTest {

    @Test(description = "TC_USERS_01 - GET /users returns 10 users including ids 1 and 10")
    public void listUsers() {
        given().spec(req).when().get("/users")
                .then().statusCode(200)
                .body("size()", equalTo(10))
                .body("id", hasItems(1, 10));
    }

    @Test(description = "TC_USERS_02 - GET /users/1 matches the user JSON schema")
    public void userSchema() {
        given().spec(req).when().get("/users/1")
                .then().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"))
                .body("username", equalTo("Bret"))
                .body("address.geo.lat", notNullValue());
    }

    @Test(description = "TC_USERS_03 - GET /users/1/todos returns only todos of user 1")
    public void userTodos() {
        given().spec(req).when().get("/users/1/todos")
                .then().statusCode(200)
                .body("userId", everyItem(equalTo(1)))
                .body("completed", everyItem(instanceOf(Boolean.class)));
    }
}
