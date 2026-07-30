package ui;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductPage;
import utils.TestData;

public class ProductTest extends BaseTest {

    @Test
    public void filterProductsByNameTest() {
        LoginPage loginPageActions = new LoginPage(driver, wait);
        loginPageActions.accessLoginPage();
        loginPageActions.validLogin(TestData.STANDARD_USER, TestData.SECRET_SAUCE);

        // Filter ascendent by product name
        ProductPage productPageActions = new ProductPage(driver, wait);
        productPageActions.filterProducts("az");

        // Verify first product
        String actualFirstProductName = productPageActions.getInventoryProductName(0);
        String actualFirstProductDetails = productPageActions.getInventoryProductDetails(0);
        String actualFirstProductPrice = productPageActions.getInventoryProductPrice(0);

        Assert.assertEquals(actualFirstProductName, TestData.PRODUCT_NAME_1);
        Assert.assertEquals(actualFirstProductDetails, TestData.PRODUCT_DESCRIPTION_1);
        Assert.assertEquals(actualFirstProductPrice, TestData.PRODUCT_PRICE_1);

        // Verify last product
        String actualLastProductName = productPageActions.getInventoryProductName(5);
        String actualLastProductDetails = productPageActions.getInventoryProductDetails(5);
        String actualLastProductPrice = productPageActions.getInventoryProductPrice(5);

        Assert.assertEquals(actualLastProductName, TestData.PRODUCT_NAME_3);
        Assert.assertEquals(actualLastProductDetails, TestData.PRODUCT_DESCRIPTION_3);
        Assert.assertEquals(actualLastProductPrice, TestData.PRODUCT_PRICE_3);

        // Filter descendent by product name
        productPageActions.filterProducts("za");

        // Verify first product
        actualFirstProductName = productPageActions.getInventoryProductName(0);
        actualFirstProductDetails = productPageActions.getInventoryProductDetails(0);
        actualFirstProductPrice = productPageActions.getInventoryProductPrice(0);

        Assert.assertEquals(actualFirstProductName, TestData.PRODUCT_NAME_3);
        Assert.assertEquals(actualFirstProductDetails, TestData.PRODUCT_DESCRIPTION_3);
        Assert.assertEquals(actualFirstProductPrice, TestData.PRODUCT_PRICE_3);

        // Verify last product
        actualLastProductName = productPageActions.getInventoryProductName(5);
        actualLastProductDetails = productPageActions.getInventoryProductDetails(5);
        actualLastProductPrice = productPageActions.getInventoryProductPrice(5);

        Assert.assertEquals(actualLastProductName, TestData.PRODUCT_NAME_1);
        Assert.assertEquals(actualLastProductDetails, TestData.PRODUCT_DESCRIPTION_1);
        Assert.assertEquals(actualLastProductPrice, TestData.PRODUCT_PRICE_1);
    }

    @Test
    public void filterProductsByPriceTest() {
        LoginPage loginPageActions = new LoginPage(driver, wait);
        loginPageActions.accessLoginPage();
        loginPageActions.validLogin(TestData.STANDARD_USER, TestData.SECRET_SAUCE);

        // Filter ascendent by product price
        ProductPage productPageActions = new ProductPage(driver, wait);
        productPageActions.filterProducts("lohi");

        // Verify first product
        String actualFirstProductName = productPageActions.getInventoryProductName(0);
        String actualFirstProductDetails = productPageActions.getInventoryProductDetails(0);
        String actualFirstProductPrice = productPageActions.getInventoryProductPrice(0);

        Assert.assertEquals(actualFirstProductName, TestData.PRODUCT_NAME_2);
        Assert.assertEquals(actualFirstProductDetails, TestData.PRODUCT_DESCRIPTION_2);
        Assert.assertEquals(actualFirstProductPrice, TestData.PRODUCT_PRICE_2);

        // Verify last product
        String actualLastProductName = productPageActions.getInventoryProductName(5);
        String actualLastProductDetails = productPageActions.getInventoryProductDetails(5);
        String actualLastProductPrice = productPageActions.getInventoryProductPrice(5);

        Assert.assertEquals(actualLastProductName, TestData.PRODUCT_NAME_4);
        Assert.assertEquals(actualLastProductDetails, TestData.PRODUCT_DESCRIPTION_4);
        Assert.assertEquals(actualLastProductPrice, TestData.PRODUCT_PRICE_4);

        // Filter descendent by product price
        productPageActions.filterProducts("hilo");

        // Verify first product
        actualFirstProductName = productPageActions.getInventoryProductName(0);
        actualFirstProductDetails = productPageActions.getInventoryProductDetails(0);
        actualFirstProductPrice = productPageActions.getInventoryProductPrice(0);

        Assert.assertEquals(actualFirstProductName, TestData.PRODUCT_NAME_4);
        Assert.assertEquals(actualFirstProductDetails, TestData.PRODUCT_DESCRIPTION_4);
        Assert.assertEquals(actualFirstProductPrice, TestData.PRODUCT_PRICE_4);

        // Verify last product
        actualLastProductName = productPageActions.getInventoryProductName(5);
        actualLastProductDetails = productPageActions.getInventoryProductDetails(5);
        actualLastProductPrice = productPageActions.getInventoryProductPrice(5);

        Assert.assertEquals(actualLastProductName, TestData.PRODUCT_NAME_2);
        Assert.assertEquals(actualLastProductDetails, TestData.PRODUCT_DESCRIPTION_2);
        Assert.assertEquals(actualLastProductPrice, TestData.PRODUCT_PRICE_2);
    }

    @Test
    public void addProductToCartTest() {
        // Login to the application
        LoginPage loginPageActions = new LoginPage(driver, wait);
        loginPageActions.accessLoginPage();
        loginPageActions.validLogin(TestData.STANDARD_USER, TestData.SECRET_SAUCE);

        // Add & remove product to cart
        ProductPage productPageActions = new ProductPage(driver, wait);
        productPageActions.addProductToCart(TestData.PRODUCT_NAME_1);
        productPageActions.addProductToCart(TestData.PRODUCT_NAME_3);
        productPageActions.addProductToCart(TestData.PRODUCT_NAME_2);

        productPageActions.removeProductFromCart(TestData.PRODUCT_NAME_1);
        productPageActions.removeProductFromCart(TestData.PRODUCT_NAME_3);
        productPageActions.removeProductFromCart(TestData.PRODUCT_NAME_2);
    }
}
