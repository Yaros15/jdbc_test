package org.example.ui;

import org.example.dao.OrdersDao;
import org.example.model.Orders;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderBrowserPanel extends JPanel {

    private JList<Orders> ordersJList = new JList<>();
    private DefaultListModel <Orders> ordersListModel;
    private Orders currentOrders = null;

    public OrderBrowserPanel (List<Orders> products){
        setLayout(new BorderLayout());
        ordersListModel = new DefaultListModel<>();
        ordersListModel.addAll(Objects.requireNonNullElseGet(products, ArrayList::new));
        ordersJList.setModel(ordersListModel);
        add(ordersJList, BorderLayout.CENTER);
        ordersJList.addListSelectionListener(e -> {
            int selectedIndex = e.getFirstIndex();
            if (selectedIndex >= 0) {
                currentOrders = ordersListModel.get(selectedIndex);
            } else {
                currentOrders = null;
            }
        });
    }

    public Orders getCurrentOrders() {
        return currentOrders;
    }

    public void deleteOrdersProduct() {
        Orders selectedValue = ordersJList.getSelectedValue();
        ordersListModel.removeElement(selectedValue);
        OrdersDao ordersDelete = new OrdersDao();
        ordersDelete.delete(selectedValue);
    }

    public void addClickListener(ListSelectionListener listener) {
        ordersJList.addListSelectionListener(listener);
    }

}
