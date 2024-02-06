package com.training.aem.core.models;

public class ProductDetailsEntity {

    int id;
    double price;
    String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ProductDetailsEntity(){

    }
    public ProductDetailsEntity(int productId, double price, String image) {
        this.id = productId;
        this.price = price;
        this.image = image;
    }

}
