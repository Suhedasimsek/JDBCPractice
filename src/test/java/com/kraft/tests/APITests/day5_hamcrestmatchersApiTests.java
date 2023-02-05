package com.kraft.tests.APITests;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class day5_hamcrestmatchersApiTests {

    //No TestNG
    //We will be using rest assured assertions(Matchers class) for the verification
    //No response body
    //Matchers is an assertion library for verification, coming from RestAssured
    //We will be using gpath syntax
    //   1. Matchers.equalTo("abc")
    //   2. Matchers.notNullValue()
    //   3. Matchers.hasItems("abc", "def")
    //Good to know for interview purposes


    //TASK
    //baseUrl = https://www.krafttechexlab.com/sw/api/v1
    //get the first user from {{baseUrl}}/allusers/getbyid/1
    //status code is equal to 200
    //verify id, name, email and location with the hamcrestmatchers

    @Test
    public void test1(){
        RestAssured
                .given()
                .pathParam("id",1)
                .when()
                .get("https://www.krafttechexlab.com/sw/api/v1/allusers/getbyid/{id}")
                .then()
                .statusCode(200)
                .and()
                .body("id[0]", Matchers.equalTo(1),
                        "name[0]", Matchers.equalTo("MercanS"),
                        "email[0]", Matchers.equalTo("afmercan@gmail.com"));
    }

    //TASK
    //baseUrl = https://www.krafttechexlab.com/sw/api/v1
    //get the first user from {{baseUrl}}/allusers/getbyid/1
    //verify response headers with the hamcrestmatchers
    //verify that the company equals to "Mrcn Software" with the hamcrestmatchers (inside body)

    @Test
    public void test2(){
        RestAssured
                .given()
                .pathParam("id",1)
                .when()
                .get("https://www.krafttechexlab.com/sw/api/v1/allusers/getbyid/{id}")
                .then()
                .statusCode(200)
                .and()
                .header("Content-Type", Matchers.equalTo("application/json; charset=UTF-8"))
                .and()
                .header("date", Matchers.notNullValue())
                .and()
                .body("company[0]", Matchers.equalTo("Mrcn Software"));
    }


    //TASK
    //baseUrl = https://www.krafttechexlab.com/sw/api/v1
    //end point /allusers/alluser
    //query parameter 1. page = 1
    //query parameter 2. pagesize = 5
    //send a request to get first 5 users and their features
    //verify that the names contains MercanS and Sebastian with hamcrestmatchers

    @Test
    public void test3(){
        RestAssured
                .given()
                .queryParams("page",1)
                .queryParams("pagesize", 5)
                .when()
                .get("https://www.krafttechexlab.com/sw/api/v1/allusers/alluser")
                .then()
                .statusCode(200)
                .and()
                .body("name", Matchers.hasItems("MercanS","Sebastian"));
    }
}
