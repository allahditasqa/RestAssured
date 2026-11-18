# Test strategy: JSONPlaceholder REST API

**Version:** 1.0  |  **Prepared by:** QA (SDET)

## 1. Objective
Show a clean, repeatable API test approach with REST Assured and TestNG: status codes, response contract (JSON schema), CRUD behaviour and negative cases. The target is a public fake API, so anyone can run the suite without accounts or secrets.

## 2. Application under test

| Service | URL | Notes |
| --- | --- | --- |
| JSONPlaceholder | https://jsonplaceholder.typicode.com | Public fake REST API. Writes are accepted and echoed back but **not stored**. |

## 3. Scope

| Module | Document | Cases | Type |
| --- | --- | --- | --- |
| Posts | [posts.md](test-cases/posts.md) | 8 | Functional, Schema |
| Users | [users.md](test-cases/users.md) | 3 | Functional, Schema |
| Negative | [negative.md](test-cases/negative.md) | 7 | Negative, Boundary |

**Out of scope:** authentication (the API has none), load and performance (see my JMeter and k6 repositories), data persistence (the service does not store writes), albums/photos resources beyond the 404 check.

## 4. Approach

| Check | How |
| --- | --- |
| Status codes and headers | REST Assured `statusCode`, `contentType` |
| Response contract | JSON Schema validation (`schemas/post-schema.json`, `schemas/user-schema.json`) |
| Business data | Body assertions with Hamcrest matchers (sizes, ids, filters, nested fields) |
| Negative and boundary | Unknown ids, unknown route, empty filter result, non-numeric id; data-driven with a TestNG data provider |
| Response time | A shared response specification: under 5 seconds |

Shared request and response specifications live in `BaseApiTest`; the base URL can be overridden with `-DbaseUrl=...`.

## 5. Environment and data
- Java 17, Maven 3.9, TestNG 7.10, REST Assured 5.5. No secrets, no accounts.
- All data is fixed in the tests (ids 1, 7, 9999 ...). Nothing is created that needs clean-up.

## 6. Entry and exit criteria
- Entry: the public service is reachable.
- Exit: every High priority case passes and the whole suite passes (the suite is small and deterministic).

## 7. Risks

| Risk | Impact | Mitigation |
| --- | --- | --- |
| Public service is slow or down | Red run that is not a product bug | Re-run; response-time limit is generous (5 s) |
| The fake API changes sample data | Wrong expected values | Values are checked in one place per case; update the document and the test together |

## 8. Deliverables
Test case documents and master CSVs (`docs/test-cases`), automation code, a module-wise HTML report and run CSV for every run (`reports/`, written by the TestNG listener), a traceability check (`node tools/docs.js check`) and a CI pipeline.
