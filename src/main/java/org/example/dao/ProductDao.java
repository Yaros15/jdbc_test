package org.example.dao;

import org.example.model.Product;

import java.util.List;
import java.util.Optional;

public class ProductDao implements Dao<Product> {

    @Override
    public Optional<Product> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void update(Product product, String[] params) {

    }

    @Override
    public void delete(Product product) {

    }
}
