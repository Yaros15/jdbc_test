package org.example.dao;

import org.example.db.DBEngine;
import org.example.model.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class OrderDao  implements Dao<Order> {

    private static final String SELECT_QUERY = "SELECT * FROM order";
    private static final String INSERT_INTO_SQL = "INSERT INTO order (foreign_customer, foreign_product) VALUES (?, ?)";
    private static final String UPDATE_SET = "UPDATE order SET foreign_customer = ?, foreign_product = ? WHERE id = ?";
    private static final String DELETE_FROM = "DELETE FROM order WHERE id = ?";

    @Override
    public Optional<Order> get(Order order/*long id*/) {
        //в каждом методе происходит создание statement или preparedStatement
        return Optional.empty();
    }

    @Override
    public List<Order> getAll() {
        try {
            Statement statement = DBEngine.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_QUERY);
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id") + " "
                        + resultSet.getInt("foreign_customer") + " "
                        + resultSet.getInt("foreign_product"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Order order) {
        try {
            PreparedStatement preparedStatement = DBEngine.getConnection().prepareStatement(INSERT_INTO_SQL);
            preparedStatement.setInt(1, order.getForeignCustomer());
            preparedStatement.setInt(2, order.getForeignProduct());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Order order/*, String[] params*/) {
        try {
            PreparedStatement preparedStatement = DBEngine.getConnection().prepareStatement(UPDATE_SET);
            preparedStatement.setInt(1, order.getForeignCustomer());
            preparedStatement.setInt(2, order.getForeignProduct());
            preparedStatement.setInt(3, order.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Order order) {
        try {
            PreparedStatement preparedStatement = DBEngine.getConnection().prepareStatement(DELETE_FROM);
            preparedStatement.setInt(1, order.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
