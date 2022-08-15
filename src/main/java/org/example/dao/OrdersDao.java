package org.example.dao;

import org.example.db.DBEngine;
import org.example.model.Orders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrdersDao implements Dao<Orders> {

    private static final String SELECT_QUERY = "SELECT customer.name_customer, product.name_product, product.price FROM orders " +
            "INNER JOIN customer ON customer.id = orders.customer_id " +
            "INNER JOIN product ON product.id = orders.product_id  " +
            "ORDER BY customer.name_customer, product.price";
    private static final String INSERT_INTO_SQL = "INSERT INTO orders (customer_id, product_id) VALUES (?, ?)";
    private static final String UPDATE_SET = "UPDATE orders SET customer_id = ?, product_id = ? WHERE id = ?";
    private static final String DELETE_FROM = "DELETE FROM orders WHERE id = ?";

    @Override
    public List<Orders> getAll() {
        ArrayList<Orders> orders = new ArrayList<>();
        try {
            Statement statement = DBEngine.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_QUERY);
            while (resultSet.next()){
                Orders newOrders = new Orders();
                newOrders.setCustomerName(resultSet.getString("name_customer"));
                newOrders.setProductName(resultSet.getString("name_product"));
                newOrders.setProductPrice(resultSet.getDouble("price"));
                orders.add(newOrders);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void save(Orders order) {
        try {
            PreparedStatement preparedStatement = DBEngine.getConnection().prepareStatement(INSERT_INTO_SQL);
            preparedStatement.setInt(1, order.getCustomerId());
            preparedStatement.setInt(2, order.getProductId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Orders order) {
        try {
            PreparedStatement preparedStatement = DBEngine.getConnection().prepareStatement(UPDATE_SET);
            preparedStatement.setInt(1, order.getCustomerId());
            preparedStatement.setInt(2, order.getProductId());
            preparedStatement.setInt(3, order.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Orders order) {
        try {
            PreparedStatement preparedStatement = DBEngine.getConnection().prepareStatement(DELETE_FROM);
            preparedStatement.setInt(1, order.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
