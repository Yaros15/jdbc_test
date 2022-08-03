package org.example.ui;

import org.example.dao.CustomerDao;
import org.example.db.DBEngine;
import org.example.model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class MainWindow {

    private static final String SAVE = "Сохранить";
    private static final String UPDATE = "Изменить";
    private static final String DELETE = "Удалить";
    private static final String FIND = "Найти";
    private static final String GET_ALL = "Показать всех";
    public static final String PRODUCT = "PRODUCT";
    public static final String CUSTOMER = "CUSTOMER";
    public static final String ORDER = "ORDER";

    private JFrame frame = new JFrame();
    private JPanel listsPanel, panelRadioButton, panelButton, panelInput;
    private JTextField fieldId, field2, field3;
    private JLabel label1, label2, label3, label4;
    private CustomerBrowserPanel customerPanel;
    private ProductPanel productPanel = new ProductPanel();
    private OrderPanel orderPanel = new OrderPanel();
    private JButton buttonCreate, buttonUpdate, buttonDelete, buttonFind, buttonGetAll;
    private ButtonGroup buttonGroup;
    private CardLayout mainCardLayout;
    private JPanel mainWindowPanel;
    private JRadioButton customerRadioButton, productRadioButton, orderRadioButton;
    private String labelText;
    private int x;

    public void show(){

        ArrayList<Customer> testList = new ArrayList<>();
        testList.add(new Customer("Dron", 10));
        testList.add(new Customer("Yarik", 12));
        testList.add(new Customer("Serega", 18));
        customerPanel = new CustomerBrowserPanel(testList);
        customerPanel.addClickListener(e -> {
            if (e.getFirstIndex() >= 0) {
                buttonUpdate.setEnabled(true);
                buttonDelete.setEnabled(true);
            }
        });

        frame.setTitle("База данных");
        frame.setBounds(100,100,500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        labelText = "Выбирите, какие действия хотите сделать с баззой";

        listsPanel = new JPanel();
        panelRadioButton = new JPanel();
        panelButton = new JPanel();
        panelInput = new JPanel();

        label4 = new JLabel(labelText);
        label1 = new JLabel("Введите id");
        label2 = new JLabel("Введите имя");
        label3 = new JLabel("Введите возраст");
        fieldId = new JTextField();
        field2 = new JTextField();
        field3 = new JTextField();
        x = 1;
        buttonCreate = new JButton(SAVE);
        buttonUpdate = new JButton(UPDATE);
        buttonUpdate.setEnabled(false);
        buttonDelete = new JButton(DELETE);
        buttonDelete.setEnabled(false);
        buttonFind = new JButton(FIND);
        buttonGetAll = new JButton(GET_ALL);

        buttonGroup = new ButtonGroup();
        customerRadioButton = new JRadioButton("Customer", true);
        productRadioButton = new JRadioButton("Product");
        orderRadioButton = new JRadioButton("Order");
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
        panelButton.add(buttonFind);
        panelButton.add(buttonGetAll);

        panelInput.setLayout(new GridLayout(3,2,5,5));
        panelInput.add(label1);
        panelInput.add(fieldId);
        panelInput.add(label2);
        panelInput.add(field2);
        panelInput.add(label3);
        panelInput.add(field3);

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

        customerRadioButton.addItemListener(e -> mainCardLayout.show(listsPanel, CUSTOMER));
        productRadioButton.addItemListener(e -> mainCardLayout.show(listsPanel, PRODUCT));
        orderRadioButton.addItemListener(e -> mainCardLayout.show(listsPanel, ORDER));
        buttonCreate.addActionListener(e ->{
            Customer newCustomer = new Customer();
            CustomerEditor customerEditor = new CustomerEditor(frame, newCustomer);

        });
        buttonUpdate.addActionListener(e -> {
            Customer currentCustomer = customerPanel.getCurrentCustomer();
            if (currentCustomer != null) {
                CustomerEditor customerEditor = new CustomerEditor(frame, currentCustomer);
            }
        });
        buttonDelete.addActionListener(e -> {
            customerPanel.deleteCurrentCustomer();
        });
        buttonFind.addActionListener(e -> {
            if (e.getSource()==buttonFind){
                if (x == 1) {
                    listsPanel.add(panelInput, BorderLayout.CENTER);
                    buttonCreate.setVisible(false);
                    buttonUpdate.setVisible(false);
                    buttonDelete.setVisible(false);
                    buttonGetAll.setVisible(false);
                    x = 0;
                }
                else if (x == 0) {
                    label4.setText(labelText);
                    String c = fieldId.getText();
                    Customer customer = new Customer();
                    customer.setId(Integer.parseInt(c));

                    CustomerDao customerDao = new CustomerDao();
                    customerDao.get(customer);

                    buttonCreate.setVisible(true);
                    buttonUpdate.setVisible(true);
                    buttonDelete.setVisible(true);
                    buttonGetAll.setVisible(true);
                    x = 1;
                }
            }
        });
        buttonGetAll.addActionListener(e-> {
            if (e.getSource()==buttonGetAll){
                CustomerDao customerDao = new CustomerDao();
                label4.setText(String.valueOf(customerDao.getAll()));
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
