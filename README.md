# Selenium Java Automation Framework
A personal QA Automation project built with Java, Selenium WebDriver, Maven and TestNG.

## Technologies
- Java 17
- Selenium WebDriver
- Maven
- TestNG
- ChromeDriver

## Features
- UI Automation
- Explicit Waits
- Page Object Model
- BaseTest implementation

## Project Structure

src/main/java
- actions
- selectors

src/test/java
- ui
- base

## Run tests

Run all tests

```bash
mvn test
```

Run a specific test

```bash
mvn test -Dtest=LoginTest
```

## Website under test
https://www.saucedemo.com