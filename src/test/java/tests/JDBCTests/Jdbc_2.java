package tests.JDBCTests;

import java.sql.*;

public class Jdbc_2 {

    public static void main(String[] args) throws SQLException {

        String dbUrl = "";
        String dbUsername = "";
        String dbPassword = "";

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername,dbPassword);

        //create statement object
        Statement statement = connection.createStatement();

        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select firstName, lastName from tableName");

        //move first row
        resultSet.next();
        System.out.println(resultSet.getString(1));
        System.out.println(resultSet.getString("lastName"));



        System.out.println("----------------------------------------------------");
        //create a new query
        ResultSet resultSet1 = statement.executeQuery("select email from tableName");

        //move first row
        resultSet1.next();
        System.out.println(resultSet1.getString(1));


        //close the instance
        resultSet.close();
        statement.close();
        connection.close();
    }

}
