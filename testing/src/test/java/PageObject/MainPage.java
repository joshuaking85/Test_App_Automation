package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    WebDriver driver;
    WebDriverWait wait;

    By user_icon = By.id("user-menu-button");
    By sign_in = By.xpath("//a[@data-testid='menu-item-Sign-in']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));;
    }

    public void load_app() {
        driver.manage().window().maximize();
        driver.get("http://localhost:3000");
    }

    public void select_user_icon(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(user_icon));
        driver.findElement(user_icon).click();
        driver.findElement(sign_in).click();
    }
}
