package com.iudigital.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    private final static String URL = "jdbc:postgresql://localhost:5432/funcionarioApp";
    private final static String USER = "postgres";
    private final static String PASS = "acertijo21";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
    
}
