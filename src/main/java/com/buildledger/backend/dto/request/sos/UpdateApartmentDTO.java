package com.buildledger.backend.dto.request.sos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateApartmentDTO {

    private long id;
    private long cooperationId;
    private String number;
    private double area;

    private double cleanArea;
    private double commonPartsPercentage;
    private double commonParts;
    private double adjoiningTerrace;
    private double adjoiningYardRoof;
    private String compensation;
    private double pricePerSquareMeter;
    private double priceYard;

    private int bedroomCount;
    private int bathroomCount;
    private long floorId;
    private double priceEur;


    public double getCleanArea() {
        return cleanArea;
    }

    public void setCleanArea(double cleanArea) {
        this.cleanArea = cleanArea;
    }

    public double getCommonPartsPercentage() {
        return commonPartsPercentage;
    }

    public double getPriceYard() {
        return priceYard;
    }

    public void setPriceYard(double priceYard) {
        this.priceYard = priceYard;
    }

    public void setCommonPartsPercentage(double commonPartsPercentage) {
        this.commonPartsPercentage = commonPartsPercentage;
    }

    public double getCommonParts() {
        return commonParts;
    }

    public void setCommonParts(double commonParts) {
        this.commonParts = commonParts;
    }

    public double getAdjoiningTerrace() {
        return adjoiningTerrace;
    }

    public void setAdjoiningTerrace(double adjoiningTerrace) {
        this.adjoiningTerrace = adjoiningTerrace;
    }

    public double getAdjoiningYardRoof() {
        return adjoiningYardRoof;
    }

    public void setAdjoiningYardRoof(double adjoiningYardRoof) {
        this.adjoiningYardRoof = adjoiningYardRoof;
    }

    public String getCompensation() {
        return compensation;
    }

    public void setCompensation(String compensation) {
        this.compensation = compensation;
    }

    public double getPricePerSquareMeter() {
        return pricePerSquareMeter;
    }

    public void setPricePerSquareMeter(double pricePerSquareMeter) {
        this.pricePerSquareMeter = pricePerSquareMeter;
    }

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
