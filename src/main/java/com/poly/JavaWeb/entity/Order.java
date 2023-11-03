package com.poly.JavaWeb.entity;

public class Order {
    private int id;
    private String customer_name;
    private String address;
    private String phone;
    private int total;

    public Order() {
    }

    public Order(String customer_name, String address, String phone, int total) {
        this.customer_name = customer_name;
        this.address = address;
        this.phone = phone;
        this.total = total;
    }

    public Order(int id, String customer_name, String address, String phone, int total) {
        this.id = id;
        this.customer_name = customer_name;
        this.address = address;
        this.phone = phone;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
