package com.poly.JavaWeb.entity;

public class Product {
    private int id;
    private String name;
    private String img;
    private double price;
    private String title;
    private String description;
    private int cateID;
    private int sell_ID;

    public Product() {
    }

    public Product(String name, String img, double price, String title, String description, int cateID) {
        this.name = name;
        this.img = img;
        this.price = price;
        this.title = title;
        this.description = description;
        this.cateID = cateID;
    }

    public Product(int id, String name, String img, double price, String title, String description, int cateID, int sell_ID) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.price = price;
        this.title = title;
        this.description = description;
        this.cateID = cateID;
        this.sell_ID = sell_ID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public int getSell_ID() {
        return sell_ID;
    }

    public void setSell_ID(int sell_ID) {
        this.sell_ID = sell_ID;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", cateID=" + cateID +
                ", sell_ID=" + sell_ID +
                '}';
    }
}
