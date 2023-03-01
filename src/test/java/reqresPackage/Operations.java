package reqresPackage;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

public class Operations {
    public static final String BASE_URL = "https://reqres.in";
    public static String nameValue = "Asif";
    public static String jobValue = "Teacher";
    public static String email = "eve.holt@reqres.in";
    public static String password = "pistol222";


    @Test
    public void updateAnUser() {
        Map<String, String> payload = new HashMap<>();
        payload.put("name", nameValue);
        payload.put("job", jobValue);

        System.out.println(payload);

        JSONObject jsonObject = new JSONObject(payload);

        Response response = given().
                body(jsonObject).
                contentType("application/json").
                when().
                patch(BASE_URL + "/api/users/2").
                then().
                assertThat().
                statusCode(200).
                log().all().
                extract().response();

        JsonPath jsonPath = response.jsonPath();
        String actualValueOfName = jsonPath.get("name");
        String actualValueOfJob = jsonPath.get("job");
        assertEquals(actualValueOfName, nameValue);
        assertEquals(actualValueOfJob, jobValue);

    }

    @Test
    public void deleteUser() {
        Response response = when().
                delete(BASE_URL + "/api/users/2").
                then().
                assertThat().
                statusCode(204).
                log().all().
                extract().response();

    }
    @Test
    public void registrationSuccessful(){
        Map<String, String> payload = new HashMap<>();
        payload.put("email", email);
        payload.put("password", password);

        System.out.println(payload);

        JSONObject jsonObject = new JSONObject(payload);

        Response response = given().
                body(jsonObject).
                contentType("application/json").
                when().
                post(BASE_URL + "/api/register").
                then().
                assertThat().
                statusCode(200).
                log().all().
                extract().response();

    }
}
