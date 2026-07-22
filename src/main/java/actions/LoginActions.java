package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selectors.Selectors;

public class LoginActions {
    Selectors selectors = new Selectors();
    WebDriver driver;
    WebDriverWait wait;

    public LoginActions(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void accessLoginPage() {
        driver.get(selectors.mainUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(selectors.usernameInput)));
    }

    public void login(String username, String password) {
        driver.findElement(By.id(selectors.usernameInput)).sendKeys(username);
        driver.findElement(By.id(selectors.passwordInput)).sendKeys(password);
        driver.findElement(By.id(selectors.loginButton)).click();
    }

    public void clearLoginFields() {
        driver.findElement(By.id(selectors.usernameInput)).clear();
        driver.findElement(By.id(selectors.passwordInput)).clear();
    }

    public void validLogin(String username, String password) {
        login(username, password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(selectors.shoppingCartContainer)));
    }

    public String invalidLogin(String username, String password) {
        login(username, password);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-test='error']"))).getText();
    }

}
