package com.buildledger.backend.dto.request.building;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateIntermediateDTO {
    private long id;

    private int entrance;
    private int floor;
    private int undergroundFloor;

    private int apartment;
    private int garage;
    private int parkingPlace;

    @Override
    public String toString() {
        return "CreateIntermediateDTO{" +
                "id=" + id +
                ", entrance=" + entrance +
                ", floor=" + floor +
                ", undergroundFloor=" + undergroundFloor +
                ", apartment=" + apartment +
                ", garage=" + garage +
                ", parkingPlace=" + parkingPlace +
                '}';
    }

    public int getGarage() {
        return garage;
    }

    public void setGarage(int garage) {
        this.garage = garage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getEntrance() {
        return entrance;
    }

    public void setEntrance(int entrance) {
        this.entrance = entrance;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getUndergroundFloor() {
        return undergroundFloor;
    }

    public void setUndergroundFloor(int undergroundFloor) {
        this.undergroundFloor = undergroundFloor;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }

    public int getParkingPlace() {
        return parkingPlace;
    }

    public void setParkingPlace(int parkingPlace) {
        this.parkingPlace = parkingPlace;
    }
}
