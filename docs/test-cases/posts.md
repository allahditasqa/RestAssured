# Posts - Test Cases

**Module:** Posts
**Automation:** `src/test/java/com/allahditta/api/tests/PostsCrudTest.java`  |  **Report:** `reports/posts/Posts test <DD-MM-YYYY>_<HH-mm-ss>.html` (all reports: `reports/index.html`)
**Service:** https://jsonplaceholder.typicode.com

## Overview
CRUD and schema checks of the `/posts` resource. JSONPlaceholder is a fake API: write requests (POST, PUT, PATCH, DELETE) return a realistic answer but nothing is stored, so every case is repeatable and needs no clean-up.

## A. Read

| ID | Title | Priority | Type | Preconditions | Test data | Steps | Expected result | Automated in |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| TC_POSTS_01 | GET /posts returns status 200 and 100 posts | High | Functional | None | None | 1. Send GET /posts. | Status 200, content type application/json, the response time is under 5 seconds and the list has 100 posts. | `src/test/java/com/allahditta/api/tests/PostsCrudTest.java::listPosts` |
| TC_POSTS_02 | GET /posts/1 matches the post JSON schema | High | Schema | None | Schema `schemas/post-schema.json` | 1. Send GET /posts/1. 2. Validate the body against the post schema. | Status 200. The body is valid against the schema, id is 1 and userId is 1. | `src/test/java/com/allahditta/api/tests/PostsCrudTest.java::getPostMatchesSchema` |
| TC_POSTS_03 | GET /posts?userId=1 returns 10 posts that all belong to user 1 | Medium | Functional | None | Query userId=1 | 1. Send GET /posts?userId=1. | Status 200. 10 posts are returned and every post has userId 1. | `src/test/java/com/allahditta/api/tests/PostsCrudTest.java::filterPostsByUser` |
| TC_POSTS_08 | GET /posts/1/comments returns 5 comments of post 1 with valid emails | Medium | Functional | None | Post id 1 | 1. Send GET /posts/1/comments. | Status 200. 5 comments are returned, every comment has postId 1 and every email contains "@". | `src/test/java/com/allahditta/api/tests/PostsCrudTest.java::postComments` |

## B. Write

| ID | Title | Priority | Type | Preconditions | Test data | Steps | Expected result | Automated in |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| TC_POSTS_04 | POST /posts creates a post and returns status 201 with the new id 101 | High | Functional | None | title "QA demo", body "created by REST Assured", userId 7 | 1. Send POST /posts with the JSON body. | Status 201. The answer repeats title "QA demo" and userId 7 and has the new id 101. | `src/test/java/com/allahditta/api/tests/PostsCrudTest.java::createPost` |
| TC_POSTS_05 | PUT /posts/1 replaces the post and returns status 200 | Medium | Functional | None | id 1, title "updated", body "updated body", userId 1 | 1. Send PUT /posts/1 with the JSON body. | Status 200 and the title in the answer is "updated". | `src/test/java/com/allahditta/api/tests/PostsCrudTest.java::updatePost` |
| TC_POSTS_06 | PATCH /posts/1 changes only the title and returns status 200 | Medium | Functional | None | title "patched" | 1. Send PATCH /posts/1 with the JSON body {"title": "patched"}. | Status 200. The title is "patched" and the id is still 1. | `src/test/java/com/allahditta/api/tests/PostsCrudTest.java::patchPost` |
| TC_POSTS_07 | DELETE /posts/1 returns status 200 | Medium | Functional | None | Post id 1 | 1. Send DELETE /posts/1. | Status 200. | `src/test/java/com/allahditta/api/tests/PostsCrudTest.java::deletePost` |

## Summary

| Cases | High priority | Automated |
| --- | --- | --- |
| 8 | 3 | 8 |
