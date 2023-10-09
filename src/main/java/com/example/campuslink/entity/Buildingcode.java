package com.example.campuslink.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
@Document(collection = "buildingcode")
public class Buildingcode {
    private String buildingcode;
    private String address;
    private String locationname;
    private String latitude;
    private String Longitude;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Buildingcode that)) return false;
        return buildingcode.equals(that.buildingcode) && address.equals(that.address) && locationname.equals(that.locationname) && Objects.equals(latitude, that.latitude) && Objects.equals(Longitude, that.Longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildingcode, address, locationname, latitude, Longitude);
    }

    @Override
    public String toString() {
        return "buildingcode{" +
                "buildingcode='" + buildingcode + '\'' +
                ", address='" + address + '\'' +
                ", locationname='" + locationname + '\'' +
                ", latitude='" + latitude + '\'' +
                ", Longitude='" + Longitude + '\'' +
                '}';
    }

    public String getBuildingcode() {
        return buildingcode;
    }

    public void setBuildingcode(String buildingcode) {
        this.buildingcode = buildingcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocationname() {
        return locationname;
    }

    public void setLocationname(String locationname) {
        this.locationname = locationname;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public Buildingcode(String buildingcode, String address, String locationname, String latitude, String longitude) {
        this.buildingcode = buildingcode;
        this.address = address;
        this.locationname = locationname;
        this.latitude = latitude;
        Longitude = longitude;
    }
}
