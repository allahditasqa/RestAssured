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
├── src/test/java/com/allahditta/report/ReportListener.java   # TestNG listener -> module-wise reports
├── src/test/resources/schemas/   # JSON Schemas (post, user)
├── docs/                         # TEST_CASE_STANDARD.md, TEST_STRATEGY.md, test-cases/ (md + master CSV)
├── tools/docs.js                 # CSV generator + traceability check (Node 18+, no packages)
├── reports/                      # reports of a real run (index.html, history.json, one folder per module)
└── .github/workflows/ci.yml
```

## Prerequisites
JDK 17+ and Maven 3.9+.

## Run
```bash
mvn test
mvn test -DbaseUrl=https://jsonplaceholder.typicode.com   # override base URL
```

## Test cases
Every test has a documented case with the same ID, in the standard described in [docs/TEST_CASE_STANDARD.md](docs/TEST_CASE_STANDARD.md) (strategy: [docs/TEST_STRATEGY.md](docs/TEST_STRATEGY.md)).

| Module | Document | Master CSV | Cases |
|--------|----------|-----------|-------|
| Posts | [posts.md](docs/test-cases/posts.md) | [posts.csv](docs/test-cases/posts.csv) | 8 |
| Users | [users.md](docs/test-cases/users.md) | [users.csv](docs/test-cases/users.csv) | 3 |
| Negative | [negative.md](docs/test-cases/negative.md) | [negative.csv](docs/test-cases/negative.csv) | 7 |
| **Total** | | | **18** (all automated) |

The ID is in the test: `@Test(description = "TC_POSTS_03 - ...")` (for the data-driven test the first data value is the ID).
The Markdown documents are the source of truth. After editing them:
```bash
node tools/docs.js csv      # regenerate the master CSVs
node tools/docs.js check    # fails when documents, CSVs and code IDs drift apart (also runs in CI)
```

## Reports
`mvn test` runs the `ReportListener` (registered in `testng.xml`) and writes module-wise reports of the real run - never edited by hand, old runs are kept:
```
reports/
├── index.html            # history, newest run first
├── history.json
└── posts/  users/  negative/
    ├── <Module> test <DD-MM-YYYY>_<HH-mm-ss>.html         # self-contained report, one card per test case
    └── <Module> test cases <DD-MM-YYYY>_<HH-mm-ss>.csv     # steps, expected result and the REAL result
```
Latest committed run: [reports/index.html](reports/index.html) (download or clone, GitHub shows HTML as text). The report of a failed test shows the assertion error. TestNG's own raw output is still in `target/surefire-reports/`.
