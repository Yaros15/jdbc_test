package org.example.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

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
                initTables();
                initData();
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

    private static void initData() {

    }

    private static void initTables() {
        if (isNeedCreateTables()) {
            ClassLoader classLoader = DBEngine.class.getClassLoader();
            File file = new File(classLoader.getResource("schema.sql").getFile());
            try {
                Scanner scanner = new Scanner(file);

                StringBuffer scriptText = new StringBuffer();
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    scriptText.append(line);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static Boolean isNeedCreateTables() {
        String[] tables = {"customer", "order", "product"};
        //DatabaseMetaData metadata = connection.getMetaData();
        ResultSet resultSet;
        //resultSet = metadata.getTables(null, null, "customer", null);
        return /*resultSet == */null;
    }

    public static Connection closeConnection(){
        /*try {
            connection.close();
            System.out.println("База завершила работу");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        return null;
    }

}
