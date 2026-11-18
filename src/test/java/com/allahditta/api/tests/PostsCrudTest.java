package com.allahditta.api.tests;

import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

/**
 * CRUD + schema tests for /posts.
 * Note: JSONPlaceholder is a fake API - writes are accepted and echoed back but not persisted.
 */
public class PostsCrudTest extends BaseApiTest {

    @Test(description = "TC_POSTS_01 - GET /posts returns status 200 and 100 posts")
    public void listPosts() {
        given().spec(req).when().get("/posts")
                .then().spec(fastResponse)
                .statusCode(200)
                .contentType("application/json")
                .body("size()", equalTo(100));
    }

    @Test(description = "TC_POSTS_02 - GET /posts/1 matches the post JSON schema")
    public void getPostMatchesSchema() {
        given().spec(req).when().get("/posts/1")
                .then().statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/post-schema.json"))
                .body("id", equalTo(1))
                .body("userId", equalTo(1));
    }

    @Test(description = "TC_POSTS_03 - GET /posts?userId=1 returns 10 posts that all belong to user 1")
    public void filterPostsByUser() {
        given().spec(req).queryParam("userId", 1)
                .when().get("/posts")
                .then().statusCode(200)
                .body("size()", equalTo(10))
                .body("userId", everyItem(equalTo(1)));
    }

    @Test(description = "TC_POSTS_04 - POST /posts creates a post and returns status 201 with the new id 101")
    public void createPost() {
        Map<String, Object> body = Map.of("title", "QA demo", "body", "created by REST Assured", "userId", 7);
        given().spec(req).body(body)
                .when().post("/posts")
                .then().statusCode(201)
                .body("id", equalTo(101))
                .body("title", equalTo("QA demo"))
                .body("userId", equalTo(7));
    }

    @Test(description = "TC_POSTS_05 - PUT /posts/1 replaces the post and returns status 200")
    public void updatePost() {
        Map<String, Object> body = Map.of("id", 1, "title", "updated", "body", "updated body", "userId", 1);
        given().spec(req).body(body)
                .when().put("/posts/1")
                .then().statusCode(200)
                .body("title", equalTo("updated"));
    }

    @Test(description = "TC_POSTS_06 - PATCH /posts/1 changes only the title and returns status 200")
    public void patchPost() {
        given().spec(req).body(Map.of("title", "patched"))
                .when().patch("/posts/1")
                .then().statusCode(200)
                .body("title", equalTo("patched"))
                .body("id", equalTo(1));
    }

    @Test(description = "TC_POSTS_07 - DELETE /posts/1 returns status 200")
    public void deletePost() {
        given().spec(req).when().delete("/posts/1")
                .then().statusCode(200);
    }

    @Test(description = "TC_POSTS_08 - GET /posts/1/comments returns 5 comments of post 1 with valid emails")
    public void postComments() {
        given().spec(req).when().get("/posts/1/comments")
                .then().statusCode(200)
                .body("size()", equalTo(5))
                .body("postId", everyItem(equalTo(1)))
                .body("email", everyItem(containsString("@")));
    }
}
