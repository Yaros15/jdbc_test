package org.example.dao;

import org.example.db.DBEngine;
import org.example.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class ProductDao implements Dao<Product> {

    private static final String SELECT_QUERY = "SELECT * FROM product";
    private static final String INSERT_INTO_SQL = "INSERT INTO product (name, price) VALUES (?, ?)";
    private static final String UPDATE_SET = "UPDATE product SET name = ?, price = ? WHERE id = ?";
    private static final String DELETE_FROM = "DELETE FROM product WHERE id = ?";

    @Override
    public Optional<Product> get(Product product/*long id*/) {
        return Optional.empty();
    }

    @Override
    public List<Product> getAll() {
        try {
            Statement statement = DBEngine.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_QUERY);
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id") + " "
                        + resultSet.getString("name") + " "
                        + resultSet.getInt("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Product product) {
        try {
            PreparedStatement preparedStatement = DBEngine.getConnection().prepareStatement(INSERT_INTO_SQL);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product/*, String[] params*/) {
        try {
            PreparedStatement preparedStatement = DBEngine.getConnection().prepareStatement(UPDATE_SET);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Product product) {
        try {
            PreparedStatement preparedStatement = DBEngine.getConnection().prepareStatement(DELETE_FROM);
            preparedStatement.setInt(1, product.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
