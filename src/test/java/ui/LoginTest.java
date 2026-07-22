package ui;

import org.testng.Assert;
import org.testng.annotations.Test;

import actions.LoginActions;
import base.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void openStore() {
        LoginActions loginActions = new LoginActions(driver, wait);

        loginActions.accessLoginPage();
        loginActions.validLogin("standard_user", "secret_sauce");

        loginActions.accessLoginPage();
        String errorMessage = loginActions.invalidLogin("invalid_user", "invalid_password");
        String invalidCredentialsMessage = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(errorMessage, invalidCredentialsMessage);

        loginActions.accessLoginPage();
        String blockedUser = loginActions.invalidLogin("locked_out_user", "secret_sauce");
        String blockedUserMessage = "Epic sadface: Sorry, this user has been locked out.";
        Assert.assertEquals(blockedUser, blockedUserMessage);
    }
}
