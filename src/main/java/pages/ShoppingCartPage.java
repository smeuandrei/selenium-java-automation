package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCartPage {

    private static final String URL = "/cart.html";
    private static final String CHECKOUT_BUTTON = ".btn.btn_action.btn_medium.checkout_button";
    private static final String SHOPPING_CART_BUTTON = ".shopping_cart_link";

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
}
