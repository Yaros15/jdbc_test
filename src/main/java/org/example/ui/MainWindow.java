package org.example.ui;

import org.example.dao.CustomerDao;
import org.example.dao.OrdersDao;
import org.example.dao.ProductDao;
import org.example.db.DBEngine;
import org.example.model.Customer;
import org.example.model.Orders;
import org.example.model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow {

    private static final String SAVE = "Сохранить";
    private static final String UPDATE = "Изменить";
    private static final String DELETE = "Удалить";
    public static final String PRODUCT = "Продукт";
    public static final String CUSTOMER = "Клиент";
    public static final String ORDER = "Заказ";
    private String nameListPanel;

    private JFrame frame = new JFrame();
    private JPanel listsPanel, panelRadioButton, panelButton;
    private CardLayout mainCardLayout;
    private JPanel mainWindowPanel;
    private CustomerBrowserPanel customerPanel;
    private ProductBrowserPanel productPanel;
    private OrderBrowserPanel orderPanel;
    private JButton buttonCreate, buttonUpdate, buttonDelete;
    private JRadioButton customerRadioButton, productRadioButton, orderRadioButton;
    private ButtonGroup buttonGroup;


    public void show(){

        CustomerDao customerGetAll = new CustomerDao();
        customerPanel = new CustomerBrowserPanel(customerGetAll.getAll());
        customerPanel.addClickListener(e -> {
            if (e.getFirstIndex() >= 0) {
                buttonUpdate.setEnabled(true);
                buttonDelete.setEnabled(true);
            }
        });

        ProductDao productGetAll = new ProductDao();
        productPanel = new ProductBrowserPanel(productGetAll.getAll());
        productPanel.addClickListener(e -> {
            if (e.getFirstIndex() >= 0) {
                buttonUpdate.setEnabled(true);
                buttonDelete.setEnabled(true);
            }
        });

        OrdersDao ordersGetAll = new OrdersDao();
        orderPanel = new OrderBrowserPanel(ordersGetAll.getAll());
        orderPanel.addClickListener(e -> {
            if (e.getFirstIndex() >= 0) {
                buttonUpdate.setEnabled(true);
                buttonDelete.setEnabled(true);
            }
        });

        frame.setTitle("База данных");
        frame.setBounds(100,100,500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        listsPanel = new JPanel();
        panelRadioButton = new JPanel();
        panelButton = new JPanel();

        buttonCreate = new JButton(SAVE);
        buttonUpdate = new JButton(UPDATE);
        buttonUpdate.setEnabled(false);
        buttonDelete = new JButton(DELETE);
        buttonDelete.setEnabled(false);

        customerRadioButton = new JRadioButton(CUSTOMER);
        productRadioButton = new JRadioButton(PRODUCT, true);
        orderRadioButton = new JRadioButton(ORDER);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(customerRadioButton);
        buttonGroup.add(productRadioButton);
        buttonGroup.add(orderRadioButton);

        panelRadioButton.setLayout(new FlowLayout());
        panelRadioButton.add(customerRadioButton);
        panelRadioButton.add(productRadioButton);
        panelRadioButton.add(orderRadioButton);

        panelButton.setLayout(new FlowLayout());
        panelButton.add(buttonCreate);
        panelButton.add(buttonUpdate);
        panelButton.add(buttonDelete);

        mainCardLayout = new CardLayout();
        listsPanel.setLayout(mainCardLayout);
        listsPanel.add(productPanel, PRODUCT);
        listsPanel.add(customerPanel, CUSTOMER);
        listsPanel.add(orderPanel, ORDER);
        mainWindowPanel = new JPanel(new BorderLayout());
        mainWindowPanel.add(panelRadioButton, BorderLayout.NORTH);
        mainWindowPanel.add(listsPanel, BorderLayout.CENTER);
        mainWindowPanel.add(panelButton, BorderLayout.SOUTH);

        frame.getContentPane().add(mainWindowPanel);

        customerRadioButton.addItemListener(e -> {
            mainCardLayout.show(listsPanel, CUSTOMER);
            nameListPanel = CUSTOMER;
            buttonUpdate.setEnabled(false);
            buttonDelete.setEnabled(false);
            buttonCreate.setEnabled(true);
        });
        productRadioButton.addItemListener(e -> {
            mainCardLayout.show(listsPanel, PRODUCT);
            nameListPanel = PRODUCT;
            buttonUpdate.setEnabled(false);
            buttonDelete.setEnabled(false);
            buttonCreate.setEnabled(true);
        });
        orderRadioButton.addItemListener(e -> {
            mainCardLayout.show(listsPanel, ORDER);
            nameListPanel = ORDER;
            buttonUpdate.setEnabled(false);
            buttonDelete.setEnabled(false);
            buttonCreate.setEnabled(false);
        });

        buttonCreate.addActionListener(e ->{
            if (nameListPanel.equals(CUSTOMER)){
                Customer newCustomer = new Customer();
                CustomerEditor customerEditor = new CustomerEditor(frame, newCustomer);
            }
            if (nameListPanel.equals(PRODUCT)){
                Product newProduct = new Product();
                ProductEditor productEditor = new ProductEditor(frame, newProduct);
            }
        });

        buttonUpdate.addActionListener(e -> {
            if (nameListPanel.equals(CUSTOMER)){
                Customer currentCustomer = customerPanel.getCurrentCustomer();
                if (currentCustomer != null) {
                    CustomerEditor customerEditor = new CustomerEditor(frame, currentCustomer);
                }
            }
            if (nameListPanel.equals(PRODUCT)){
                Product currentProduct = productPanel.getCurrentProduct();
                if (currentProduct != null) {
                    ProductEditor productEditor = new ProductEditor(frame, currentProduct);
                }
            }
        });

        buttonDelete.addActionListener(e -> {
            if (nameListPanel.equals(CUSTOMER)){
                customerPanel.deleteCurrentCustomer();
            }
            if (nameListPanel.equals(PRODUCT)){
                productPanel.deleteCurrentProduct();
            }
            if (nameListPanel.equals(ORDER)){
                orderPanel.deleteOrdersProduct();
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DBEngine.closeConnection();
            }
        });
        frame.setVisible(true);
    }

}
