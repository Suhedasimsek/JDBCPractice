package tests.APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class d7_patchMethod {

    //PATCH
    //TASK

    /*
    REGISTER A NEW USER
    baseUrl = https://www.krafttechexlab.com/sw/api/v1
    endpoint = /allusers/register
    Given accept type and Content type is JSON
    And request json body is:
       {
      "name": "xxxxxx",
      "email": "xxxxxx@xxxxxx.com",
      "password": "xxxxxx",
       }
    */

    /*
    LOGIN WITH THE SAME USER AND GET THE TOKEN
    Base Url = https://www.krafttechexlab.com/sw/api/v1
    End Point = /allusers/login
    email = xxxxxx@xxxxxx.com
    password = xxxxxx
    Given request body should have email and password as multipart/form-data
    Get the token has been got by response body and assign this token to a global String
    */

    /*
    UPDATE SAME USER
    PATCH METHOD
    baseUrl = https://www.krafttechexlab.com/sw/api/v1
    endpoint = /user/update
    TOKEN SHOULD BE PROVIDED AS A HEADER TO BE ABLE TO SEND A REQUEST SUCCESSFULLY
    THE BODY THAT IS GOING TO BE UPDATED SHOULD BE PROVIDED AS JSON
    name should be changed as aslıhan
    */

    @Test
    public void test(){
        Map<String,Object> map = new HashMap<>();
        map.put("name", "xxxxxx");
        map.put("email", "xxxxxx@xxxxxx.com");
        map.put("password", "xxxxxx");
        System.out.println("map = " + map);

        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(map)
                .post("https://www.krafttechexlab.com/sw/api/v1/allusers/register");

        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("Register ends here");
        System.out.println("Let's proceed with login");
        System.out.println("");

        String token;
        Response response1 = RestAssured
                .given()
                .contentType("multipart/form-data")
                .multiPart("email", "xxxxxx@xxxxxx.com")
                .multiPart("password", "xxxxxx")
                .when()
                .post("https://www.krafttechexlab.com/sw/api/v1/allusers/login");

        token = response1.path("token");
        System.out.println("response1.statusCode() = " + response1.statusCode());
        System.out.println("login ends here");
        System.out.println("Let's move forward with patch method");
        System.out.println("");

        //update "xxxxxx" as "aslıhan"
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "Aslıhan");
        Response response2 = RestAssured
                .given()
                .header("token", token)
                .body(map1)
                .when()
                .patch("https://www.krafttechexlab.com/sw/api/v1/user/update");
        System.out.println("response2.statusCode() = " + response2.statusCode());

    }

}
