package com.example.campuslink.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
@Document(collection = "buildingcode")
public class BuildingCode {
    private String buildingCode;
    private String address;
    private String locationName;
    private String latitude;
    private String Longitude;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BuildingCode that)) return false;
        return buildingCode.equals(that.buildingCode) && address.equals(that.address) && locationName.equals(that.locationName) && Objects.equals(latitude, that.latitude) && Objects.equals(Longitude, that.Longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildingCode, address, locationName, latitude, Longitude);
    }

    @Override
    public String toString() {
        return "buildingcode{" +
                "buildingcode='" + buildingCode + '\'' +
                ", address='" + address + '\'' +
                ", locationname='" + locationName + '\'' +
                ", latitude='" + latitude + '\'' +
                ", Longitude='" + Longitude + '\'' +
                '}';
    }

    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
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

    public BuildingCode(String buildingcode, String address, String locationname, String latitude, String longitude) {
        this.buildingCode = buildingcode;
        this.address = address;
        this.locationName = locationname;
        this.latitude = latitude;
        Longitude = longitude;
    }
}
