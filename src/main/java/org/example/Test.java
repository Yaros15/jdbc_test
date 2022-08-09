package org.example;

import org.example.model.Orders;

public class Test {
    public static void main(String[] args) {

    Orders orders = new Orders();
    orders.setCustomerName("pupsic");
    orders.setProductName("groznsii");
    orders.setProductPrise(15.15);
        System.out.println(orders);
    }
}
