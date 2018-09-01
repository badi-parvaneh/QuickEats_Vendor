package com.example.quickeats_vendor;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by badiparvaneh on 4/27/18.
 */

public class Order {
    private String customerName;
    private ArrayList<Food> cart;
    private Date date;
    private Date ETA;
    private PickupPoint pickupPoint;

    public Order(String customerName, ArrayList<Food> allItems, Date date, Date ETA, PickupPoint pickupPoint) {
        this.customerName = customerName;
        this.cart = allItems;
        this.date = date;
        this.ETA = ETA;
        this.pickupPoint = pickupPoint;
    }

    public String getCustomerName() {
        return customerName;
    }

    public ArrayList<Food> getCart() {
        return cart;
    }

    public Date getDate() {
        return date;
    }

    public Date getETA() {
        return ETA;
    }

    public PickupPoint getPickupPoint() {
        return pickupPoint;
    }
}
