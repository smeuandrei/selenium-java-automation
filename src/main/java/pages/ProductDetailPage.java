package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailPage {

    private static final String PRODUCT_NAME = ".inventory_details_name.large_size";
    private static final String PRODUCT_DESCRIPTION = ".inventory_details_desc.large_size";
    private static final String PRODUCT_PRICE = ".inventory_details_price";
    private static final String ADD_TO_CART_BUTTON = ".btn.btn_primary.btn_small.btn_inventory";
    private static final String REMOVE_FROM_CART_BUTTON = ".btn.btn_secondary.btn_small.btn_inventory";
    private static final String RETURN_TO_PRODUCTS_BUTTON = ".btn.btn_secondary.back.btn_large.inventory_details_back_button";
    private static final String INVENTORY_ITEM = ".inventory_item";
    private static final String SHOPPING_BADGE = ".shopping_cart_badge";

    WebDriver driver;
    WebDriverWait wait;

    public ProductDetailPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public String getProductName(){
        String actualProductName = driver.findElement(By.cssSelector(PRODUCT_NAME)).getText();
        return actualProductName;
    }

    public String getProductDetails(){
        String actualProductDetails = driver.findElement(By.cssSelector(PRODUCT_DESCRIPTION)).getText();
        return actualProductDetails;
    }

    public String getProductPrice(){
        String actualProductPrice = driver.findElement(By.cssSelector(PRODUCT_PRICE)).getText();
        return actualProductPrice;
    }

    public void verifyAddToCartButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ADD_TO_CART_BUTTON)));
    }

    public void addCurrentProductToCart(){
        driver.findElement(By.cssSelector(ADD_TO_CART_BUTTON)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(SHOPPING_BADGE)));
    }

    public void removeProductFromCart(){
        driver.findElement(By.cssSelector(REMOVE_FROM_CART_BUTTON)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ADD_TO_CART_BUTTON)));
    }

    public void navigateToHomePage(){
        driver.findElement(By.cssSelector(RETURN_TO_PRODUCTS_BUTTON)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(INVENTORY_ITEM)));
    }
}
