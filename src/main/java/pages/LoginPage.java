package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage {
    private String mainUrl = "https://www.saucedemo.com";
    private String usernameInput = "user-name";
    private String passwordInput = "password";
    private String loginButton = "login-button";
    private String shoppingCartContainer = "shopping_cart_container";

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

     public void accessLoginPage() {
        driver.get(this.mainUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(this.usernameInput)));
    }

    public void login(String username, String password) {
        driver.findElement(By.id(this.usernameInput)).sendKeys(username);
        driver.findElement(By.id(this.passwordInput)).sendKeys(password);
        driver.findElement(By.id(this.loginButton)).click();
    }

    public void clearLoginFields() {
        driver.findElement(By.id(this.usernameInput)).clear();
        driver.findElement(By.id(this.passwordInput)).clear();
    }

    public void validLogin(String username, String password) {
        login(username, password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(this.shoppingCartContainer)));
    }

    public String invalidLogin(String username, String password) {
        login(username, password);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-test='error']"))).getText();
    }
}
