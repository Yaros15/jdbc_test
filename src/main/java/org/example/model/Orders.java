package org.example.model;

public class Orders {

    private int id;
    private int customerId;
    private int productId;
    private String customerName;
    private String productName;
    private double productPrice;

    public Orders(){
    }

    public Orders(int customerId, int productId){
        this.customerId = customerId;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCustomerName (){
        return customerName;
    }

    public void setCustomerName (String customerName){
        this.customerName = customerName;
    }

    public String getProductName (){
        return productName;
    }

    public void setProductName (String productName){
        this.productName = productName;
    }

    public double getProductPrice (){
        return productPrice;
    }

    public void setProductPrice (double productPrice){
        this.productPrice = productPrice;
    }

    public String toString() {
        return   "customer = " + getCustomerName() +
                ", product = " + getProductName() +
                ", price = " + getProductPrice();
    }

}
