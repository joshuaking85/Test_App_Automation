package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;
    By email_input = By.id("email");
    By password_input = By.id("password");
    By login = By.id("login-button");
    By dashboard_header = By.xpath("//h1[text()='Dashboard']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void login_as_user(String email, String password) {
        wait.until(ExpectedConditions.presenceOfElementLocated(email_input));
        driver.findElement(email_input).sendKeys(email);
        driver.findElement(password_input).sendKeys(password);
        driver.findElement(login).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(dashboard_header));
    }
}
