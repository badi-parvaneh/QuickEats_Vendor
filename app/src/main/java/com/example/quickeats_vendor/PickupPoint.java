package com.example.quickeats_vendor;

import android.location.Location;

/**
 * Created by badiparvaneh on 4/27/18.
 */

public class PickupPoint {
    private String name;
    private Location location;

    public PickupPoint(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }
}
