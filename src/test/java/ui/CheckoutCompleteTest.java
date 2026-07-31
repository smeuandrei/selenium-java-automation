package ui;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CheckoutCompletePage;
import pages.CheckoutOverviewPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;
import pages.ShoppingCartPage;
import utils.TestData;

public class CheckoutCompleteTest extends BaseTest {

    @Test
    public void completeOrderAndReturnHomeTest() {
        CheckoutOverviewPage checkoutOverviewPageActions = accessCheckoutOverviewPage();
        checkoutOverviewPageActions.clickFinish();

        CheckoutCompletePage checkoutCompletePageActions = new CheckoutCompletePage(driver, wait);
        checkoutCompletePageActions.waitForCompletePage();

        Assert.assertTrue(checkoutCompletePageActions.isCheckoutCompletePageDisplayed());
        Assert.assertEquals(checkoutCompletePageActions.getCompletionMessage(), TestData.ORDER_CONFIRMATION_MESSAGE);
        Assert.assertEquals(checkoutCompletePageActions.getCompletionDetails(), TestData.ORDER_THANK_YOU_MESSAGE);
        Assert.assertFalse(checkoutCompletePageActions.isShoppingCartBadgeVisible());

        checkoutCompletePageActions.clickBackHome();
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"));
        Assert.assertEquals(new ProductPage(driver, wait).getInventoryProductName(0), TestData.PRODUCT_NAME_1);
    }

    private CheckoutOverviewPage accessCheckoutOverviewPage() {
        LoginPage loginPageActions = new LoginPage(driver, wait);
        loginPageActions.accessLoginPage();
        loginPageActions.validLogin(TestData.STANDARD_USER, TestData.SECRET_SAUCE);

        ProductPage productPageActions = new ProductPage(driver, wait);
        productPageActions.addProductToCart(TestData.PRODUCT_NAME_1);
        productPageActions.addProductToCart(TestData.PRODUCT_NAME_2);

        ShoppingCartPage shoppingCartPageActions = new ShoppingCartPage(driver, wait);
        shoppingCartPageActions.accessCartPageByButton();
        shoppingCartPageActions.clickCheckoutButton();

        CheckoutPage checkoutPageActions = new CheckoutPage(driver, wait);
        checkoutPageActions.waitForCheckoutInformationPage();
        checkoutPageActions.fillCheckoutInformation(TestData.FIRST_NAME, TestData.LAST_NAME, TestData.POSTAL_CODE);
        checkoutPageActions.continueCheckout();

        CheckoutOverviewPage checkoutOverviewPageActions = new CheckoutOverviewPage(driver, wait);
        checkoutOverviewPageActions.waitForOverviewPage();
        return checkoutOverviewPageActions;
    }
}
