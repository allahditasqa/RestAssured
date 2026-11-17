package com.allahditta.api.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import static org.hamcrest.Matchers.lessThan;

public abstract class BaseApiTest {
    protected static RequestSpecification req;
    protected static ResponseSpecification fastResponse;

    @BeforeClass(alwaysRun = true)
    public void configure() {
        String base = System.getProperty("baseUrl", "https://jsonplaceholder.typicode.com");
        RestAssured.baseURI = base;
        req = new RequestSpecBuilder()
                .setBaseUri(base)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
        fastResponse = new ResponseSpecBuilder()
                .expectResponseTime(lessThan(5000L))
                .build();
    }
}
