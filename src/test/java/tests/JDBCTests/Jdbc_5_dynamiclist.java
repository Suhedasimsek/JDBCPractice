package tests.JDBCTests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.*;


public class Jdbc_5_dynamiclist {

    String dbUrl = "";
    String dbUsername = "";
    String dbPassword = "";

    @Test
    public void dynamicList() throws SQLException {

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from tableName");

        //reach number of column and name of columns
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        //create a list
        List<Map<String,Object>> list = new ArrayList<>();

        while (resultSet.next()){
            Map<String,Object> map = new HashMap<>();

            for(int i = 1; i<resultSetMetaData.getColumnCount()+1; i++){
                map.put(resultSetMetaData.getColumnName(i),resultSet.getString(i));
            }

        list.add(map);
        }

        System.out.println("list = " + list);


        //close the connection
        resultSet.close();
        statement.close();
        connection.close();
    }

}
