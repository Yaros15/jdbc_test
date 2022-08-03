package org.example.ui;

import org.example.model.Customer;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerBrowserPanel extends JPanel {

    private JList<Customer> customerJList = new JList<>();
    private DefaultListModel<Customer> customerListModel;

    private Customer currentCustomer = null;

    public CustomerBrowserPanel(List<Customer> customers) {
        setLayout(new BorderLayout());
        customerListModel = new DefaultListModel<>();
        customerListModel.addAll(Objects.requireNonNullElseGet(customers, ArrayList::new));
        customerJList.setModel(customerListModel);
        add(customerJList, BorderLayout.CENTER);
        customerJList.addListSelectionListener(e -> {
            int selectedIndex = e.getFirstIndex();
            if (selectedIndex >= 0) {
                currentCustomer = customerListModel.get(selectedIndex);
            } else {
                currentCustomer = null;
            }
        });
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void deleteCurrentCustomer() {
        Customer selectedValue = customerJList.getSelectedValue();
        customerListModel.removeElement(selectedValue);
    }

    public void addClickListener(ListSelectionListener listener) {
        customerJList.addListSelectionListener(listener);
    }
}
