# Users - Test Cases

**Module:** Users
**Automation:** `src/test/java/com/allahditta/api/tests/UsersTest.java`  |  **Report:** `reports/users/Users test <DD-MM-YYYY>_<HH-mm-ss>.html` (all reports: `reports/index.html`)
**Service:** https://jsonplaceholder.typicode.com

## Overview
Read checks of the `/users` resource, including a nested object (address and geo position) and the todos of one user.

## A. Read

| ID | Title | Priority | Type | Preconditions | Test data | Steps | Expected result | Automated in |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| TC_USERS_01 | GET /users returns 10 users including ids 1 and 10 | High | Functional | None | None | 1. Send GET /users. | Status 200. The list has 10 users and the ids contain 1 and 10. | `src/test/java/com/allahditta/api/tests/UsersTest.java::listUsers` |
| TC_USERS_02 | GET /users/1 matches the user JSON schema | High | Schema | None | Schema `schemas/user-schema.json` | 1. Send GET /users/1. 2. Validate the body against the user schema. | Status 200. The body is valid against the schema, the username is "Bret" and address.geo.lat is not empty. | `src/test/java/com/allahditta/api/tests/UsersTest.java::userSchema` |
| TC_USERS_03 | GET /users/1/todos returns only todos of user 1 | Medium | Functional | None | User id 1 | 1. Send GET /users/1/todos. | Status 200. Every todo has userId 1 and its "completed" value is true or false. | `src/test/java/com/allahditta/api/tests/UsersTest.java::userTodos` |

## Summary

| Cases | High priority | Automated |
| --- | --- | --- |
| 3 | 2 | 3 |
