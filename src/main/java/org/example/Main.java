package org.example;

import org.example.db.DBEngine;
import org.example.model.Customer;

import java.sql.*;
import java.text.Format;
import java.util.Scanner;

public class Main {

    private static final String URL = "jdbc:postgresql://localhost:5432/one_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private static final String ADD_A_CLIENT = "Добавить";
    private static final String DELETE_A_CLIENT = "Удалить";
    private static final String SHOW_CUSTOMERS = "Показать";
    private static final String CLOSED_THE_TABLE = "Закрыть";

    private static final String CREATE_SQL = "CREATE TABLE customer " +
            "(id SERIAL PRIMARY KEY, name TEXT, age INTEGER)"; //Скрипты инициализации БД должны быть в отдельном файле
    private static final String INSERT_INTO_SQL = "INSERT INTO customers (name, age) VALUES (?, ?)";
    private static final String DELETE_FROM = "DELETE FROM customers WHERE id = ";
    private static final String SELECT_FROM = "SELECT * FROM customers";

    public static Connection connection;
    public static Statement statement;

    public static boolean exit = false;

    public static void main(String[] args) {
        Connection connection1 = DBEngine.getConnection();
        return;
/*
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("База готова к работе");
            statement = connection.createStatement();

            do {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Напишите что сделать в базе: " + ADD_A_CLIENT + " " + DELETE_A_CLIENT + " "
                        + SHOW_CUSTOMERS + " " + CLOSED_THE_TABLE + ": ");
                String text = scanner.nextLine();

                if (text.equals(ADD_A_CLIENT)) {

                    System.out.println("Введите имя: ");
                    String name = scanner.nextLine();
                    System.out.println("Введите возраст: ");
                    int age = scanner.nextInt();

                    statement.executeUpdate(INSERT_INTO_SQL + "VALUES ( '" + name + "' ," + age + ")");
                    System.out.println("добавлен новый пользователь: " + name + " " + age);
                }

                if (text.equals(DELETE_A_CLIENT)) {

                    System.out.println("Введите id пользователя: ");
                    int id = scanner.nextInt();
                    statement.executeUpdate(DELETE_FROM + id);
                    System.out.println("Удален 1 пользователь");
                }

                if (text.equals(SHOW_CUSTOMERS)) {
                    ResultSet resultSet = statement.executeQuery(SELECT_FROM);
                    while (resultSet.next()) {
                        Customer customer = new Customer();
                        customer.setId(resultSet.getInt("id"));
                        customer.setAge(resultSet.getInt("age"));
                        customer.setName(resultSet.getString("name"));
                        System.out.println(customer);
                    }
                }

                if (text.equals(CLOSED_THE_TABLE)) {
                    System.out.println(CLOSED_THE_TABLE);
                    exit = true;
                }

            } while (exit == false);

            System.out.println("База завершила работу");
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
    }

    private static void initDatabase() {

    }

    private static Boolean checkIfTableExist(String tableName) {
        connection.getMetaData().getTables(null, null, tableName, null);
    }
}