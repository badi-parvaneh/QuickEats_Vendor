package com.example.quickeats_vendor;

/**
 * Created by badiparvaneh on 4/27/18.
 */

public class Food {
    private String name;
    private String price;
    private String info;
    private double priceDouble;
    private int imageId;

    public Food(String name, String price, double priceDouble, String info, int imageId) {
        this.name = name;
        this.price = price;
        this.info = info;
        this.priceDouble = priceDouble;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getInfo() {
        return info;
    }

    public double getPriceDouble() { return priceDouble; }

    public int getImageId() {return imageId;}

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setPriceDouble(double priceDouble) {
        this.priceDouble = priceDouble;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
