package org.example.model;

public class Orders {

    private int id;
    private int customerId;
    private int productId;
    Customer customer = new Customer();
    Product product = new Product();

    public void setCustomerName (String name){
        customer.setName(name);
    }

    public void setProductName (String product){
        this.product.setName(product);
    }

    public void setProductPrise (double price){
        product.setPrice(price);
    }

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

    public String toString() {
        return /*"customer Id =" + customerId +
                ", product Id = " + productId;*/
        "customer = " + customer.getName() +
                ", product = " + product.getName() +
                ", price = " + product.getPrice();
    }

}
