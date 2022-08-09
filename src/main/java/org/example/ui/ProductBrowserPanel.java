package org.example.ui;

import org.example.dao.ProductDao;
import org.example.model.Product;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductBrowserPanel extends JPanel {

    private JList<Product> productJList = new JList<>();
    private DefaultListModel <Product> productListModel;
    private Product currentProduct = null;

    public ProductBrowserPanel (List<Product> products){
        setLayout(new BorderLayout());
        productListModel = new DefaultListModel<>();
        productListModel.addAll(Objects.requireNonNullElseGet(products, ArrayList::new));
        productJList.setModel(productListModel);
        add(productJList, BorderLayout.CENTER);
        productJList.addListSelectionListener(e -> {
            int selectedIndex = e.getFirstIndex();
            if (selectedIndex >= 0) {
                currentProduct = productListModel.get(selectedIndex);
            } else {
                currentProduct = null;
            }
        });
    }

    public Product getCurrentProduct() {
        return currentProduct;
    }

    public void deleteCurrentProduct() {
        Product selectedValue = productJList.getSelectedValue();
        productListModel.removeElement(selectedValue);
        ProductDao productDelete = new ProductDao();
        productDelete.delete(selectedValue);
    }

    public void addClickListener(ListSelectionListener listener) {
        productJList.addListSelectionListener(listener);
    }
}
