package tests.APITests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class d4_getAllUsersWithJsonPath {

    /*
    //REQUEST
    baseUrl = https://www.krafttechexlab.com/sw/api/v1
    endpoint = /allusers/alluser
    query param page --> 1
    query param pagesize -->10


    //RESPONSE
    then status code should be 200
    and response content type:application/json;charset=UTF-8
     */

    @Test
    public void getAllUsers(){

        Response response = RestAssured
                .given()
                .queryParams("page", 1)
                .queryParams("pagesize", 10)
                .when()
                .get("https://www.krafttechexlab.com/sw/api/v1/allusers/alluser");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");

        //get the first Id
        int id = response.path("id[0]");
        System.out.println("id = " + id);

        //get the first Id with jsonpath
        JsonPath jsonPath = response.jsonPath();
        int idWithJsonPath = jsonPath.getInt("id[0]");
        System.out.println("idWithJsonPath = " + idWithJsonPath);

        //get the second id
        int secondId = jsonPath.getInt("id[1]");
        System.out.println("secondId = " + secondId);

        //get all names
        List<String> names = jsonPath.getList("name");
        System.out.println("names = " + names);

        //get all IDs
        List<Object> allIds = jsonPath.getList("id");
        System.out.println("allIds = " + allIds);

        //get the skills of first user
        List<Object> list = jsonPath.getList("skills[0]");
        System.out.println("list = " + list);
        System.out.println("list.get(0) = " + list.get(0));
        System.out.println("list.get(1) = " + list.get(1));

        //get the first skill of first user
        String firstSkillOfFirstUser = jsonPath.get("skills[0][0]");
        System.out.println("firstSkillOfFirstUser = " + firstSkillOfFirstUser);
        //get the second skill of first user
        String secondskillOfFirstUser = jsonPath.getString("skills[0][1]");
        System.out.println("secondskillOfFirstUser = " + secondskillOfFirstUser);

        //get first id of first education
        int firstIdOfFirstEducation = jsonPath.get("education[0].id[0]");
        System.out.println("firstIdOfFirstEducation = " + firstIdOfFirstEducation);

        //second way
        Map<String,Object> map = jsonPath.get("education[0][0]");
        System.out.println("map = " + map);
        System.out.println("map.get(\"id\") = " + map.get("id"));
    }
}
