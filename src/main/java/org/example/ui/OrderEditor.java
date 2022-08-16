package org.example.ui;

import org.example.dao.OrdersDao;
import org.example.model.Orders;

import javax.swing.*;
import java.awt.*;

public class OrderEditor extends JDialog {

    private Orders ordersToEdit;

    private JLabel customerIdLabel, productIdLabel;

    private JTextField customerIdField, productIdField;

    private JButton createButton, updateButton, cancelButton;

    public OrderEditor (JFrame frame, Orders orders){
        super(frame, "Редактор заказов", true);
        ordersToEdit = orders;

        int customerId = orders.getCustomerId();
        if (customerId != 0) {
            customerIdField.setText(String.valueOf(customerId));
        }

        int productId = orders.getProductId();
        if (productId != 0) {
            productIdField.setText(String.valueOf(productId));
        }

        super.setBounds(150,150,300,300);
        pack();
        setVisible(true);
    }

    protected JRootPane createRootPane() {
        JRootPane rootPane = super.createRootPane();
        rootPane.setLayout(new BorderLayout());

        customerIdLabel = new JLabel("id клиента");
        productIdLabel = new JLabel("id продукта");

        customerIdField = new JTextField();
        productIdField = new JTextField();

        createButton = new JButton("Создать");
        updateButton = new JButton("Изменить");
        cancelButton = new JButton("Закрыть");

        JPanel fieldPanel = new JPanel(new GridLayout(2,2,5,5));
        fieldPanel.add(customerIdLabel);
        fieldPanel.add(customerIdField);
        fieldPanel.add(productIdLabel);
        fieldPanel.add(productIdField);

        JPanel buttonsPanel = new JPanel(new GridLayout(1,3,5,5));
        buttonsPanel.add(createButton);
        buttonsPanel.add(updateButton);
        buttonsPanel.add(cancelButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(fieldPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        createButton.addActionListener(e -> {
            if (e.getSource() == createButton) {
                ordersToEdit.setCustomerId(Integer.parseInt(customerIdField.getText()));
                ordersToEdit.setProductId(Integer.parseInt(productIdField.getText()));
                /*OrdersDao saveOrders = new OrdersDao();
                saveOrders.save(ordersToEdit);*/
                System.out.println(ordersToEdit.getCustomerId() + " " + ordersToEdit.getProductId());
                super.dispose();
            }
        });

        updateButton.addActionListener(e -> {
            if (e.getSource() == updateButton){
                ordersToEdit.setCustomerId(Integer.parseInt(customerIdField.getText()));
                ordersToEdit.setProductId(Integer.parseInt(productIdField.getText()));
                /*OrdersDao updateOrders = new OrdersDao();
                updateOrders.update(ordersToEdit);*/
                System.out.println(ordersToEdit.getCustomerId() + " " + ordersToEdit.getProductId());
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
