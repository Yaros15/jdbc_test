package org.example.dao;

import org.example.db.DBEngine;
import org.example.model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDao implements Dao<Customer> {

    private static final String SELECT_QUERY = "SELECT * FROM customer";
    private static final String SELECT_QUERY1 = "SELECT * FROM customer WHERE id = ?";
    private static final String INSERT_INTO_SQL = "INSERT INTO customer (name, age) VALUES (?, ?)";
    private static final String UPDATE_SET = "UPDATE customer SET name = ?, age = ? WHERE id = ?";
    private static final String DELETE_FROM = "DELETE FROM customer WHERE id = ?";

    @Override
    public Optional<Customer> get(Customer customer/*long id*/) {

        try {
            PreparedStatement preparedStatement = DBEngine.getConnection().prepareStatement(SELECT_QUERY1);
            preparedStatement.setInt(1, customer.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Customer client = new Customer();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setAge(resultSet.getInt("age"));
                /*System.out.println(resultSet.getInt("id") + " "
                        + resultSet.getString("name") + " "
                        + resultSet.getInt("age"));*/
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Customer> getAll() {
        ArrayList <Customer> pup = new ArrayList<>();
        try {
            Statement statement = DBEngine.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_QUERY);
            while (resultSet.next()){
                Customer client = new Customer();
                 client.setId(resultSet.getInt("id"));
                 client.setName(resultSet.getString("name"));
                 client.setAge(resultSet.getInt("age"));
                pup.add(client);
            }
            System.out.println("summa clientov" + pup);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pup;
    }

    @Override
    public void save(Customer customer) {
        try {
            PreparedStatement preparedStatement = DBEngine.getConnection().prepareStatement(INSERT_INTO_SQL);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setInt(2, customer.getAge());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer /*, String[] params*/) {
        try {
            PreparedStatement preparedStatement = DBEngine.getConnection().prepareStatement(UPDATE_SET);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setInt(2, customer.getAge());
            preparedStatement.setInt(3, customer.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Customer customer) {
        try {
            PreparedStatement preparedStatement = DBEngine.getConnection().prepareStatement(DELETE_FROM);
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
