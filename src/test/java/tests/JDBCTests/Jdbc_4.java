package tests.JDBCTests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jdbc_4 {

    String dbUrl = "";
    String dbUsername = "";
    String dbPassword = "";

    @Test
    public void listOfMapExample(){
    Map<String,Object> row1 = new HashMap<>();
    row1.put("employeeId", 1000);
    row1.put("firstName", "Eren");
    row1.put("lastName", "Çengel");
    row1.put("salary", 100000);
    System.out.println("row1 = " + row1);

    Map<String, Object> row2 = new HashMap<>();
    row2.put("employeeId", 1001);
    row2.put("firstName", "Alperen");
    row2.put("lastName", "Çengel");
    row2.put("salary", 150000);
    System.out.println("row2 = " + row2);

    //list for keeping all rows in a map
    List<Map<String,Object>> list = new ArrayList<>();
    list.add(row1);
    list.add(row2);

    System.out.println("list = " + list);
    }

    @Test
    public void listOfMapExampleViaDataBase() throws SQLException {

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from tableName");

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        //move to first row
        resultSet.next();
        Map<String, Object> row1 = new HashMap<>();
        row1.put(resultSetMetaData.getColumnName(1), resultSet.getString(1));
        row1.put(resultSetMetaData.getColumnName(2),resultSet.getString(2));
        row1.put(resultSetMetaData.getColumnName(3),resultSet.getString(3));
        row1.put(resultSetMetaData.getColumnName(4),resultSet.getString(4));
        //System.out.println("row1 = " + row1);

        //move to next row
        resultSet.next();
        Map<String, Object> row2 = new HashMap<>();
        row2.put(resultSetMetaData.getColumnName(1),resultSet.getString(1));
        row2.put(resultSetMetaData.getColumnName(2),resultSet.getString(2));
        row2.put(resultSetMetaData.getColumnName(3),resultSet.getString(3));
        row2.put(resultSetMetaData.getColumnName(4),resultSet.getString(4));
        //System.out.println("row2 = " + row2);

        List<Map<String, Object>> list = new ArrayList<>();
        list.add(row1);
        list.add(row2);
        System.out.println("list = " + list);
        System.out.println("");
        System.out.println("list.get(0) = " + list.get(0));
        System.out.println("list.get(0).get(\"EMPLOYEEID\") = " + list.get(0).get("EMPLOYEEID"));


        resultSet.close();
        statement.close();
        connection.close();
    }

}
