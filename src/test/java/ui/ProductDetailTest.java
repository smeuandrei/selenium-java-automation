package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductDetailPage;
import pages.ProductPage;
import base.BaseTest;

public class ProductDetailTest extends BaseTest {

    private static final String PRODUCT_NAME_1 = "Sauce Labs Backpack";
    private static final String PRODUCT_DESCRIPTION_1 = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
    private static final String PRODUCT_PRICE_1 = "$29.99";

    @Test
    public void verifyProductDetails() {
        // Valid login
        LoginPage loginPageActions = new LoginPage(driver, wait);
        loginPageActions.accessLoginPage();
        loginPageActions.validLogin("standard_user", "secret_sauce");

        // Valid navigate to product page
        ProductPage productPageActions = new ProductPage(driver, wait);
        productPageActions.navigateToProductPage(PRODUCT_NAME_1);

        // Verify product data on dedicated page
        ProductDetailPage productDetailPageActions = new ProductDetailPage(driver, wait);
        String actualProductName = productDetailPageActions.getProductName();
        String actualProductDetails = productDetailPageActions.getProductDetails();
        String actualProductPrice = productDetailPageActions.getProductPrice();

        Assert.assertEquals(actualProductName, PRODUCT_NAME_1);
        Assert.assertEquals(actualProductDetails, PRODUCT_DESCRIPTION_1);
        Assert.assertEquals(actualProductPrice, PRODUCT_PRICE_1);

        //Verify add to cart button
        productDetailPageActions.verifyAddToCartButton();

        //Go back to the main page
        productDetailPageActions.navigateToHomePage();
    }
}
