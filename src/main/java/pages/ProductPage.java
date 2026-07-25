package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    private static final String INVENTORY_ITEM = ".inventory_item";
    private static final String PRODUCT_NAME = ".inventory_item_name";
    private static final String PRODUCT_DETAILS = ".inventory_item_desc";
    private static final String PRODUCT_PRICE = ".inventory_item_price";
    private static final String PRODUCT_ADD_TO_CART_BUTTON = ".btn.btn_primary.btn_small.btn_inventory";
    private static final String PRODUCT_REMOVE_FROM_CART_BUTTON = ".btn.btn_secondary.btn_small.btn_inventory";
    private static final String PRODUCT_SHOPPING_CART_BADGE = ".shopping_cart_badge";
    private static final String PRODUCT_FILTER = ".product_sort_container";
    private static final String PRODUCT_DETAIL_PAGE_NAME = ".inventory_details_name.large_size";

    WebDriver driver;
    WebDriverWait wait;

    public ProductPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void filterProducts(String filterBy) {
        WebElement sortDropdown = driver.findElement(By.cssSelector(PRODUCT_FILTER));
        Select sort = new Select(sortDropdown);
        sort.selectByValue(filterBy);
    }

    public String getInventoryProductName(int listProductNumberIndex) {
        List<WebElement> inventoryProducts = driver.findElements(By.cssSelector(INVENTORY_ITEM));
        WebElement inventoryProductNumber = inventoryProducts.get(listProductNumberIndex);
        String actualInventoryProductName = inventoryProductNumber.findElement(By.cssSelector(PRODUCT_NAME)).getText();
        return actualInventoryProductName;
    }

    public String getInventoryProductDetails(int listProductNumberIndex) {
        List<WebElement> inventoryProducts = driver.findElements(By.cssSelector(INVENTORY_ITEM));
        WebElement inventoryProductNumber = inventoryProducts.get(listProductNumberIndex);
        String actualInventoryProductDetails = inventoryProductNumber.findElement(By.cssSelector(PRODUCT_DETAILS))
                .getText();
        return actualInventoryProductDetails;
    }

    public String getInventoryProductPrice(int listProductNumberIndex) {
        List<WebElement> inventoryProducts = driver.findElements(By.cssSelector(INVENTORY_ITEM));
        WebElement inventoryProductNumber = inventoryProducts.get(listProductNumberIndex);
        String actualInventoryProductPrice = inventoryProductNumber.findElement(By.cssSelector(PRODUCT_PRICE))
                .getText();
        return actualInventoryProductPrice;
    }

    public void navigateToProductPage(String productName) {
        driver.findElement(By.cssSelector(PRODUCT_NAME)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(PRODUCT_DETAIL_PAGE_NAME)));
    }

    public void addProductToCart(String productName) {
        List<WebElement> inventoryProducts = driver.findElements(By.cssSelector(INVENTORY_ITEM));
        for (WebElement product : inventoryProducts) {
            String actualProductName = product.findElement(By.cssSelector(PRODUCT_NAME)).getText();
            if (actualProductName.equals(productName)) {
                product.findElement(By.cssSelector(PRODUCT_ADD_TO_CART_BUTTON)).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(PRODUCT_REMOVE_FROM_CART_BUTTON)));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(PRODUCT_SHOPPING_CART_BADGE)));
            }
        }
    }

    public void removeProductFromCart(String productName) {
        List<WebElement> inventoryProducts = driver.findElements(By.cssSelector(INVENTORY_ITEM));
        for (WebElement product : inventoryProducts) {
            String actualProductName = product.findElement(By.cssSelector(PRODUCT_NAME)).getText();
            if (actualProductName.equals(productName)) {
                product.findElement(By.cssSelector(PRODUCT_REMOVE_FROM_CART_BUTTON)).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(PRODUCT_ADD_TO_CART_BUTTON)));
            }
        }
    }
}