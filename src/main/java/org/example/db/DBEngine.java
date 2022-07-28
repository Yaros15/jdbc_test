package org.example.db;

import java.sql.Connection;
import java.util.ResourceBundle;

public class DBEngine {

    private static Connection connection = null;

    private DBEngine() {

    }

    public static Connection getConnection() {
        if (connection == null) {
            ResourceBundle resource = ResourceBundle.getBundle("database");
            // логика по подготовке коннекшена
        } else {
            return connection;
        }
        return null;
    }
}
