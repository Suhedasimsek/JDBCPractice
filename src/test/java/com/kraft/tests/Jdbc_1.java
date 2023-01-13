package com.kraft.tests;

import java.sql.*;

public class Jdbc_1 {

    public static void main(String[] args) throws SQLException {

        String dbUrl = "";
        String dbUsername = "";
        String dbPassword = "";

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername,dbPassword);

        //create statement object
        Statement statement = connection.createStatement();

        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from tableName");

        //normalde resultset default olarak sütun satırına işaret etmektedir
        //aşağıya inmek için resultSet().next() methodu kullanılır

        //move pointer to the first row
        //sütun sayıları 1'den başlar
        //ilgili sütuna index number ile ulaşma
        resultSet.next();
        System.out.println(resultSet.getString(1));
        System.out.println(resultSet.getString(2));
        System.out.println(resultSet.getString(3));

        System.out.println("");

        //move pointer second row
        resultSet.next();
        System.out.println(resultSet.getString(1));
        System.out.println(resultSet.getString(2));
        System.out.println(resultSet.getString(3));

        System.out.println("");

        //move pointer third row
        //ilgili sütuna sütun adı ile ulaşma
        resultSet.next();
        System.out.println(resultSet.getString("email"));


        //close the instance
        resultSet.close();
        statement.close();
        connection.close();
    }

}
