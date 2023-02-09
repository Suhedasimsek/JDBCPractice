package tests.JDBCTests;

import org.testng.annotations.Test;

import java.sql.*;

public class Jdbc_3 {

    @Test
    public void someMethodForMovingCursor() throws SQLException {
        String dbUrl = "";
        String dbUsername = "";
        String dbPassword = "";

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername,dbPassword);

        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from tableName");

        //ResultSet.TYPE_SCROLL_INSENSITIVE --> pointer ile serbestçe aşağı yukarı gidilebilir
        //ResultSet.CONCUR_READ_ONLY --> read only, do not update database

        resultSet.next();
        resultSet.next();
        //hangi satırdayım?
        System.out.println("resultSet.getRow() = " + resultSet.getRow());

        //kaç satır olduğunu nasıl öğreneceğim?
        resultSet.last();
        System.out.println("resultSet.getRow() = " + resultSet.getRow());

        //bir önceki satıra nasıl gideceğim?
        resultSet.previous();
        System.out.println("resultSet.getRow() = " + resultSet.getRow());

        //sütun isimlerinin olduğu satıra gitme
        resultSet.beforeFirst();
        System.out.println("resultSet.getRow() = " + resultSet.getRow());

        //istenilen satıra gitme
        resultSet.absolute(10);
        System.out.println("resultSet.getRow() = " + resultSet.getRow());

        //close the instance
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void dataBaseMetaDataExample() throws SQLException {
        String dbUrl = "";
        String dbUsername = "";
        String dbPassword = "";

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername,dbPassword);

        //create statement object
        //Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        //run query and get the result in resultset object
        //ResultSet resultSet = statement.executeQuery("select * from employeesEren");

        DatabaseMetaData databaseMetaData = connection.getMetaData();
        System.out.println("databaseMetaData.getUserName() = " + databaseMetaData.getUserName());
        System.out.println("databaseMetaData.getDatabaseProductName() = " + databaseMetaData.getDatabaseProductName());
        System.out.println("databaseMetaData.getDatabaseProductVersion() = " + databaseMetaData.getDatabaseProductVersion());
        System.out.println("databaseMetaData.getDriverName() = " + databaseMetaData.getDriverName());
        System.out.println("databaseMetaData.getDriverVersion() = " + databaseMetaData.getDriverVersion());


        //close the instance
        //resultSet.close();
        //statement.close();
        connection.close();
    }


    @Test
    public void resultSetMetadataExample() throws SQLException {
        String dbUrl = "";
        String dbUsername = "";
        String dbPassword = "";

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername,dbPassword);

        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from tableName");

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        //kaç tane sütunumuz var?
        int columnCount = resultSetMetaData.getColumnCount();
        System.out.println("columnCount = " + columnCount);

        //sütun isimleri
        String columnName = resultSetMetaData.getColumnName(5);
        System.out.println("columnName = " + columnName);

        //bütün sütun isimlerini dinamik olarak getir
        for (int i = 1; i<= columnCount ; i++){
            System.out.println("resultSetMetaData.getColumnName(" + i + ") = " + resultSetMetaData.getColumnName(i));
        }

        //close the instance
        resultSet.close();
        statement.close();
        connection.close();
    }
}
