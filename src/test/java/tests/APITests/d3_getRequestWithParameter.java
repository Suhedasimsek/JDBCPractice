package tests.APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class d3_getRequestWithParameter {

    String kraftUrl = "https://www.krafttechexlab.com/sw/api/v1";

    @Test
    public void getRequest_PathParameter(){
        //old way
        Response response = RestAssured
                .when()
                .get(kraftUrl + "/allusers/getbyid/1");
        //how to provide path parameter inside request
        //new way
        Response response1 = RestAssured
                .given()
                .pathParam("id", 1)
                .and()
                .accept(ContentType.JSON)
                .when()
                .get(kraftUrl + "/allusers/getbyid/{id}");
        Assert.assertEquals(response1.statusCode(),200);
        Assert.assertTrue(response1.body().asString().contains("" +
                "afmercan@gmail.com"));
    }

    @Test
    public void getRequest_QueryParameter(){
        Response response = RestAssured
                .given()
                .queryParams("pagesize", 10)
                .queryParams("page", 1)
                .when()
                .get(kraftUrl + "/allusers/alluser");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.body().asString().contains("Manual Tester"));
    }

    @Test
    public void getRequest_QueryParameterWithMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("pagesize", 10);
        map.put("page", 1);

        Response response = RestAssured
                .given()
                //.queryParams("pagesize", 10)
                //.queryParams("page", 1)
                .queryParams(map)
                .when()
                .get(kraftUrl + "/allusers/alluser");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.body().asString().contains("Manual Tester"));
    }

}
