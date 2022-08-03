package org.example.model;

public class Order {

    private int id;
    private int foreignCustomer;
    private int foreignProduct;

    public Order(){
    }

    public Order(int foreignCustomer, int foreignProduct){
        this.foreignCustomer = foreignCustomer;
        this.foreignProduct = foreignProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getForeignCustomer() {
        return foreignCustomer;
    }

    public void setForeignCustomer(int foreignCustomer) {
        this.foreignCustomer = foreignCustomer;
    }

    public int getForeignProduct() {
        return foreignProduct;
    }

    public void setForeignProduct(int foreignProduct) {
        this.foreignProduct = foreignProduct;
    }

    public String toString() {
        return "Order{" +
                "id=" + id +
                ", foreignCustomer=" + foreignCustomer +
                ", foreignProduct='" + foreignProduct + '\'' +
                '}';
    }

}
