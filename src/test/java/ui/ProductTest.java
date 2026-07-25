package ui;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductPage;

public class ProductTest extends BaseTest {

    private static final String PRODUCT_NAME_1 = "Sauce Labs Backpack";
    private static final String PRODUCT_DESCRIPTION_1 = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
    private static final String PRODUCT_PRICE_1 = "$29.99";

    private static final String PRODUCT_NAME_2 = "Test.allTheThings() T-Shirt (Red)";
    private static final String PRODUCT_DESCRIPTION_2 = "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton.";
    private static final String PRODUCT_PRICE_2 = "$15.99";

    private static final String PRODUCT_NAME_3 = "Sauce Labs Onesie";
    private static final String PRODUCT_DESCRIPTION_3 = "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.";
    private static final String PRODUCT_PRICE_3 = "$7.99";

    private static final String PRODUCT_NAME_4 = "Sauce Labs Fleece Jacket";
    private static final String PRODUCT_DESCRIPTION_4 = "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.";
    private static final String PRODUCT_PRICE_4 = "$49.99";

    @Test
    public void filterProductsByName() {
        LoginPage loginPageActions = new LoginPage(driver, wait);
        loginPageActions.accessLoginPage();
        loginPageActions.validLogin("standard_user", "secret_sauce");

        // try {
        // Thread.sleep(30000);
        // } catch (Exception e) {
        // // TODO: handle exception
        // }

        // Filter ascendent by product name
        ProductPage productPageActions = new ProductPage(driver, wait);
        productPageActions.filterProducts("az");

        // Verify first product
        String actualFirstProductName = productPageActions.getInventoryProductName(0);
        String actualFirstProductDetails = productPageActions.getInventoryProductDetails(0);
        String actualFirstProductPrice = productPageActions.getInventoryProductPrice(0);

        Assert.assertEquals(actualFirstProductName, PRODUCT_NAME_1);
        Assert.assertEquals(actualFirstProductDetails, PRODUCT_DESCRIPTION_1);
        Assert.assertEquals(actualFirstProductPrice, PRODUCT_PRICE_1);

        // Verify last product
        String actualLastProductName = productPageActions.getInventoryProductName(5);
        String actualLastProductDetails = productPageActions.getInventoryProductDetails(5);
        String actualLastProductPrice = productPageActions.getInventoryProductPrice(5);

        Assert.assertEquals(actualLastProductName, PRODUCT_NAME_2);
        Assert.assertEquals(actualLastProductDetails, PRODUCT_DESCRIPTION_2);
        Assert.assertEquals(actualLastProductPrice, PRODUCT_PRICE_2);

        // Filter descendent by product name
        productPageActions.filterProducts("za");

        // Verify first product
        actualFirstProductName = productPageActions.getInventoryProductName(0);
        actualFirstProductDetails = productPageActions.getInventoryProductDetails(0);
        actualFirstProductPrice = productPageActions.getInventoryProductPrice(0);

        Assert.assertEquals(actualFirstProductName, PRODUCT_NAME_2);
        Assert.assertEquals(actualFirstProductDetails, PRODUCT_DESCRIPTION_2);
        Assert.assertEquals(actualFirstProductPrice, PRODUCT_PRICE_2);

        // Verify last product
        actualLastProductName = productPageActions.getInventoryProductName(5);
        actualLastProductDetails = productPageActions.getInventoryProductDetails(5);
        actualLastProductPrice = productPageActions.getInventoryProductPrice(5);

        Assert.assertEquals(actualLastProductName, PRODUCT_NAME_1);
        Assert.assertEquals(actualLastProductDetails, PRODUCT_DESCRIPTION_1);
        Assert.assertEquals(actualLastProductPrice, PRODUCT_PRICE_1);
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

        Assert.assertEquals(actualFirstProductName, PRODUCT_NAME_3);
        Assert.assertEquals(actualFirstProductDetails, PRODUCT_DESCRIPTION_3);
        Assert.assertEquals(actualFirstProductPrice, PRODUCT_PRICE_3);

        // Verify last product
        String actualLastProductName = productPageActions.getInventoryProductName(5);
        String actualLastProductDetails = productPageActions.getInventoryProductDetails(5);
        String actualLastProductPrice = productPageActions.getInventoryProductPrice(5);

        Assert.assertEquals(actualLastProductName, PRODUCT_NAME_4);
        Assert.assertEquals(actualLastProductDetails, PRODUCT_DESCRIPTION_4);
        Assert.assertEquals(actualLastProductPrice, PRODUCT_PRICE_4);

        // Filter descendent by product price
        productPageActions.filterProducts("hilo");

        // Verify first product
        actualFirstProductName = productPageActions.getInventoryProductName(0);
        actualFirstProductDetails = productPageActions.getInventoryProductDetails(0);
        actualFirstProductPrice = productPageActions.getInventoryProductPrice(0);

        Assert.assertEquals(actualFirstProductName, PRODUCT_NAME_4);
        Assert.assertEquals(actualFirstProductDetails, PRODUCT_DESCRIPTION_4);
        Assert.assertEquals(actualFirstProductPrice, PRODUCT_PRICE_4);

        // Verify last product
        actualLastProductName = productPageActions.getInventoryProductName(5);
        actualLastProductDetails = productPageActions.getInventoryProductDetails(5);
        actualLastProductPrice = productPageActions.getInventoryProductPrice(5);

        Assert.assertEquals(actualLastProductName, PRODUCT_NAME_3);
        Assert.assertEquals(actualLastProductDetails, PRODUCT_DESCRIPTION_3);
        Assert.assertEquals(actualLastProductPrice, PRODUCT_PRICE_3);
    }

    @Test
    public void addProductToCart() {
        // Login to the application
        LoginPage loginPageActions = new LoginPage(driver, wait);
        loginPageActions.accessLoginPage();
        loginPageActions.validLogin("standard_user", "secret_sauce");

        // Add & remove product to cart
        ProductPage productPageActions = new ProductPage(driver, wait);
        productPageActions.addProductToCart(PRODUCT_NAME_1);
        productPageActions.addProductToCart(PRODUCT_NAME_2);
        productPageActions.addProductToCart(PRODUCT_NAME_3);

        productPageActions.removeProductFromCart(PRODUCT_NAME_1);
        productPageActions.removeProductFromCart(PRODUCT_NAME_2);
        productPageActions.removeProductFromCart(PRODUCT_NAME_3);
    }
}
