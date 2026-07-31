package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.TestData;

public class CheckoutCompletePage {

    private static final String COMPLETE_URL = "/checkout-complete.html";
    private static final String TITLE = ".title";
    private static final String COMPLETE_HEADER = ".complete-header";
    private static final String COMPLETE_TEXT = ".complete-text";
    private static final String BACK_HOME_BUTTON = "#back-to-products";
    private static final String SHOPPING_CART_BADGE = ".shopping_cart_badge";

    WebDriver driver;
    WebDriverWait wait;

    public CheckoutCompletePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void waitForCompletePage() {
        wait.until(ExpectedConditions.urlContains(COMPLETE_URL));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(TITLE)));
    }

    public boolean isCheckoutCompletePageDisplayed() {
        waitForCompletePage();
        return driver.findElement(By.cssSelector(TITLE)).getText().equals(TestData.CHECKOUT_COMPLETE_TITLE);
    }

    public String getCompletionMessage() {
        return driver.findElement(By.cssSelector(COMPLETE_HEADER)).getText();
    }

    public String getCompletionDetails() {
        return driver.findElement(By.cssSelector(COMPLETE_TEXT)).getText();
    }

    public boolean isShoppingCartBadgeVisible() {
        return !driver.findElements(By.cssSelector(SHOPPING_CART_BADGE)).isEmpty();
    }

    public void clickBackHome() {
        driver.findElement(By.cssSelector(BACK_HOME_BUTTON)).click();
        wait.until(ExpectedConditions.urlContains("/inventory.html"));
    }
}
