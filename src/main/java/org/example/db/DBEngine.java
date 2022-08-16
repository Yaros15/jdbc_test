package org.example.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class DBEngine {

    private static Connection connection = null;

    private DBEngine() {
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
            } catch (SQLException e){
                e.printStackTrace();
            }
        } else {
            return connection;
        }
        return connection;
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
                System.out.println(scriptText);
                try {
                    Statement statement = connection.createStatement();
                    boolean resultSet = statement.execute(String.valueOf(scriptText));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Создам таблицу");
            initData();
        }
    }

    private static void initData (){
            ClassLoader classLoader = DBEngine.class.getClassLoader();
            File file = new File(classLoader.getResource("data.sql").getFile());
            try{
                Scanner scanner = new Scanner(file);

                StringBuffer scriptText = new StringBuffer();
                while (scanner.hasNext()){
                    String line = scanner.nextLine();
                    scriptText.append(line);
                }
                System.out.println(scriptText);
                Statement statement;
                try {
                    statement = connection.createStatement();
                    int resultSet = statement.executeUpdate(String.valueOf(scriptText));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                /*String[] lineSplit = String.valueOf(scriptText).split(" ;");
                for (String newLine : lineSplit) {
                    System.out.println(newLine);
                }*/
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Таблица заполнина");
        }

    private static Boolean isNeedCreateTables() {
        boolean table;
        DatabaseMetaData metadata = null;
        ResultSet resultSet;
        try {
            metadata = connection.getMetaData();

            resultSet = metadata.getTables(null, null, "customer", null);
            if (resultSet.next()){
                System.out.println("Таблица есть");
                table = false;
            } else {
                System.out.println("Таблицы нет");
                table = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return table;
    }

    public static Connection closeConnection(){
        try {
            connection.close();
            System.out.println("База завершила работу");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
