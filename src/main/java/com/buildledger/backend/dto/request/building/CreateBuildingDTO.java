package com.buildledger.backend.dto.request.building;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBuildingDTO {

    private String title;
    private String description;

    private int garageCount;
    private int parkingPlaceCount;
    private int floorCount;
    private int undergroundFloorCount;
    private int apartmentCount;

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

    public int getGarageCount() {
        return garageCount;
    }

    public void setGarageCount(int garageCount) {
        this.garageCount = garageCount;
    }

    public int getParkingPlaceCount() {
        return parkingPlaceCount;
    }

    public void setParkingPlaceCount(int parkingPlaceCount) {
        this.parkingPlaceCount = parkingPlaceCount;
    }

    public int getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(int floorCount) {
        this.floorCount = floorCount;
    }

    public int getUndergroundFloorCount() {
        return undergroundFloorCount;
    }

    public void setUndergroundFloorCount(int undergroundFloorCount) {
        this.undergroundFloorCount = undergroundFloorCount;
    }

    public int getApartmentCount() {
        return apartmentCount;
    }

    public void setApartmentCount(int apartmentCount) {
        this.apartmentCount = apartmentCount;
    }
}
