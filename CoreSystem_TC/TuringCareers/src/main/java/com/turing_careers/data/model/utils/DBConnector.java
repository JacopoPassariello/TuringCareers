package com.turing_careers.data.model.utils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnector {
    private static DataSource datasource;
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/turing_careers";
    private static Connection conn = null;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcURL, "root", "tc_root");
        } catch (SQLException e) {
            System.out.println("Connection Failed \n");
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println("It was impossible to find the DB driver \n");
            System.out.println(e);
        }
        return conn;
    }
}

