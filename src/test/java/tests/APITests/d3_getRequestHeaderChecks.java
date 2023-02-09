package tests.APITests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class d3_getRequestHeaderChecks {

    @BeforeClass
    public void setUpAPI(){
        RestAssured.baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void test1(){
        Response response = RestAssured
                .given()
                .pathParam("id", 1)
                .when()
                .get("allusers/getbyid/{id}");
        //verify status code
        Assert.assertEquals(response.statusCode(),200);
        //verify if response headers has content-type
        boolean bool = response.headers().hasHeaderWithName("Content-Type");
        Assert.assertTrue(bool);
        //verify if content-type is equal to application/json; charset=UTF-8
        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType, "application/json; charset=UTF-8");
    }
}
