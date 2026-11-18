# Negative - Test Cases

**Module:** Negative
**Automation:** `src/test/java/com/allahditta/api/tests/NegativeTest.java`  |  **Report:** `reports/negative/Negative test <DD-MM-YYYY>_<HH-mm-ss>.html` (all reports: `reports/index.html`)
**Service:** https://jsonplaceholder.typicode.com

## Overview
Requests for things that do not exist, and requests that match nothing. TC_NEGATIVE_01 to 04 are one data-driven test (four ids); the first value of every data row is the test case ID.

## A. Missing resources

| ID | Title | Priority | Type | Preconditions | Test data | Steps | Expected result | Automated in |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| TC_NEGATIVE_01 | GET /posts/9999 returns status 404 and an empty object | High | Negative | None | Post id 9999 | 1. Send GET /posts/9999. | Status 404 and the body is an empty object. | `src/test/java/com/allahditta/api/tests/NegativeTest.java::unknownIdReturns404` |
| TC_NEGATIVE_02 | GET /users/9999 returns status 404 and an empty object | High | Negative | None | User id 9999 | 1. Send GET /users/9999. | Status 404 and the body is an empty object. | `src/test/java/com/allahditta/api/tests/NegativeTest.java::unknownIdReturns404` |
| TC_NEGATIVE_03 | GET /comments/9999 returns status 404 and an empty object | Medium | Negative | None | Comment id 9999 | 1. Send GET /comments/9999. | Status 404 and the body is an empty object. | `src/test/java/com/allahditta/api/tests/NegativeTest.java::unknownIdReturns404` |
| TC_NEGATIVE_04 | GET /albums/9999 returns status 404 and an empty object | Medium | Negative | None | Album id 9999 | 1. Send GET /albums/9999. | Status 404 and the body is an empty object. | `src/test/java/com/allahditta/api/tests/NegativeTest.java::unknownIdReturns404` |
| TC_NEGATIVE_05 | GET /does-not-exist returns status 404 | High | Negative | None | Path /does-not-exist | 1. Send GET /does-not-exist. | Status 404. | `src/test/java/com/allahditta/api/tests/NegativeTest.java::unknownRoute` |

## B. Boundary

| ID | Title | Priority | Type | Preconditions | Test data | Steps | Expected result | Automated in |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| TC_NEGATIVE_06 | Filtering posts with userId=9999 returns status 200 and an empty list | Medium | Boundary | None | Query userId=9999 | 1. Send GET /posts?userId=9999. | Status 200 (not an error) and the list is empty. | `src/test/java/com/allahditta/api/tests/NegativeTest.java::emptyFilterResult` |
| TC_NEGATIVE_07 | GET /posts/abc with a non-numeric id returns status 404 | Medium | Negative | None | Post id "abc" | 1. Send GET /posts/abc. | Status 404. | `src/test/java/com/allahditta/api/tests/NegativeTest.java::nonNumericId` |

## Summary

| Cases | High priority | Automated |
| --- | --- | --- |
| 7 | 3 | 7 |
