package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage {
    private static final String MAIN_URL = "https://www.saucedemo.com";
    private static final String USERNAME_FIELD = "user-name";
    private static final String PASSWORD_FIELD = "password";
    private static final String LOGIN_BUTTON = "login-button";
    private static final String SHOPPING_CART_CONTAINER = "shopping_cart_container";

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

     public void accessLoginPage() {
        driver.get(MAIN_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(USERNAME_FIELD)));
    }

    public void login(String username, String password) {
        driver.findElement(By.id(USERNAME_FIELD)).sendKeys(username);
        driver.findElement(By.id(PASSWORD_FIELD)).sendKeys(password);
        driver.findElement(By.id(LOGIN_BUTTON)).click();
        System.out.println(driver.manage().getCookies());
    }

    public void clearLoginFields() {
        driver.findElement(By.id(USERNAME_FIELD)).clear();
        driver.findElement(By.id(PASSWORD_FIELD)).clear();
    }

    public void validLogin(String username, String password) {
        login(username, password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(SHOPPING_CART_CONTAINER)));
    }

    public String invalidLogin(String username, String password) {
        login(username, password);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-test='error']"))).getText();
    }
}
