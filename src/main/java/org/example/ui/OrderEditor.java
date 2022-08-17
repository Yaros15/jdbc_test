package org.example.ui;

import org.example.dao.OrdersDao;
import org.example.model.Orders;

import javax.swing.*;
import java.awt.*;

public class OrderEditor extends JDialog {

    private Orders ordersToEdit;

    private JLabel customerIdLabel, productIdLabel;

    private JTextField customerIdField, productIdField;

    private JButton okButton, cancelButton;

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

        System.out.println(orders.getId() + " " + orders.getCustomerId() + " " + orders.getProductId());
        super.setBounds(150,150,300,300);
        pack();
        setVisible(true);
    }

    protected JRootPane createRootPane() {
        JRootPane rootPane = super.createRootPane();
        rootPane.setLayout(new BorderLayout());

        customerIdLabel = new JLabel("ID клиента");
        productIdLabel = new JLabel("ID продукта");

        customerIdField = new JTextField();
        productIdField = new JTextField();

        okButton = new JButton("Ok");
        cancelButton = new JButton("Закрыть");

        JPanel fieldPanel = new JPanel(new GridLayout(2,2,5,5));
        fieldPanel.add(customerIdLabel);
        fieldPanel.add(customerIdField);
        fieldPanel.add(productIdLabel);
        fieldPanel.add(productIdField);

        JPanel buttonsPanel = new JPanel(new GridLayout(1,2,5,5));
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(fieldPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        okButton.addActionListener(e -> {
            if (ordersToEdit.getId() > 0){

                ordersToEdit.setCustomerId(Integer.parseInt(customerIdField.getText()));
                ordersToEdit.setProductId(Integer.parseInt(productIdField.getText()));
                OrdersDao updateOrders = new OrdersDao();
                updateOrders.update(ordersToEdit);
                super.dispose();

            } else {

                ordersToEdit.setCustomerId(Integer.parseInt(customerIdField.getText()));
                ordersToEdit.setProductId(Integer.parseInt(productIdField.getText()));
                OrdersDao saveOrders = new OrdersDao();
                saveOrders.save(ordersToEdit);
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
