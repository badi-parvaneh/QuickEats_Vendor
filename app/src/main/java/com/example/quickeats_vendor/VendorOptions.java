package com.example.quickeats_vendor;

/**
 * Created by badiparvaneh on 4/29/18.
 */

public class VendorOptions {

    private String name;
    private int imageId;

    public VendorOptions(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
