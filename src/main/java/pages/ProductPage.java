package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    private static final String INVENTORY_ITEM = ".inventory_item";
    private static final String PRODUCT_NAME = ".inventory_item_name";
    private static final String PRODUCT_DETAILS = ".inventory_item_desc";
    private static final String PRODUCT_PRICE = ".inventory_item_price";
    private static final String PRODUCT_FILTER = ".product_sort_container";

    WebDriver driver;
    WebDriverWait wait;

    public ProductPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void filterProducts(String filterBy){
        WebElement sortDropdown = driver.findElement(By.cssSelector(this.PRODUCT_FILTER));
        Select sort = new Select(sortDropdown);
        sort.selectByValue(filterBy);
    }

    public String getInventoryProductName(int listProductNumberIndex) {
        List<WebElement> inventoryProducts = driver.findElements(By.cssSelector(this.INVENTORY_ITEM));
        WebElement inventoryProductNumber = inventoryProducts.get(listProductNumberIndex);
        String actualInventoryProductName = inventoryProductNumber.findElement(By.cssSelector(this.PRODUCT_NAME)).getText();
        return actualInventoryProductName;
    }

    public String getInventoryProductDetails(int listProductNumberIndex) {
        List<WebElement> inventoryProducts = driver.findElements(By.cssSelector(this.INVENTORY_ITEM));
        WebElement inventoryProductNumber = inventoryProducts.get(listProductNumberIndex);
        String actualInventoryProductDetails = inventoryProductNumber.findElement(By.cssSelector(this.PRODUCT_DETAILS)).getText();
        return actualInventoryProductDetails;
    }

    public String getInventoryProductPrice(int listProductNumberIndex) {
        List<WebElement> inventoryProducts = driver.findElements(By.cssSelector(this.INVENTORY_ITEM));
        WebElement inventoryProductNumber = inventoryProducts.get(listProductNumberIndex);
        String actualInventoryProductPrice = inventoryProductNumber.findElement(By.cssSelector(this.PRODUCT_PRICE)).getText();
        return actualInventoryProductPrice;
    }
}