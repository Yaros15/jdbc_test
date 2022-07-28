package org.example.dao;

import org.example.db.DBEngine;
import org.example.model.Customer;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class CustomerDao implements Dao<Customer> {
    private static final String SELECT_QUERY = "SELECT * FROM customer";
    private static final String INSERT_INTO_SQL = "INSERT INTO customers (name, age) VALUES (?, ?)";

    private Connection connection = DBEngine.getConnection();

    @Override
    public Optional<Customer> get(long id) {
        //в каждом методе происходит создание statement или preparedStatement
        return Optional.empty();
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public void save(Customer customer) {

    }

    @Override
    public void update(Customer customer, String[] params) {

    }

    @Override
    public void delete(Customer customer) {

    }
}
