package org.example.ui;

import org.example.dao.ProductDao;
import org.example.model.Product;

import javax.swing.*;
import java.awt.*;

public class ProductEditor extends JDialog {

    private Product productToEdit;

    private JLabel nameLabel, priceLabel;

    private JTextField nameField, priceField;

    private JButton createButton, updateButton, cancelButton;

    public ProductEditor (JFrame frame, Product product){
        super(frame, "Редактор продуктов", true);
        productToEdit = product;
        String name = product.getName();
        if (name != null) {
            nameField.setText(name);
        }
        double price = product.getPrice();
        if (price != 0) {
            priceField.setText(String.valueOf(price));
        }
        super.setBounds(150,150,300,300);
        pack();
        setVisible(true);
    }

    @Override
    protected JRootPane createRootPane() {
        JRootPane rootPane = super.createRootPane();
        rootPane.setLayout(new BorderLayout());

        nameLabel = new JLabel("Имя:");
        priceLabel = new JLabel("Цена:");

        nameField = new JTextField();
        priceField = new JTextField();

        createButton = new JButton("Создать");
        updateButton = new JButton("Изменить");
        cancelButton = new JButton("Закрыть");

        JPanel fieldPanel = new JPanel(new GridLayout(2,2,5,5));
        fieldPanel.add(nameLabel);
        fieldPanel.add(nameField);
        fieldPanel.add(priceLabel);
        fieldPanel.add(priceField);

        JPanel buttonsPanel = new JPanel(new GridLayout(1,3,5,5));
        buttonsPanel.add(createButton);
        buttonsPanel.add(updateButton);
        buttonsPanel.add(cancelButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(fieldPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        nameField.addActionListener(e -> {
            String value = nameField.getText();
            if (value == null || value.isEmpty()) {
                return;
            }
            productToEdit.setName(value);
        });

        createButton.addActionListener(e -> {
            if (e.getSource() == createButton) {
                productToEdit.setName(nameField.getText());
                productToEdit.setPrice(Integer.parseInt(priceField.getText()));
                ProductDao saveProduct = new ProductDao();
                saveProduct.save(productToEdit);
                super.dispose();
            }
        });

        updateButton.addActionListener(e -> {
            if (e.getSource() == updateButton){
                productToEdit.setName(nameField.getText());
                productToEdit.setPrice(Integer.parseInt(priceField.getText()));
                ProductDao updateProduct = new ProductDao();
                updateProduct.update(productToEdit);
                super.dispose();
            }
        });

        cancelButton.addActionListener(e -> {
            if (e.getSource() == cancelButton) {
                super.dispose();
            }
        });

        rootPane.add(mainPanel, BorderLayout.CENTER);
        return rootPane;
    }

}
