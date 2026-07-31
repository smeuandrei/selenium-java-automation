package ui;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CheckoutOverviewPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;
import pages.ShoppingCartPage;
import utils.TestData;

public class CheckoutOverviewTest extends BaseTest {

    @Test
    public void verifyCheckoutOverviewProductsAndTotalTest() {
        CheckoutOverviewPage checkoutOverviewPageActions = accessCheckoutOverviewPage();

        Assert.assertTrue(checkoutOverviewPageActions.isProductDisplayed(TestData.PRODUCT_NAME_1));
        Assert.assertTrue(checkoutOverviewPageActions.isProductDisplayed(TestData.PRODUCT_NAME_2));
        Assert.assertTrue(checkoutOverviewPageActions.isProductPriceDisplayed(TestData.PRODUCT_PRICE_1));
        Assert.assertTrue(checkoutOverviewPageActions.isProductPriceDisplayed(TestData.PRODUCT_PRICE_2));
        Assert.assertEquals(parseMoney(checkoutOverviewPageActions.getItemSubtotal()),
                expectedSubtotal(TestData.PRODUCT_PRICE_1, TestData.PRODUCT_PRICE_2));
        Assert.assertEquals(parseMoney(checkoutOverviewPageActions.getTax()),
                expectedTax(TestData.PRODUCT_PRICE_1, TestData.PRODUCT_PRICE_2));
        Assert.assertEquals(parseMoney(checkoutOverviewPageActions.getTotal()),
                expectedTotal(TestData.PRODUCT_PRICE_1, TestData.PRODUCT_PRICE_2));
    }

    @Test
    public void cancelButtonReturnsToCartTest() {
        CheckoutOverviewPage checkoutOverviewPageActions = accessCheckoutOverviewPage();

        checkoutOverviewPageActions.clickCancel();

        ProductPage productPageActions = new ProductPage(driver, wait);
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"));
        Assert.assertEquals(productPageActions.getInventoryProductName(0), TestData.PRODUCT_NAME_1);
    }

    @Test
    public void finishButtonCompletesCheckoutTest() {
        CheckoutOverviewPage checkoutOverviewPageActions = accessCheckoutOverviewPage();

        checkoutOverviewPageActions.clickFinish();

        Assert.assertTrue(checkoutOverviewPageActions.isCheckoutCompletePageDisplayed());
        Assert.assertEquals(checkoutOverviewPageActions.getConfirmationMessage(), TestData.ORDER_CONFIRMATION_MESSAGE);
        Assert.assertEquals(checkoutOverviewPageActions.getThankYouMessage(), TestData.ORDER_THANK_YOU_MESSAGE);
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

    private BigDecimal expectedSubtotal(String firstPrice, String secondPrice) {
        BigDecimal total = parseMoney(firstPrice);
        total = total.add(parseMoney(secondPrice));
        return total.setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal expectedTax(String firstPrice, String secondPrice) {
        return expectedSubtotal(firstPrice, secondPrice)
                .multiply(new BigDecimal("0.08"))
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal expectedTotal(String firstPrice, String secondPrice) {
        return expectedSubtotal(firstPrice, secondPrice)
                .add(expectedTax(firstPrice, secondPrice))
                .setScale(2, RoundingMode.HALF_UP);
    }

    private static BigDecimal parseMoney(String value) {
        return new BigDecimal(value.replaceAll("[^0-9.]", ""));
    }
}
