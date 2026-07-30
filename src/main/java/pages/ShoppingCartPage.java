package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCartPage {

    private static final String URL = "/cart.html";
    private static final String CHECKOUT_BUTTON = ".btn.btn_action.btn_medium.checkout_button";
    private static final String SHOPPING_CART_BUTTON = ".shopping_cart_link";
    private static final String CART_ITEM = ".cart_item";
    private static final String CART_ITEM_NAME = ".inventory_item_name";
    private static final String REMOVE_BUTTON = ".cart_button";
    private static final String SHOPPING_CART_BADGE = ".shopping_cart_badge";

    WebDriver driver;
    WebDriverWait wait;

    public ShoppingCartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void accessCartPageByUrl() {
        driver.get(LoginPage.MAIN_URL + URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CHECKOUT_BUTTON)));
    }

    public void accessCartPageByButton() {
        driver.findElement(By.cssSelector(SHOPPING_CART_BUTTON)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CHECKOUT_BUTTON)));
    }

    public boolean isProductDisplayedInCart(String productName) {
        return driver.findElements(By.cssSelector(CART_ITEM)).stream()
                .map(product -> product.findElement(By.cssSelector(CART_ITEM_NAME)).getText())
                .anyMatch(actualProductName -> actualProductName.equals(productName));
    }

    public void removeProductFromCart(String productName) {
        for (WebElement product : driver.findElements(By.cssSelector(CART_ITEM))) {
            String actualProductName = product.findElement(By.cssSelector(CART_ITEM_NAME)).getText();
            if (actualProductName.equals(productName)) {
                product.findElement(By.cssSelector(REMOVE_BUTTON)).click();
                wait.until(driver -> !isProductDisplayedInCart(productName));
                return;
            }
        }

        throw new IllegalArgumentException("Product was not found in cart: " + productName);
    }

    public boolean isShoppingCartBadgeDisplayed() {
        return !driver.findElements(By.cssSelector(SHOPPING_CART_BADGE)).isEmpty();
    }
}
