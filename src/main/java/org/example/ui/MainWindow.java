package org.example.ui;

import org.example.dao.CustomerDao;
import org.example.model.Customer;

import javax.swing.*;
import java.awt.*;

public class MainWindow {

    private static final String SAVE = "Сохранить";
    private static final String UPDATE = "Изменить";
    private static final String DELETE = "Удалить";
    private static final String FIND = "Найти";
    private static final String GET_ALL = "Показать всех";

    private JFrame frame = new JFrame();
    private JPanel panelWindow, panelRadioButton, panelButton, panelInput;
    private JTextField fieldId, field2, field3;
    private JLabel label1, label2, label3, label4;
    private JButton buttonSave, buttonUpdate, buttonDelete, buttonFind, buttonGetAll;
    private ButtonGroup buttonGroup;
    private JRadioButton radioButton1, radioButton2, radioButton3;
    private String labelText;
    private int x;

    public void go (){

        frame.setTitle("База данных");
        frame.setBounds(100,100,500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        labelText = "Выбирите, какие действия хотите сделать с баззой";

        panelWindow = new JPanel();
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
        buttonSave = new JButton(SAVE);
        buttonUpdate = new JButton(UPDATE);
        buttonDelete = new JButton(DELETE);
        buttonFind = new JButton(FIND);
        buttonGetAll = new JButton(GET_ALL);

        buttonGroup = new ButtonGroup();
        radioButton1 = new JRadioButton("Customer");
        radioButton2 = new JRadioButton("Product");
        radioButton3 = new JRadioButton("Order");
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);

        frame.getContentPane().add(panelWindow);

        panelRadioButton.setLayout(new FlowLayout());
        panelRadioButton.add(radioButton1);
        panelRadioButton.add(radioButton2);
        panelRadioButton.add(radioButton3);

        panelButton.setLayout(new FlowLayout());
        panelButton.add(buttonSave);
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
        
        panelWindow.setLayout(new BorderLayout());
        panelWindow.add(panelRadioButton, BorderLayout.NORTH);
        panelWindow.add(label4, BorderLayout.CENTER);
        panelWindow.add(panelButton, BorderLayout.SOUTH);

        buttonSave.addActionListener(e ->{
            if (e.getSource()==buttonSave) {
                if (x == 1) {
                    panelWindow.add(panelInput, BorderLayout.CENTER);
                    buttonDelete.setVisible(false);
                    buttonUpdate.setVisible(false);
                    buttonFind.setVisible(false);
                    buttonGetAll.setVisible(false);

                    x = 0;
                }
                else if (x == 0) {
                    label4.setText(labelText);
                    String a = field2.getText();
                    String b = field3.getText();
                    Customer customer = new Customer();
                    customer.setName(a);
                    customer.setAge(Integer.parseInt(b));

                    CustomerDao customerDao = new CustomerDao();
                    customerDao.save(customer);

                    buttonDelete.setVisible(true);
                    buttonUpdate.setVisible(true);
                    buttonFind.setVisible(true);
                    buttonGetAll.setVisible(true);

                    x = 1;
                }
            }
        });
        buttonUpdate.addActionListener(e -> {
            if (e.getSource()==buttonUpdate){
                if (x == 1) {

                    panelWindow.add(panelInput, BorderLayout.CENTER);
                    buttonDelete.setVisible(false);
                    buttonSave.setVisible(false);
                    buttonGetAll.setVisible(false);

                    x = 0;
                }
                else if (x == 0) {
                    label4.setText(labelText);
                    String a = field2.getText();
                    String b = field3.getText();
                    String c = fieldId.getText();
                    Customer customer = new Customer();
                    customer.setName(a);
                    customer.setAge(Integer.parseInt(b));
                    customer.setId(Integer.parseInt(c));

                    CustomerDao customerDao = new CustomerDao();
                    customerDao.update(customer);

                    buttonDelete.setVisible(true);
                    buttonSave.setVisible(true);
                    buttonGetAll.setVisible(true);

                    x = 1;
                }
            }
        });
        buttonDelete.addActionListener(e -> {
            if (e.getSource()==buttonDelete){
                if (x == 1) {
                    panelWindow.add(panelInput, BorderLayout.CENTER);
                    buttonSave.setVisible(false);
                    buttonUpdate.setVisible(false);
                    buttonGetAll.setVisible(false);

                    x = 0;
                }
                else if (x == 0) {
                    label4.setText(labelText);
                    String c = fieldId.getText();
                    Customer customer = new Customer();
                    customer.setId(Integer.parseInt(c));

                    CustomerDao customerDao = new CustomerDao();
                    customerDao.delete(customer);

                    buttonSave.setVisible(true);
                    buttonUpdate.setVisible(true);
                    buttonGetAll.setVisible(true);
                    x = 1;
                }
            }
        });
        buttonFind.addActionListener(e -> {
            if (e.getSource()==buttonFind){
                if (x == 1) {
                    panelWindow.add(panelInput, BorderLayout.CENTER);
                    buttonSave.setVisible(false);
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

                    buttonSave.setVisible(true);
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



        frame.setVisible(true);
    }

}
