# PaymentPlanAutomation

An end-to-end UI test automation project for the checkout flow of the [SauceDemo](https://www.saucedemo.com/) demo shop. It is built with Java, Selenium WebDriver and TestNG, and uses the Page Object Model to keep tests readable and easy to maintain.

## What it tests

The `PaymentPlanE2ETest` test walks through a complete purchase:

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
- Apache Maven 3.6 or newer
- Google Chrome (Selenium Manager downloads a matching ChromeDriver automatically)
- Internet access to reach `https://www.saucedemo.com/`

## Project structure

```
PaymentPlanAutomation
├── pom.xml
└── src
    ├── main/java/com/payment/pages     # Page objects
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

> **Note:** This file is listed in `.gitignore`, so your local values are not meant to be committed. Never commit real credentials to the repository.

## Running the tests

Run all tests:

```bash
mvn test
```

Run a single test class:

```bash
mvn -Dtest=PaymentPlanE2ETest test
```

You can also run `PaymentPlanE2ETest` directly from IntelliJ IDEA by clicking the run icon next to the class or test method.

Chrome opens a maximized window for each test and closes it when the test finishes, even if the test fails. Surefire writes its results to `target/surefire-reports/`.

## How it works

- **Page Object Model:** each page of the shop has its own class holding its locators and actions, so tests call methods like `loginPage.login(...)` instead of using Selenium directly.
- **`BaseTest`:** a TestNG `@BeforeMethod` launches Chrome before each test, and an `@AfterMethod(alwaysRun = true)` quits it afterwards.
- **`ConfigReader`:** loads `config.properties` once and returns values by key.

## Possible improvements

- Add explicit waits (`WebDriverWait`) for more stable tests
- Move the base URL and test data into configuration
- Parametrize product selection instead of hard-coding the backpack
- Capture a screenshot when a test fails
- Add more test cases (invalid login, empty cart, missing checkout details)
