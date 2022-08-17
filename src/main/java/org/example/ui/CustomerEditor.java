package org.example.ui;

import org.example.dao.CustomerDao;
import org.example.model.Customer;

import javax.swing.*;
import java.awt.*;

public class CustomerEditor extends JDialog {

    private Customer customerToEdit;

    private JLabel nameLabel, ageLabel;

    private JTextField nameField, ageField;

    private JButton okButton, cancelButton;

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

        okButton = new JButton("Ok");
        cancelButton = new JButton("Закрыть");

        JPanel fieldPanel = new JPanel(new GridLayout(2,2,5,5));
        fieldPanel.add(nameLabel);
        fieldPanel.add(nameField);
        fieldPanel.add(ageLabel);
        fieldPanel.add(ageField);

        JPanel buttonsPanel = new JPanel(new GridLayout(1,2,5,5));
        buttonsPanel.add(okButton);
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

            okButton.addActionListener(e-> {
                if (customerToEdit.getId() > 0){
                    customerToEdit.setName(nameField.getText());
                    customerToEdit.setAge(Integer.parseInt(ageField.getText()));
                    CustomerDao updateCustomer = new CustomerDao();
                    updateCustomer.update(customerToEdit);
                    super.dispose();
                } else {
                    customerToEdit.setName(nameField.getText());
                    customerToEdit.setAge(Integer.parseInt(ageField.getText()));
                    CustomerDao saveCustomer = new CustomerDao();
                    saveCustomer.save(customerToEdit);
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
