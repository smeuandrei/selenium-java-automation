package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductDetailPage;
import pages.ProductPage;
import base.BaseTest;
import utils.TestData;

public class ProductDetailTest extends BaseTest {

    @Test
    public void verifyProductDetailsTest() {
        // Valid login
        LoginPage loginPageActions = new LoginPage(driver, wait);
        loginPageActions.accessLoginPage();
        loginPageActions.validLogin(TestData.STANDARD_USER, TestData.SECRET_SAUCE);

        // Valid navigate to product page
        ProductPage productPageActions = new ProductPage(driver, wait);
        productPageActions.navigateToProductPage(TestData.PRODUCT_NAME_1);

        // Verify product data on dedicated page
        ProductDetailPage productDetailPageActions = new ProductDetailPage(driver, wait);
        String actualProductName = productDetailPageActions.getProductName();
        String actualProductDetails = productDetailPageActions.getProductDetails();
        String actualProductPrice = productDetailPageActions.getProductPrice();

        Assert.assertEquals(actualProductName, TestData.PRODUCT_NAME_1);
        Assert.assertEquals(actualProductDetails, TestData.PRODUCT_DESCRIPTION_1);
        Assert.assertEquals(actualProductPrice, TestData.PRODUCT_PRICE_1);

        //Verify add to cart button
        productDetailPageActions.verifyAddToCartButton();

        //Go back to the main page
        productDetailPageActions.navigateToHomePage();
    }

    @Test
    public void addToCartTest(){
        // Valid login
        LoginPage loginPageActions = new LoginPage(driver, wait);
        loginPageActions.accessLoginPage();
        loginPageActions.validLogin(TestData.STANDARD_USER, TestData.SECRET_SAUCE);

        // Valid navigate to product page
        ProductPage productPageActions = new ProductPage(driver, wait);
        productPageActions.navigateToProductPage(TestData.PRODUCT_NAME_1);

        //Add product to cart
        ProductDetailPage productDetailPageActions = new ProductDetailPage(driver, wait);
        productDetailPageActions.addCurrentProductToCart();
        productDetailPageActions.removeProductFromCart();
    }
}
