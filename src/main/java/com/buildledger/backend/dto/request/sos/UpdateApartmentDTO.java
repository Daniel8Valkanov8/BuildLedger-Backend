package com.buildledger.backend.dto.request.sos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateApartmentDTO {

    private long id;
    private long cooperationId;
    private String number;
    private double area;
    private int bedroomCount;
    private int bathroomCount;
    private long floorId;
    private double priceEur;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCooperationId() {
        return cooperationId;
    }

    public void setCooperationId(long cooperationId) {
        this.cooperationId = cooperationId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getBedroomCount() {
        return bedroomCount;
    }

    public void setBedroomCount(int bedroomCount) {
        this.bedroomCount = bedroomCount;
    }

    public int getBathroomCount() {
        return bathroomCount;
    }

    public void setBathroomCount(int bathroomCount) {
        this.bathroomCount = bathroomCount;
    }

    public long getFloorId() {
        return floorId;
    }

    public void setFloorId(long floorId) {
        this.floorId = floorId;
    }

    public double getPriceEur() {
        return priceEur;
    }

    public void setPriceEur(double priceEur) {
        this.priceEur = priceEur;
    }
}
