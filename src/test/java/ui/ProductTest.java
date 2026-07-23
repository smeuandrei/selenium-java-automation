package ui;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductPage;

public class ProductTest extends BaseTest {

    private static final String productName_1 = "Sauce Labs Backpack";
    private static final String productDetails_1 = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
    private static final String productPrice_1 = "$29.99";

    private static final String productName_2 = "Test.allTheThings() T-Shirt (Red)";
    private static final String productDetails_2 = "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton.";
    private static final String productPrice_2 = "$15.99";

    private static final String productName_3 = "Sauce Labs Onesie";
    private static final String productDetails_3 = "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.";
    private static final String productPrice_3 = "$7.99";

    private static final String productName_4 = "Sauce Labs Fleece Jacket";
    private static final String productDetails_4 = "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.";
    private static final String productPrice_4 = "$49.99";

    @Test
    public void filterProductsByName() {
        LoginPage loginPageActions = new LoginPage(driver, wait);
        loginPageActions.accessLoginPage();
        loginPageActions.validLogin("standard_user", "secret_sauce");

        // Filter ascendent by product name
        ProductPage productPageActions = new ProductPage(driver, wait);
        productPageActions.filterProducts("az");

        // Verify first product
        String actualFirstProductName = productPageActions.getInventoryProductName(0);
        String actualFirstProductDetails = productPageActions.getInventoryProductDetails(0);
        String actualFirstProductPrice = productPageActions.getInventoryProductPrice(0);

        Assert.assertEquals(actualFirstProductName, productName_1);
        Assert.assertEquals(actualFirstProductDetails, productDetails_1);
        Assert.assertEquals(actualFirstProductPrice, productPrice_1);

        // Verify last product
        String actualLastProductName = productPageActions.getInventoryProductName(5);
        String actualLastProductDetails = productPageActions.getInventoryProductDetails(5);
        String actualLastProductPrice = productPageActions.getInventoryProductPrice(5);

        Assert.assertEquals(actualLastProductName, productName_2);
        Assert.assertEquals(actualLastProductDetails, productDetails_2);
        Assert.assertEquals(actualLastProductPrice, productPrice_2);

        // Filter descendent by product name
        productPageActions.filterProducts("za");

        // Verify first product
        actualFirstProductName = productPageActions.getInventoryProductName(0);
        actualFirstProductDetails = productPageActions.getInventoryProductDetails(0);
        actualFirstProductPrice = productPageActions.getInventoryProductPrice(0);

        Assert.assertEquals(actualFirstProductName, productName_2);
        Assert.assertEquals(actualFirstProductDetails, productDetails_2);
        Assert.assertEquals(actualFirstProductPrice, productPrice_2);

        // Verify last product
        actualLastProductName = productPageActions.getInventoryProductName(5);
        actualLastProductDetails = productPageActions.getInventoryProductDetails(5);
        actualLastProductPrice = productPageActions.getInventoryProductPrice(5);

        Assert.assertEquals(actualLastProductName, productName_1);
        Assert.assertEquals(actualLastProductDetails, productDetails_1);
        Assert.assertEquals(actualLastProductPrice, productPrice_1);
    }

    @Test
    public void filterProductsByPrice() {
        LoginPage loginPageActions = new LoginPage(driver, wait);
        loginPageActions.accessLoginPage();
        loginPageActions.validLogin("standard_user", "secret_sauce");

        // Filter ascendent by product price
        ProductPage productPageActions = new ProductPage(driver, wait);
        productPageActions.filterProducts("lohi");

        // Verify first product
        String actualFirstProductName = productPageActions.getInventoryProductName(0);
        String actualFirstProductDetails = productPageActions.getInventoryProductDetails(0);
        String actualFirstProductPrice = productPageActions.getInventoryProductPrice(0);

        Assert.assertEquals(actualFirstProductName, productName_3);
        Assert.assertEquals(actualFirstProductDetails, productDetails_3);
        Assert.assertEquals(actualFirstProductPrice, productPrice_3);

        // Verify last product
        String actualLastProductName = productPageActions.getInventoryProductName(5);
        String actualLastProductDetails = productPageActions.getInventoryProductDetails(5);
        String actualLastProductPrice = productPageActions.getInventoryProductPrice(5);

        Assert.assertEquals(actualLastProductName, productName_4);
        Assert.assertEquals(actualLastProductDetails, productDetails_4);
        Assert.assertEquals(actualLastProductPrice, productPrice_4);

        // Filter descendent by product price
        productPageActions.filterProducts("hilo");

        // Verify first product
        actualFirstProductName = productPageActions.getInventoryProductName(0);
        actualFirstProductDetails = productPageActions.getInventoryProductDetails(0);
        actualFirstProductPrice = productPageActions.getInventoryProductPrice(0);

        Assert.assertEquals(actualFirstProductName, productName_4);
        Assert.assertEquals(actualFirstProductDetails, productDetails_4);
        Assert.assertEquals(actualFirstProductPrice, productPrice_4);

        // Verify last product
        actualLastProductName = productPageActions.getInventoryProductName(5);
        actualLastProductDetails = productPageActions.getInventoryProductDetails(5);
        actualLastProductPrice = productPageActions.getInventoryProductPrice(5);

        Assert.assertEquals(actualLastProductName, productName_3);
        Assert.assertEquals(actualLastProductDetails, productDetails_3);
        Assert.assertEquals(actualLastProductPrice, productPrice_3);
    }
}
