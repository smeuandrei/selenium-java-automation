package ui;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import base.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void openStore() {
        LoginPage loginPageActions = new LoginPage(driver, wait);

        loginPageActions.accessLoginPage();
        loginPageActions.validLogin("standard_user", "secret_sauce");

        loginPageActions.accessLoginPage();
        String errorMessage = loginPageActions.invalidLogin("invalid_user", "invalid_password");
        String invalidCredentialsMessage = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(errorMessage, invalidCredentialsMessage);

        loginPageActions.accessLoginPage();
        String blockedUser = loginPageActions.invalidLogin("locked_out_user", "secret_sauce");
        String blockedUserMessage = "Epic sadface: Sorry, this user has been locked out.";
        Assert.assertEquals(blockedUser, blockedUserMessage);
    }
}
