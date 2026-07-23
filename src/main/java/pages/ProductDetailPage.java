package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailPage {

    private static final String productDetailName = ".inventory_details_name.large_size";
    private static final String productDetailDescription = ".inventory_details_desc.large_size";
    private static final String productDetailPrice = ".inventory_details_price";
    private static final String productDetailAddToCardButton = ".btn.btn_primary.btn_small.btn_inventory";
    private static final String backTpProductsButton = ".btn.btn_secondary.back.btn_large.inventory_details_back_button";
    private static final String inventoryItem = ".inventory_item";

    WebDriver driver;
    WebDriverWait wait;

    public ProductDetailPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public String getProductName(){
        String actualProductName = driver.findElement(By.cssSelector(this.productDetailName)).getText();
        return actualProductName;
    }

    public String getProductDetails(){
        String actualProductDetails = driver.findElement(By.cssSelector(this.productDetailDescription)).getText();
        return actualProductDetails;
    }

    public String getProductPrice(){
        String actualProductPrice = driver.findElement(By.cssSelector(this.productDetailPrice)).getText();
        return actualProductPrice;
    }

    public void verifyAddToCartButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(productDetailAddToCardButton)));
    }

    public void navigateToHomePage(){
        driver.findElement(By.cssSelector(backTpProductsButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(inventoryItem)));
    }

}
