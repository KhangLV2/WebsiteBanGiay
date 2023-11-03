package com.poly.JavaWeb.entity;

public class ProductCart {
    public int quantity;
    public Product product;

    public ProductCart() {
    }

    public ProductCart(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void incrementQuantity(){
        this.quantity++;
    }

    public void reduceQuantity(){this.quantity--;}
}
