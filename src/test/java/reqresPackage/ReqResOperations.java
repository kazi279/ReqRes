package reqresPackage;

import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


public class ReqResOperations {

    // What do you do in your api automation work?
    //First we have to read the api documentation for the testing purpose

    // When we have a request body payload, then we have map for the payload
    // We have to convert the mapping object to JSONObject
    // We have to send the specific request
    // After that we are verifying the server response and then validate it by assertion

    public static final String BASE_URL = "https://reqres.in";

    @Test
    public void createUser() {
//         Request Payload/ Request Body
// {
//        "name": "morpheus",
//        "job": "leader"
//  }
        Map<String, String> payload = new HashMap<>();
        payload.put("name", "morpheus");
        payload.put("job", "leader");

        System.out.println(payload);

        JSONObject jsonObject = new JSONObject(payload);
        System.out.println(jsonObject);

        Response res = given().body(jsonObject).contentType("application/json").
                when().post(BASE_URL + "/api/users").
                then().assertThat().statusCode(201).
                log().all().extract().response();
        //when there is a body there must be a content type

        JsonPath jsonPath = res.jsonPath();
        String actualValueOFName = jsonPath.get("name");
        assertEquals(actualValueOFName, "morpheus");

        String actualValueOFJob = jsonPath.get("job");
        assertEquals(actualValueOFJob, "leader");

//        {
//            "username": "test@gmail.com",
//            "password": "123456",
//            "status": 1
//        }

//        Map<String, Object> loginPayload = new HashMap<>();
//        loginPayload.put("username", "test@gmail.com");
//        loginPayload.put("password", "123456");
//        loginPayload.put("status", 1);
    }

    //{
//            "name": "morpheus",
//                "job": "zion resident"
//        }
    @Test
    public void updateUser() {
        Map<String, String> payload = new HashMap<>();
        payload.put("name", "Abir");
        payload.put("job", "Doctor");

        System.out.println(payload);

        JSONObject jsonObject = new JSONObject(payload);

        Response response = given().
                body(jsonObject).
                contentType("application/json").
                when().
                put(BASE_URL + "/api/users/2").
                then().
                assertThat().
                statusCode(200).
                log().all().
                extract().response();

        JsonPath jsonPath = response.jsonPath();
        String actualValueOfName = jsonPath.get("name");
        String actualValueOfJob = jsonPath.get("job");
        assertEquals(actualValueOfName, "Abir");
        assertEquals(actualValueOfJob, "Doctor");


    }

}
