package Api;

import Data.Person;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiClient {
    private static String baseURI = "http://localhost:3000";
    private static String email = "fakeJosh.king@email.com";
    private static String password = "fakePassword1";

    public ApiClient(String baseURI) {
        this.baseURI = baseURI;
        this.email = email;
        this.password = password;
    }

    public Map<String, String> Login() {
        //
        String signInPath = "/api/auth/login";
        String signInBody = "{\"email\": \"" + email + "\",\"password\": \"" + password + "\"}";

        // Send POST request to login
        Response response = given()
                .contentType(ContentType.JSON)
                .body(signInBody)
                .post(baseURI + signInPath);

        // Extract cookies
        return response.getCookies();
    }

    public static void getRecordsByEmail(String callBackUrl, String csrfToken, String sessionToken, Person person) {
        String getRecordQuery = baseURI + "/api/record/user/email/" + email;
        String cookie = "authjs.callback-url=" + callBackUrl + "; authjs.csrf-token=" + csrfToken + "; authjs.session-token=" + sessionToken;
        Response response = given()
                .contentType(ContentType.JSON)
                .cookie("Cookie:", cookie)
                .get(getRecordQuery);
        // System.out.println(response.getBody().asString());
        /* JsonPath jsonPath = response.getBody().jsonPath();
        List<Map<String, Object>> records = jsonPath.getList("records");
        for (Map<String, Object> record : records) {
            System.out.println("Record: " + record);
        }*/
        response.then()
                .statusCode(200)
                .body("records[0].firstName", equalTo("FAKEJosh"))
                .body("records[1].firstName", equalTo(person.firstName))
                .body("records[1].lastName", equalTo(person.lastName));


    }

    /*public static void main(String[] args) {
        ApiClient apiClient = new ApiClient(baseURI);
        Map<String, String> cookies = apiClient.Login();
        String csrfToken = cookies.get("authjs.csrf-token");
        String callbackUrl = cookies.get("authjs.callback-url");
        String sessionToken = cookies.get("authjs.session-token");
        if (cookies != null) {
            apiClient.getRecordsByEmail(callbackUrl, csrfToken, sessionToken);
        } else {
            System.out.println("Cookie not found!");
        }
    } */


}
