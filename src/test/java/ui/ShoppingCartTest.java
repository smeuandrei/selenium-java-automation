package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import pages.ShoppingCartPage;
import base.BaseTest;
import utils.TestData;

public class ShoppingCartTest extends BaseTest {

    @Test
    public void addToCartTest() {

        // Login to application
        LoginPage loginPaceActions = new LoginPage(driver, wait);
        loginPaceActions.accessLoginPage();
        loginPaceActions.validLogin(TestData.STANDARD_USER, TestData.SECRET_SAUCE);

        // Add product to cart
        ProductPage productPageActions = new ProductPage(driver, wait);
        productPageActions.addProductToCart(TestData.PRODUCT_NAME_1);
        productPageActions.addProductToCart(TestData.PRODUCT_NAME_2);

        //Verify cart
        ShoppingCartPage shoppingCartPageActions = new ShoppingCartPage(driver, wait);
        shoppingCartPageActions.accessCartPageByButton();
        Assert.assertTrue(shoppingCartPageActions.isProductDisplayedInCart(TestData.PRODUCT_NAME_1));
        Assert.assertTrue(shoppingCartPageActions.isProductDisplayedInCart(TestData.PRODUCT_NAME_2));
    }

    @Test
    public void removeProductsFromCartTest() {

        // Login to application
        LoginPage loginPaceActions = new LoginPage(driver, wait);
        loginPaceActions.accessLoginPage();
        loginPaceActions.validLogin(TestData.STANDARD_USER, TestData.SECRET_SAUCE);

        // Add products to cart
        ProductPage productPageActions = new ProductPage(driver, wait);
        productPageActions.addProductToCart(TestData.PRODUCT_NAME_1);
        productPageActions.addProductToCart(TestData.PRODUCT_NAME_2);

        // Remove products from cart
        ShoppingCartPage shoppingCartPageActions = new ShoppingCartPage(driver, wait);
        shoppingCartPageActions.accessCartPageByButton();
        shoppingCartPageActions.removeProductFromCart(TestData.PRODUCT_NAME_1);
        shoppingCartPageActions.removeProductFromCart(TestData.PRODUCT_NAME_2);

        // Verify cart is empty
        Assert.assertFalse(shoppingCartPageActions.isProductDisplayedInCart(TestData.PRODUCT_NAME_1));
        Assert.assertFalse(shoppingCartPageActions.isProductDisplayedInCart(TestData.PRODUCT_NAME_2));
        Assert.assertFalse(shoppingCartPageActions.isShoppingCartBadgeDisplayed());
    }
}
