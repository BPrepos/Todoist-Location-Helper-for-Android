package com.e.praca;

import java.io.Serializable;

public class Label implements Serializable {
    private String name;
    private long labelId;
    private double latitude, longitude;

    Label(String name, long labelId) {
        this.name = name;
        this.labelId = labelId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getLabelid() {
        return labelId;
    }

    public void setLabelid(long id) {
        this.labelId = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }


}
