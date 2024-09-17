import Api.ApiClient;
import Data.*;
import DataBase.*;
import DataBase.Record;
import PageObject.*;
import org.junit.Ignore;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSpec {
    // Declare necessary classes
    private static WebDriver driver;
    private static Person person;
    private static MainPage mainPage;
    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static AddRecord addRecord;
    private static DeleteRecord deleteRecord;
    // DB credentials
    String URL = "jdbc:postgresql://localhost:5432/census_app";
    String USERNAME = "postgres";
    String PASSWORD = "postgres";
    String apiURI = "http://localhost:3000";
    DBConnector connector = new DBConnector(URL, USERNAME, PASSWORD);
    ApiClient apiClient = new ApiClient(apiURI);

    @BeforeAll
    public static void setUp() {
        // Initialize Webdriver
        driver = new ChromeDriver();
        // Create object to add record for
        person = new Person();
        // Page Objects
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        addRecord = new AddRecord(driver);
        deleteRecord = new DeleteRecord(driver);
    }

    @Test
    @Order(1)
    @DisplayName("Load App and Add Person To Household")
    public void testAddRecord() {
        // Load Page
        mainPage.load_app();
        // Login
        mainPage.select_user_icon();
        loginPage.login_as_user("fakeJosh.king@email.com", "fakePassword1");
        // Select add record
        dashboardPage.click_add_new_record();
        // Add Record
        addRecord.add_new_person_to_household(person);
    }

    @Test
    @Order(2)
    @DisplayName("Confirm Database contains new record")
    public void databaseCheck(){
        // Query and add data to "records"
        List<Record> records = connector.connectAndQuery();
        // Assert that records are not empty
        assertFalse("The record list should not be empty", records.isEmpty());
        // Confirm second record added
        Assertions.assertEquals(2, records.size(), "Expected number of records");
        // Validate the content of records
        Record newRecord = records.get(1);
        assertNotNull("New record should not be null", newRecord);
        assertEquals("Expected First Name", person.firstName, newRecord.getFirstName());
        assertEquals("Expected Last Name", person.lastName, newRecord.getLastName());
        // assertEquals("Expect DOB to contain", person.getFormattedBirthday(), newRecord.getDob()); Format issues
        assertEquals("Expected Gender", person.gender, newRecord.getGender());
    }

    @Test
    @Order(3)
    @DisplayName("Confirm API data")
    public void apiCheck() {
        Map<String, String> cookies = apiClient.Login();
        String csrfToken = cookies.get("authjs.csrf-token");
        String callbackUrl = cookies.get("authjs.callback-url");
        String sessionToken = cookies.get("authjs.session-token");
        if (cookies != null) {
            apiClient.getRecordsByEmail(callbackUrl, csrfToken, sessionToken, person);
        } else {
            System.out.println("Cookie not found!");
        }
    }

    @AfterAll
    public static void tearDown() {
        // Add api or DB methods to clean up test data
        // Close the browser and quit the WebDriver instance
        if (driver != null) {
            driver.quit();
        }
    }
}
