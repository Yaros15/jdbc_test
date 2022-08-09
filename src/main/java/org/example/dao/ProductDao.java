package org.example.dao;

import org.example.db.DBEngine;
import org.example.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements Dao<Product> {

    private static final String SELECT_QUERY_ALL = "SELECT * FROM product";
    private static final String INSERT_INTO_SQL = "INSERT INTO product (name, price) VALUES (?, ?)";
    private static final String UPDATE_SET = "UPDATE product SET name = ?, price = ? WHERE id = ?";
    private static final String DELETE_FROM = "DELETE FROM product WHERE id = ?";


    @Override
    public List<Product> getAll() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            Statement statement = DBEngine.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_QUERY_ALL);
            while (resultSet.next()){
                Product newProducts = new Product();
                newProducts.setId(resultSet.getInt("id"));
                newProducts.setName(resultSet.getString("name"));
                newProducts.setPrice(resultSet.getInt("price"));
                products.add(newProducts);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void save(Product product) {
        try {
            PreparedStatement preparedStatement = DBEngine.getConnection().prepareStatement(INSERT_INTO_SQL);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {
        try {
            PreparedStatement preparedStatement = DBEngine.getConnection().prepareStatement(UPDATE_SET);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Product product) {
        try {
            PreparedStatement preparedStatement = DBEngine.getConnection().prepareStatement(DELETE_FROM);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
