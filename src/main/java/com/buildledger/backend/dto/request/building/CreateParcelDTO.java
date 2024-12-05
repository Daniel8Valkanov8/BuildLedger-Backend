package com.buildledger.backend.dto.request.building;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateParcelDTO {
    private String eik;
    private String address;
    private double area;
    private boolean isCompensated;
    private boolean isRegular;
    private double percentageOfCompensation;
    private boolean withElectricityAndWater;
    private double priceBgn;
    private double priceEur;
    private Long projectId;  // ID на проекта, към който принадлежи парцелът

    public String getEik() {
        return eik;
    }

    public void setEik(String eik) {
        this.eik = eik;
    }

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

    public boolean isCompensated() {
        return isCompensated;
    }

    public void setCompensated(boolean compensated) {
        isCompensated = compensated;
    }

    public boolean isRegular() {
        return isRegular;
    }

    public void setRegular(boolean regular) {
        isRegular = regular;
    }

    public double getPercentageOfCompensation() {
        return percentageOfCompensation;
    }

    public void setPercentageOfCompensation(double percentageOfCompensation) {
        this.percentageOfCompensation = percentageOfCompensation;
    }

    public boolean isWithElectricityAndWater() {
        return withElectricityAndWater;
    }

    public void setWithElectricityAndWater(boolean withElectricityAndWater) {
        this.withElectricityAndWater = withElectricityAndWater;
    }

    public double getPriceBgn() {
        return priceBgn;
    }

    public void setPriceBgn(double priceBgn) {
        this.priceBgn = priceBgn;
    }

    public double getPriceEur() {
        return priceEur;
    }

    public void setPriceEur(double priceEur) {
        this.priceEur = priceEur;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
