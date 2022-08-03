package org.example.ui;

import org.example.model.Customer;

import javax.swing.*;
import java.awt.*;

public class CustomerEditor extends JDialog {

    private Customer customerToEdit;

    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel ageLabel;

    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;

    private JButton okButton;
    private JButton cancelButton;

    public CustomerEditor(JFrame frame, Customer customer) {
        super(frame, "Customer Editor", true);
        customerToEdit = customer;
        String name = customer.getName();
        if (name != null) {
            nameField.setText(name);
        }
        int age = customer.getAge();
        if (age != 0) {
            ageField.setText(String.valueOf(age));
        }
        setSize(new Dimension(400, 400));
        pack();
        setVisible(true);
    }

    @Override
    protected JRootPane createRootPane() {
        JRootPane rootPane = super.createRootPane();
        rootPane.setLayout(new BorderLayout());

        JPanel fieldPanel = new JPanel(new GridLayout(3,2,5,5));
        idLabel = new JLabel("Id:");
        nameLabel = new JLabel("Name:");
        ageLabel = new JLabel("Age:");

        idField = new JTextField();
        nameField = new JTextField();
        ageField = new JTextField();

        okButton = new JButton("Ok");
        cancelButton = new JButton("Cancel");
        fieldPanel.add(idLabel);
        fieldPanel.add(idField);
        fieldPanel.add(nameLabel);
        fieldPanel.add(nameField);
        fieldPanel.add(ageLabel);
        fieldPanel.add(ageField);

        JPanel buttonsPanel = new JPanel(new BorderLayout());
        buttonsPanel.add(okButton, BorderLayout.WEST);
        buttonsPanel.add(cancelButton, BorderLayout.EAST);
        fieldPanel.add(buttonsPanel);
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
        rootPane.add(mainPanel, BorderLayout.CENTER);
        return rootPane;
    }
}
