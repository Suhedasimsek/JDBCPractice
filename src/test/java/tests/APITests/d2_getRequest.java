package tests.APITests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class d2_getRequest {

    String kraftUrl = "https://www.krafttechexlab.com/sw/api/v1";

    @Test
    public void simpleGetRequest(){
        //make api call
        Response response = RestAssured
                .given()
                .queryParams("page", 1)
                .queryParams("pagesize", 3)
                .when()
                .get(kraftUrl + "/allusers/alluser");

        System.out.println(response.statusCode());
        response.prettyPrint();
    }

    @Test
    public void simpleGetRequest2(){
        //make api call
        Response response = RestAssured
                                    .when()
                                    .get(kraftUrl + "/allusers/getbyid/1");
        //verification
        Assert.assertEquals(response.statusCode(),200);
        //first way to check response body
        //response.body().asString()
        //verify that response body has "aFm"
        Assert.assertTrue(response.body().asString().contains("aFm"));
        Assert.assertTrue(response.prettyPrint().contains("aFm"));
    }

    @Test
    public void simpleGetRequest3_HamCrestMatchers(){
        //make api call
        RestAssured
                .when()
                .get(kraftUrl + "/allusers/getbyid/1")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json; charset=UTF-8");
    }
}
