package ui;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;
import pages.ShoppingCartPage;
import utils.TestData;

public class CheckoutTest extends BaseTest {

    @Test(priority = 1)
    public void continueCheckoutWithoutFirstNameTest() {
        CheckoutPage checkoutPageActions = accessCheckoutInformationPage();

        checkoutPageActions.continueCheckout();
        String errorMessage = checkoutPageActions.getErrorMessage();

        Assert.assertEquals(errorMessage, TestData.FIRST_NAME_REQUIRED_MESSAGE);
    }

    @Test(priority = 2)
    public void continueCheckoutWithoutLastNameTest() {
        CheckoutPage checkoutPageActions = accessCheckoutInformationPage();

        checkoutPageActions.fillFirstName(TestData.FIRST_NAME);
        checkoutPageActions.continueCheckout();
        String errorMessage = checkoutPageActions.getErrorMessage();

        Assert.assertEquals(errorMessage, TestData.LAST_NAME_REQUIRED_MESSAGE);
    }

    @Test(priority = 3)
    public void continueCheckoutWithoutPostalCodeTest() {
        CheckoutPage checkoutPageActions = accessCheckoutInformationPage();

        checkoutPageActions.fillFirstName(TestData.FIRST_NAME);
        checkoutPageActions.fillLastName(TestData.LAST_NAME);
        checkoutPageActions.continueCheckout();
        String errorMessage = checkoutPageActions.getErrorMessage();

        Assert.assertEquals(errorMessage, TestData.POSTAL_CODE_REQUIRED_MESSAGE);
    }

    @Test(priority = 4)
    public void continueCheckoutWithValidInformationTest() {
        CheckoutPage checkoutPageActions = accessCheckoutInformationPage();

        checkoutPageActions.fillCheckoutInformation(TestData.FIRST_NAME, TestData.LAST_NAME, TestData.POSTAL_CODE);
        checkoutPageActions.continueCheckout();

        Assert.assertTrue(checkoutPageActions.isCheckoutOverviewPageDisplayed());
    }

    private CheckoutPage accessCheckoutInformationPage() {
        LoginPage loginPageActions = new LoginPage(driver, wait);
        loginPageActions.accessLoginPage();
        loginPageActions.validLogin(TestData.STANDARD_USER, TestData.SECRET_SAUCE);

        ProductPage productPageActions = new ProductPage(driver, wait);
        productPageActions.addProductToCart(TestData.PRODUCT_NAME_1);

        ShoppingCartPage shoppingCartPageActions = new ShoppingCartPage(driver, wait);
        shoppingCartPageActions.accessCartPageByButton();
        shoppingCartPageActions.clickCheckoutButton();

        CheckoutPage checkoutPageActions = new CheckoutPage(driver, wait);
        checkoutPageActions.waitForCheckoutInformationPage();
        return checkoutPageActions;
    }
}
