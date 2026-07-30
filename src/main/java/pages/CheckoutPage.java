package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    private static final String FIRST_NAME_FIELD = "first-name";
    private static final String LAST_NAME_FIELD = "last-name";
    private static final String POSTAL_CODE_FIELD = "postal-code";
    private static final String CONTINUE_BUTTON = "continue";
    private static final String ERROR_MESSAGE = "[data-test='error']";
    private static final String CHECKOUT_OVERVIEW_TITLE = ".title";
    private static final String CHECKOUT_OVERVIEW_URL = "/checkout-step-two.html";

    WebDriver driver;
    WebDriverWait wait;

    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void waitForCheckoutInformationPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(FIRST_NAME_FIELD)));
    }

    public void fillFirstName(String firstName) {
        driver.findElement(By.id(FIRST_NAME_FIELD)).sendKeys(firstName);
    }

    public void fillLastName(String lastName) {
        driver.findElement(By.id(LAST_NAME_FIELD)).sendKeys(lastName);
    }

    public void fillPostalCode(String postalCode) {
        driver.findElement(By.id(POSTAL_CODE_FIELD)).sendKeys(postalCode);
    }

    public void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        fillFirstName(firstName);
        fillLastName(lastName);
        fillPostalCode(postalCode);
    }

    public void continueCheckout() {
        driver.findElement(By.id(CONTINUE_BUTTON)).click();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ERROR_MESSAGE))).getText();
    }

    public boolean isCheckoutOverviewPageDisplayed() {
        wait.until(ExpectedConditions.urlContains(CHECKOUT_OVERVIEW_URL));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CHECKOUT_OVERVIEW_TITLE)))
                .getText()
                .equals("Checkout: Overview");
    }
}
