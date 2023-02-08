package com.kraft.tests.APITests;

import com.kraft.tests.APITests.apipojotemplates.PostRegisterClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class day7_postMethod {

    //POST METHOD
    /*
    Along with the others, there are 3 particular ways to provide data into the request body when we use post method:
        1. Assign the JSON body inside a string variable and put it into the body() method
        2. Put data inside a map and provide it into the body() method
        NOTE:body() method converts the data inside the map to JSON automatically. This only happens with POST,PUT andPATCH method.
        3. Put data into an object which is created based on a java custom class and put it into the body() method.
     */


    //TASK
    /*
    baseUrl = https://www.krafttechexlab.com/sw/api/v1
    endpoint = /allusers/register
    Given accept type and Content type is JSON
    And request json body is:
    {
    "name": "xxx",
    "email": "xxx@xxx.com",
    "password": "xxx"
    }
    When user sends POST request
    Then status code 200
    And content type should be application/json
    And json payload/response/body should contain:
    a new generated id that is special for user
    name
    email
    ...
     */

    //FIRST WAY
    //STRING
    @Test
    public void test1(){

        String jsonBody  = "{\n" +
                "    \"name\": \"xxx\",\n" +
                "    \"email\": \"xxx@xxx.com\",\n" +
                "    \"password\": \"xxx\"\n" +
                "    }";

        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post("https://www.krafttechexlab.com/sw/api/v1/allusers/register");
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.path("name"), "xxx");
        Assert.assertEquals(response.path("email"), "xxx@xxx.com");
        Assert.assertNotNull(response.path("id"));
    }



    //TASK
    /*
    baseUrl = https://www.krafttechexlab.com/sw/api/v1
    endpoint = /allusers/register
    Given accept type and Content type is JSON
    And request json body is:
    {
    "name": "xxxx",
    "email": "xxxx@xxxx.com",
    "password": "xxxx"
    }
    When user sends POST request
    Then status code 200
    And content type should be application/json
    And json payload/response/body should contain:
    a new generated id that is special for user
    name
    email
    ...
     */
    //SECOND WAY
    //MAP
    @Test
    public void test2(){
        Map<String,Object> map = new HashMap<>();
        map.put("name", "xxxx");
        map.put("email", "xxxx@xxxx.com");
        map.put("password", "xxxx");
        System.out.println("map = " + map);

        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(map)
                .post("https://www.krafttechexlab.com/sw/api/v1/allusers/register");
        System.out.println("response.path(\"id\") = " + response.path("id"));
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.path("name"), "xxxx");
        Assert.assertEquals(response.path("email"), "xxxx@xxxx.com");
        Assert.assertNotNull(response.path("id"));
    }

    //TASK
    /*
    baseUrl = https://www.krafttechexlab.com/sw/api/v1
    endpoint = /allusers/register
    Given accept type and Content type is JSON
    And request json body is:
    {
    "name": "xxxxx",
    "email": "xxxxx@xxxxx.com",
    "password": "xxxxx"
    }
    When user sends POST request
    Then status code 200
    And content type should be application/json
    And json payload/response/body should contain:
    a new generated id that is special for user
    name
    email
    ...
     */

    //THIRD WAY
    //JAVA CUSTOM CLASS
    @Test
    public void test3(){
        //PostRegisterClass postRegisterClass = new PostRegisterClass("xxxxx","xxxxx@xxxxx.com", "xxxxx");

        PostRegisterClass postRegisterClass = new PostRegisterClass();
        postRegisterClass.setEmail("xxxxx@xxxxx.com");
        postRegisterClass.setName("xxxxx");
        postRegisterClass.setPassword("xxxxx");

        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(postRegisterClass)
                .when()
                .post("https://www.krafttechexlab.com/sw/api/v1/allusers/register");
        System.out.println("response.path(\"id\") = " + response.path("id"));
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.path("name"), "xxxxx");
        Assert.assertEquals(response.path("email"), "xxxxx@xxxxx.com");
        Assert.assertNotNull(response.path("id"));
    }
}
