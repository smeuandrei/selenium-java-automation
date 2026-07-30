package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import pages.ShoppingCartPage;
import base.BaseTest;

public class ShoppingCartTest extends BaseTest {

    private static final String PRODUCT_NAME_1 = "Sauce Labs Backpack";
    private static final String PRODUCT_NAME_2 = "Sauce Labs Onesie";

    @Test
    public void addToCartTest() {

        // Login to application
        LoginPage loginPaceActions = new LoginPage(driver, wait);
        loginPaceActions.accessLoginPage();
        loginPaceActions.validLogin("standard_user", "secret_sauce");

        // Add product to cart
        ProductPage productPageActions = new ProductPage(driver, wait);
        productPageActions.addProductToCart(PRODUCT_NAME_1);
        productPageActions.addProductToCart(PRODUCT_NAME_2);

        //Verify cart
        ShoppingCartPage shoppingCartPageActions = new ShoppingCartPage(driver, wait);
        shoppingCartPageActions.accessCartPageByButton();
        Assert.assertTrue(shoppingCartPageActions.isProductDisplayedInCart(PRODUCT_NAME_1));
        Assert.assertTrue(shoppingCartPageActions.isProductDisplayedInCart(PRODUCT_NAME_2));
    }

    @Test
    public void removeProductsFromCartTest() {

        // Login to application
        LoginPage loginPaceActions = new LoginPage(driver, wait);
        loginPaceActions.accessLoginPage();
        loginPaceActions.validLogin("standard_user", "secret_sauce");

        // Add products to cart
        ProductPage productPageActions = new ProductPage(driver, wait);
        productPageActions.addProductToCart(PRODUCT_NAME_1);
        productPageActions.addProductToCart(PRODUCT_NAME_2);

        // Remove products from cart
        ShoppingCartPage shoppingCartPageActions = new ShoppingCartPage(driver, wait);
        shoppingCartPageActions.accessCartPageByButton();
        shoppingCartPageActions.removeProductFromCart(PRODUCT_NAME_1);
        shoppingCartPageActions.removeProductFromCart(PRODUCT_NAME_2);

        // Verify cart is empty
        Assert.assertFalse(shoppingCartPageActions.isProductDisplayedInCart(PRODUCT_NAME_1));
        Assert.assertFalse(shoppingCartPageActions.isProductDisplayedInCart(PRODUCT_NAME_2));
        Assert.assertFalse(shoppingCartPageActions.isShoppingCartBadgeDisplayed());
    }
}
