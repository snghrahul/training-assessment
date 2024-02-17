package com.training.aem.core.bean;

public class ProductDetailsEntity {

    int id;
    double price;
    String image;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    String category;

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
    public ProductDetailsEntity(int productId, double price, String image, String category) {
        this.id = productId;
        this.price = price;
        this.image = image;
        this.category = category;
    }

}
