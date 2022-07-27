package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final String URL = "jdbc:postgresql://localhost:5432/one_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private static final String ADD_A_CLIENT = "Добавить";
    private static final String DELETE_A_CLIENT = "Удалить";
    private static final String SHOW_CUSTOMERS = "Показать";
    private static final String CLOSED_THE_TABLE = "Закрыть";

    private static final String CREATE_SQL = "CREATE TABLE customers " +
            "(id SERIAL PRIMARY KEY, name TEXT, age INTEGER)";
    private static final String INSERT_INTO_SQL = "INSERT INTO customers (name, age) ";
    private static final String DELETE_FROM = "DELETE FROM customers WHERE id = ";
    private static final String SELECT_FROM = "SELECT * FROM customers";

    public static Connection connection;
    public static Statement statement;

    public static boolean exit = false;

    public static void main(String[] args) {

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("База готова к работе");
            statement = connection.createStatement();

            do {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Напишите что сделать в базе: " + ADD_A_CLIENT + " " + DELETE_A_CLIENT + " "
                        + SHOW_CUSTOMERS + " " + CLOSED_THE_TABLE + ": ");
                String text = scanner.nextLine();

                if (text.equals(ADD_A_CLIENT)){

                    System.out.println("Введите имя: ");
                    String name = scanner.nextLine();
                    System.out.println("Введите возраст: ");
                    int age = scanner.nextInt();

                    statement.executeUpdate(INSERT_INTO_SQL + "VALUES ( '" + name + "' ," + age + ")");
                    System.out.println("добавлен новый пользователь: "+ name + " " + age);
                }

                if (text.equals(DELETE_A_CLIENT)){

                    System.out.println("Введите id пользователя: ");
                    int id = scanner.nextInt();
                    statement.executeUpdate(DELETE_FROM + id);
                    System.out.println("Удален 1 пользователь");
                }

                if (text.equals(SHOW_CUSTOMERS)){
                    ResultSet resultSet = statement.executeQuery(SELECT_FROM);
                    while (resultSet.next()){
                        System.out.println(resultSet.getInt("id") + " "
                                + resultSet.getString("name") + " "
                                + resultSet.getInt("age"));
                    }
                }

                if (text.equals(CLOSED_THE_TABLE)){
                    System.out.println(CLOSED_THE_TABLE);
                    exit = true;
                }

            }while (exit == false);

            System.out.println("База завершила работу");
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}