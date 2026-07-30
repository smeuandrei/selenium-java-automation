package ui;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;
import pages.ShoppingCartPage;

public class CheckoutTest extends BaseTest {

    private static final String PRODUCT_NAME = "Sauce Labs Backpack";
    private static final String FIRST_NAME = "Andrei";
    private static final String LAST_NAME = "Popescu";
    private static final String POSTAL_CODE = "100100";
    private static final String FIRST_NAME_REQUIRED_MESSAGE = "Error: First Name is required";
    private static final String LAST_NAME_REQUIRED_MESSAGE = "Error: Last Name is required";
    private static final String POSTAL_CODE_REQUIRED_MESSAGE = "Error: Postal Code is required";

    @Test(priority = 1)
    public void continueCheckoutWithoutFirstNameTest() {
        CheckoutPage checkoutPageActions = accessCheckoutInformationPage();

        checkoutPageActions.continueCheckout();
        String errorMessage = checkoutPageActions.getErrorMessage();

        Assert.assertEquals(errorMessage, FIRST_NAME_REQUIRED_MESSAGE);
    }

    @Test(priority = 2)
    public void continueCheckoutWithoutLastNameTest() {
        CheckoutPage checkoutPageActions = accessCheckoutInformationPage();

        checkoutPageActions.fillFirstName(FIRST_NAME);
        checkoutPageActions.continueCheckout();
        String errorMessage = checkoutPageActions.getErrorMessage();

        Assert.assertEquals(errorMessage, LAST_NAME_REQUIRED_MESSAGE);
    }

    @Test(priority = 3)
    public void continueCheckoutWithoutPostalCodeTest() {
        CheckoutPage checkoutPageActions = accessCheckoutInformationPage();

        checkoutPageActions.fillFirstName(FIRST_NAME);
        checkoutPageActions.fillLastName(LAST_NAME);
        checkoutPageActions.continueCheckout();
        String errorMessage = checkoutPageActions.getErrorMessage();

        Assert.assertEquals(errorMessage, POSTAL_CODE_REQUIRED_MESSAGE);
    }

    @Test(priority = 4)
    public void continueCheckoutWithValidInformationTest() {
        CheckoutPage checkoutPageActions = accessCheckoutInformationPage();

        checkoutPageActions.fillCheckoutInformation(FIRST_NAME, LAST_NAME, POSTAL_CODE);
        checkoutPageActions.continueCheckout();

        Assert.assertTrue(checkoutPageActions.isCheckoutOverviewPageDisplayed());
    }

    private CheckoutPage accessCheckoutInformationPage() {
        LoginPage loginPageActions = new LoginPage(driver, wait);
        loginPageActions.accessLoginPage();
        loginPageActions.validLogin("standard_user", "secret_sauce");

        ProductPage productPageActions = new ProductPage(driver, wait);
        productPageActions.addProductToCart(PRODUCT_NAME);

        ShoppingCartPage shoppingCartPageActions = new ShoppingCartPage(driver, wait);
        shoppingCartPageActions.accessCartPageByButton();
        shoppingCartPageActions.clickCheckoutButton();

        CheckoutPage checkoutPageActions = new CheckoutPage(driver, wait);
        checkoutPageActions.waitForCheckoutInformationPage();
        return checkoutPageActions;
    }
}
