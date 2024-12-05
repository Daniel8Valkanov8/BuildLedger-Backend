package com.buildledger.backend.dto.responce.building;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseParcelDTO {
    private long id;
    private String eik;
    private String address;
    private double area;
    private boolean isCompensated;
    private boolean isRegular;
    private double percentageOfCompensation;
    private boolean withElectricityAndWater;
    private double priceEur;
    private String projectTitle;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public double getPriceEur() {
        return priceEur;
    }

    public void setPriceEur(double priceEur) {
        this.priceEur = priceEur;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
