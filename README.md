# RestAssured

**Automation Framework:** REST Assured 5 + Java 17 + TestNG API tests against the public fake API [JSONPlaceholder](https://jsonplaceholder.typicode.com).

## What is covered
| Class | Focus |
|-------|-------|
| `PostsCrudTest` | GET list/by id/filter/nested, POST, PUT, PATCH, DELETE, JSON-schema validation, response-time spec |
| `UsersTest` | list, nested objects, JSON-schema validation, per-user todos |
| `NegativeTest` | 404 on unknown ids/routes (data-driven), empty filter result, non-numeric id |

> JSONPlaceholder is a *fake* API: write requests (POST/PUT/PATCH/DELETE) return realistic responses but nothing is persisted. The tests assert the response contract, not persistence.

## Structure
```
RestAssured/
├── pom.xml
├── testng.xml
├── src/test/java/com/allahditta/api/tests/
│   ├── BaseApiTest.java      # shared request/response specs, -DbaseUrl override
│   ├── PostsCrudTest.java
│   ├── UsersTest.java
│   └── NegativeTest.java
├── src/test/resources/schemas/   # JSON Schemas (post, user)
└── .github/workflows/ci.yml
```

## Prerequisites
JDK 17+ and Maven 3.9+.

## Run
```bash
mvn test
mvn test -DbaseUrl=https://jsonplaceholder.typicode.com   # override base URL
```
Reports: `target/surefire-reports/`.
