package ui;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import base.BaseTest;
import utils.TestData;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {
        LoginPage loginPageActions = new LoginPage(driver, wait);

        loginPageActions.accessLoginPage();
        loginPageActions.validLogin(TestData.STANDARD_USER, TestData.SECRET_SAUCE);

        loginPageActions.accessLoginPage();
        String errorMessage = loginPageActions.invalidLogin(TestData.INVALID_USER, TestData.INVALID_PASSWORD);
        Assert.assertEquals(errorMessage, TestData.INVALID_CREDENTIALS_MESSAGE);

        loginPageActions.accessLoginPage();
        String blockedUser = loginPageActions.invalidLogin(TestData.LOCKED_OUT_USER, TestData.SECRET_SAUCE);
        Assert.assertEquals(blockedUser, TestData.LOCKED_OUT_MESSAGE);
    }
}
