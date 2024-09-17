package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class DashboardPage {
    WebDriver driver;
    WebDriverWait wait;
    By add_record_button = By.xpath("//button[text()='Add record']");
    By add_person_to_household_header = By.xpath("//h2[text()='Add new person to your household']");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void click_add_new_record() {
        wait.until(ExpectedConditions.presenceOfElementLocated(add_record_button));
        driver.findElement(add_record_button).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(add_person_to_household_header));
    }
}
