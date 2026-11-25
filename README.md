# PaymentPlanAutomation

An end-to-end UI test automation project for the checkout flow of the [SauceDemo](https://www.saucedemo.com/) demo shop. It is built with Java, Selenium WebDriver and TestNG, and uses the Page Object Model with explicit waits to keep tests readable and stable.

## What it tests

`PaymentPlanE2ETest` walks through a complete purchase:

| Step | Action | Verification |
|------|--------|--------------|
| 1 | Log in with the configured credentials | URL contains `inventory.html` |
| 2 | Add "Sauce Labs Backpack" to the cart and open the cart | Backpack is listed in the cart |
| 3 | Proceed to checkout and enter customer details | URL contains `checkout-step-two.html` |
| 4 | Finish the order | URL contains `checkout-complete.html` |
| 5 | Read the confirmation message | Message equals "Thank you for your order!" |

## Tech stack

- **Java 17**
- **Maven** (build and dependency management)
- **Selenium WebDriver 4.27.0**
- **TestNG 7.10.2**
- **Maven Surefire Plugin 3.5.2** (test execution)

## Prerequisites

- JDK 17 or newer
- Apache Maven 3.6 or newer, available as `mvn` in your terminal
- Google Chrome (Selenium Manager downloads a matching ChromeDriver automatically)
- Internet access to reach `https://www.saucedemo.com/`

## Project structure

```
PaymentPlanAutomation
├── pom.xml
└── src
    ├── main/java/com/payment/pages     # Page objects
    │   ├── BasePage.java               # Shared driver and explicit wait
    │   ├── LoginPage.java
    │   ├── ProductsPage.java
    │   ├── CartPage.java
    │   └── CheckoutPage.java
    └── test
        ├── java/com/payment/tests      # Test infrastructure and tests
        │   ├── BaseTest.java           # Starts and stops the browser for each test
        │   ├── ConfigReader.java       # Reads values from config.properties
        │   └── PaymentPlanE2ETest.java # The end-to-end checkout test
        └── resources
            └── config.properties       # Login credentials
```

## Configuration

Test credentials are read from `src/test/resources/config.properties`:

```properties
username=your_username_here
password=your_password_here
```

Replace the placeholder values before running the tests. SauceDemo publishes its demo accounts on its login page; `standard_user` is the standard one.

> **Note:** Never commit real credentials to the repository. Keep your local values out of version control.

## Running the tests

Run from the project root (the folder containing `pom.xml`).

Run all tests with a visible browser:

```bash
mvn test
```

Run in headless mode (no browser window):

```bash
mvn test -Dheadless
```

Run a single test class:

```bash
mvn test "-Dtest=PaymentPlanE2ETest"
```

You can also run `PaymentPlanE2ETest` directly from IntelliJ IDEA by clicking the run icon next to the class or test method.

A successful run ends with:

```
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

Detailed reports are written to `target/surefire-reports/`.

## How it works

- **Page Object Model:** each page of the shop has its own class holding its locators and actions, so the test calls methods like `loginPage.login(...)` instead of using Selenium directly.
- **Explicit waits:** every page class extends `BasePage`, which provides the shared `driver` and a `WebDriverWait` with a 10-second timeout. Pages wait for a specific condition (an element to be visible or clickable, or the URL to change) before interacting, instead of assuming the page is ready. The URL and cart checks return `false` on timeout so assertion messages stay readable.
- **`BaseTest`:** a TestNG `@BeforeMethod` launches Chrome before each test, and an `@AfterMethod(alwaysRun = true)` quits it afterwards, even when the test fails.
- **Headless mode:** `BaseTest` reads the `headless` system property. When it is set, Chrome starts with `--headless=new` and a fixed 1920x1080 window; otherwise the window is maximized.
- **`ConfigReader`:** loads `config.properties` once and returns values by key.

## Troubleshooting

- **`'mvn' is not recognized`:** Maven is not on your `PATH`. Install it, add its `bin` folder to `PATH`, then restart the terminal and check with `mvn -v`. Alternatively, run Maven goals from IntelliJ's Maven panel.
- **`release version 17 not supported`:** your JDK is older than 17. Check with `java -version`.
- **`SLF4J: Failed to load class ... StaticLoggerBinder`:** harmless. No logging backend is on the classpath, so Selenium's logs are discarded.
- **`Unable to find CDP implementation matching ...`:** harmless for this project. It means your Chrome is newer than what this Selenium version was built against. Updating the Selenium version in `pom.xml` removes it.
- **A test passes normally but fails headless:** check the failure message first. Headless mode uses a fixed window size, and layout-dependent elements can behave differently.

## Possible improvements

- Move the base URL and test data into configuration
- Parametrize product selection instead of hard-coding the backpack
- Capture a screenshot when a test fails
- Add more test cases (invalid login, empty cart, missing checkout details)
- Update Selenium to the latest 4.x release and add a logging backend such as `slf4j-simple`
