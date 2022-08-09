package org.example.ui;

import org.example.dao.CustomerDao;
import org.example.model.Customer;

import javax.swing.*;
import java.awt.*;

public class CustomerEditor extends JDialog {

    private Customer customerToEdit;

    private JLabel nameLabel, ageLabel;

    private JTextField nameField, ageField;

    private JButton createButton, updateButton, cancelButton;

    public CustomerEditor(JFrame frame, Customer customer) {
        super(frame, "Редактор клиентов", true);
        customerToEdit = customer;
        String name = customer.getName();
        if (name != null) {
            nameField.setText(name);
        }
        int age = customer.getAge();
        if (age != 0) {
            ageField.setText(String.valueOf(age));
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
        ageLabel = new JLabel("Возраст:");

        nameField = new JTextField();
        ageField = new JTextField();

        createButton = new JButton("Создать");
        updateButton = new JButton("Изменить");
        cancelButton = new JButton("Закрыть");

        JPanel fieldPanel = new JPanel(new GridLayout(2,2,5,5));
        fieldPanel.add(nameLabel);
        fieldPanel.add(nameField);
        fieldPanel.add(ageLabel);
        fieldPanel.add(ageField);

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
            customerToEdit.setName(value);
        });

            createButton.addActionListener(e -> {
                if (e.getSource() == createButton) {
                    customerToEdit.setName(nameField.getText());
                    customerToEdit.setAge(Integer.parseInt(ageField.getText()));
                    CustomerDao saveCustomer = new CustomerDao();
                    saveCustomer.save(customerToEdit);
                    super.dispose();
                }
                System.out.println("Customer create");
            });

            updateButton.addActionListener(e -> {
                if (e.getSource() == updateButton){
                    customerToEdit.setName(nameField.getText());
                    customerToEdit.setAge(Integer.parseInt(ageField.getText()));
                    CustomerDao saveCustomer = new CustomerDao();
                    saveCustomer.update(customerToEdit);
                    super.dispose();
                }
                System.out.println("Customer update");
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
