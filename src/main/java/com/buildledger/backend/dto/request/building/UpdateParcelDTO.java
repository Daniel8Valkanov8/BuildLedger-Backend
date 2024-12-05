package com.buildledger.backend.dto.request.building;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateParcelDTO {
    private String address;
    private double area;
    private String isCompensated;
    private String isRegular;
    private double percentageOfCompensation;
    private String withElectricityAndWater;
    private double priceEur;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getIsCompensated() {
        return isCompensated;
    }

    public void setIsCompensated(String isCompensated) {
        this.isCompensated = isCompensated;
    }

    public String getIsRegular() {
        return isRegular;
    }

    public void setIsRegular(String isRegular) {
        this.isRegular = isRegular;
    }

    public double getPercentageOfCompensation() {
        return percentageOfCompensation;
    }

    public void setPercentageOfCompensation(double percentageOfCompensation) {
        this.percentageOfCompensation = percentageOfCompensation;
    }

    public String getWithElectricityAndWater() {
        return withElectricityAndWater;
    }

    public void setWithElectricityAndWater(String withElectricityAndWater) {
        this.withElectricityAndWater = withElectricityAndWater;
    }

    public double getPriceEur() {
        return priceEur;
    }

    public void setPriceEur(double priceEur) {
        this.priceEur = priceEur;
    }
}
