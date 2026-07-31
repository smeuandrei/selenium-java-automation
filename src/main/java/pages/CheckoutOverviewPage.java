package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.TestData;

public class CheckoutOverviewPage {

    private static final String OVERVIEW_URL = "/checkout-step-two.html";
    private static final String TITLE = ".title";
    private static final String CART_ITEM = ".cart_item";
    private static final String ITEM_NAME = ".inventory_item_name";
    private static final String ITEM_PRICE = ".inventory_item_price";
    private static final String SUBTOTAL = ".summary_subtotal_label";
    private static final String TAX = ".summary_tax_label";
    private static final String TOTAL = ".summary_total_label";
    private static final String CANCEL_BUTTON = "#cancel";
    private static final String FINISH_BUTTON = "#finish";

    WebDriver driver;
    WebDriverWait wait;

    public CheckoutOverviewPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void waitForOverviewPage() {
        wait.until(ExpectedConditions.urlContains(OVERVIEW_URL));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(TITLE)));
    }

    public boolean isOverviewPageDisplayed() {
        waitForOverviewPage();
        return driver.findElement(By.cssSelector(TITLE)).getText().equals(TestData.CHECKOUT_OVERVIEW_TITLE);
    }

    public boolean isProductDisplayed(String productName) {
        int totalCartItemNumber = driver.findElements(By.cssSelector(CART_ITEM)).size();
        for (int i = 0; i < totalCartItemNumber; i++) {
            String actualProductName = driver.findElements(By.cssSelector(CART_ITEM)).get(i)
                    .findElement(By.cssSelector(ITEM_NAME)).getText();
            if (actualProductName.equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isProductPriceDisplayed(String productPrice) {
        int totalCartItemNumber = driver.findElements(By.cssSelector(CART_ITEM)).size();
        for (int i = 0; i < totalCartItemNumber; i++) {
            String actualProductPrice = driver.findElements(By.cssSelector(CART_ITEM)).get(i)
                    .findElement(By.cssSelector(ITEM_PRICE)).getText();
            if (actualProductPrice.equals(productPrice)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAllProductsDisplayed(String firstProductName, String secondProductName) {
        return isProductDisplayed(firstProductName) && isProductDisplayed(secondProductName);
    }

    public boolean isAllProductPricesDisplayed(String firstProductPrice, String secondProductPrice) {
        return isProductPriceDisplayed(firstProductPrice) && isProductPriceDisplayed(secondProductPrice);
    }

    public String getItemSubtotal() {
        return driver.findElement(By.cssSelector(SUBTOTAL)).getText();
    }

    public String getTax() {
        return driver.findElement(By.cssSelector(TAX)).getText();
    }

    public String getTotal() {
        return driver.findElement(By.cssSelector(TOTAL)).getText();
    }

    public void clickCancel() {
        driver.findElement(By.cssSelector(CANCEL_BUTTON)).click();
        wait.until(ExpectedConditions.urlContains("/inventory.html"));
    }

    public void clickFinish() {
        driver.findElement(By.cssSelector(FINISH_BUTTON)).click();
        wait.until(ExpectedConditions.urlContains("/checkout-complete.html"));
    }

    public boolean isCheckoutCompletePageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(TITLE)))
                .getText()
                .equals(TestData.CHECKOUT_COMPLETE_TITLE);
    }

    public String getConfirmationMessage() {
        return driver.findElement(By.cssSelector(".complete-header")).getText();
    }

    public String getThankYouMessage() {
        return driver.findElement(By.cssSelector(".complete-text")).getText();
    }
}
