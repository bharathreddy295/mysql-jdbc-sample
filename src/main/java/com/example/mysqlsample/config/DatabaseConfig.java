package com.example.mysqlsample.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {
    private static final String PROPS_FILE = "/db.properties";
    private static String url;
    private static String username;
    private static String password;

    static {
        try (InputStream in = DatabaseConfig.class.getResourceAsStream(PROPS_FILE)) {
            Properties props = new Properties();
            props.load(in);
            url = props.getProperty("db.url");
            username = props.getProperty("db.username");
            password = props.getProperty("db.password");
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Failed to load db properties: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
