package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DeleteRecord {
    WebDriver driver;
    WebDriverWait wait;
    Integer record_count;
    By edit_household = By.xpath("//button[text()='Edit household']");
    By delete_button = By.xpath("//button[text()='Delete']");

    public DeleteRecord(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void deleteRecord() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(edit_household));
        List<WebElement> records = driver.findElements(delete_button);
        record_count = records.size();
        System.out.println(record_count);
        //driver.findElement(delete_button).click();
    }

}
