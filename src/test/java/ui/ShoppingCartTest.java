package ui;

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
    }
}
