package PageObject;

import Data.Person;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddRecord {
    WebDriver driver;
    WebDriverWait wait;
    By relationship_button = By.xpath("//div[@id='relationship']/button");
    By first_name_input_field = By.id("firstName");
    By last_name_input_field = By.id("lastName");
    By date_of_birth_input_field = By.cssSelector("input[placeholder='Pick a date']");
    By telephone_input_field = By.id("telephone");
    By gender_male_button = By.cssSelector("button[value='MALE']");
    By gender_female_button = By.cssSelector("button[value='FEMALE']");
    By hispanic_button = By.cssSelector("div[id='hispanic'] button");
    By other_hispanic_input_field = By.id("hispanicOther"); // Enables when "OTHER" is selected
    By race_button = By.cssSelector("div[id='race'] button");
    By other_race_input_field = By.id("raceOther");
    By other_stay_button = By.cssSelector("div[id='otherStay'] button");// Enables when "OTHER" is selected
    By update_button = By.xpath("//button[text()='Update']");
    By cancel_button = By.xpath("//button[text()='Cancel']");
    By ok_button = By.cssSelector("button[name='cancel-password-update-button btn']");
    public By new_record_saved_prompt = By.cssSelector("div[data-testid='success-message'] p");

    public AddRecord(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void select_dropdown(String option) {
        By select_list = By.xpath("//span[text()='" + option + "']");
        wait.until(ExpectedConditions.elementToBeClickable(select_list));
        WebElement list_box = driver.findElement(By.cssSelector("div[data-radix-popper-content-wrapper]"));
        // Scroll to element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", list_box);
        driver.findElement(select_list).click();
        wait.until(ExpectedConditions.stalenessOf(list_box));
    }

    public void add_new_person_to_household(Person person) {
        wait.until(ExpectedConditions.presenceOfElementLocated(relationship_button));
        driver.findElement(relationship_button).click();
        // Select Relationship
        select_dropdown(person.getRelationship().toString());
        // Enter Fname, Lname, DOB, Telephone
        driver.findElement(first_name_input_field).sendKeys(person.firstName);
        driver.findElement(last_name_input_field).sendKeys(person.lastName);
        // *DOB Improvement* pass in date and navigate the calendar selecting proper date
        driver.findElement(date_of_birth_input_field).sendKeys(person.getFormattedBirthday());
        // driver.findElement(telephone_input_field).click();
        //driver.findElement(date_of_birth_input_field).sendKeys(Keys.TAB);
        //driver.findElement(telephone_input_field).sendKeys(person.getTelephone());
        // Select gender
        String gender = person.gender;
        if ("MALE".equalsIgnoreCase(gender)) {
            driver.findElement(gender_male_button).click();
        } else if ("FEMALE".equalsIgnoreCase(gender)) {
            driver.findElement(gender_female_button).click();
        } else {
            throw new IllegalArgumentException("Unknown gender: " + gender);
        }
        // Hispanic selection
        driver.findElement(hispanic_button).click();
        select_dropdown(person.hispanicSelection.toString());
        if ("OTHER".equals(person.hispanicSelection.toString())) {
            driver.findElement(other_hispanic_input_field).sendKeys("Other");
        }
        // Race selection
        driver.findElement(race_button).click();
        select_dropdown(person.race.toString());
        if ("OTHER".equals(person.race.toString())) {
            driver.findElement(other_race_input_field).sendKeys("Other");
        }
        // Other stay selection
        driver.findElement(other_stay_button).click();
        select_dropdown(person.otherStay.toString());
        driver.findElement(update_button).click();
        // Issues with the text verification on some selections
        // wait.until(ExpectedConditions.textToBePresentInElementLocated(new_record_saved_prompt, person.relationship.toString()));
        wait.until(ExpectedConditions.elementToBeClickable(ok_button));
        driver.findElement(ok_button).click();
    }
}
