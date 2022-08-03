package org.example.db;

import java.sql.*;
import java.util.ResourceBundle;

public class DBEngine {

    private static Connection connection = null;

    private DBEngine() { // лишаем возможности наследования класса
    }

    public static Connection getConnection() {
        if (connection == null) {
            ResourceBundle resource = ResourceBundle.getBundle("database");
            String url = resource.getString("db.url");
            String user = resource.getString("db.user");
            String password = resource.getString("db.password");

            try {
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("База готова к работе");

                /*DatabaseMetaData metadata = connection.getMetaData();
                ResultSet resultSet;
                resultSet = metadata.getTables(null, null, "customer", null);*/

            } catch (SQLException e){
                e.printStackTrace();
            }
            // логика по подготовке коннекшена
        } else {
            return connection;
        }
        return connection; //null;
    }

    public static Connection getClose (){
        try {
            connection.close();
            System.out.println("База завершила работу");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
