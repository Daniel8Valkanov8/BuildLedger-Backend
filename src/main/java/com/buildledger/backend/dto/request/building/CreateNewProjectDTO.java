package com.buildledger.backend.dto.request.building;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@Getter

public class CreateNewProjectDTO {

    private String title;
    private String eik;
    private String address;
    private double parcelArea;
    private LocalDate startDate;
    private LocalDate endDate;
    private int buildingCount;
    private String buildingStatus;

    public CreateNewProjectDTO(String title, String eik, String address, double parcelArea, LocalDate startDate, LocalDate endDate, int buildingCount, String buildingStatus) {
        this.title = title;
        this.eik = eik;
        this.address = address;
        this.parcelArea = parcelArea;
        this.startDate = startDate;
        this.endDate = endDate;
        this.buildingCount = buildingCount;
        this.buildingStatus = buildingStatus;
    }

    public CreateNewProjectDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public double getParcelArea() {
        return parcelArea;
    }

    public void setParcelArea(double parcelArea) {
        this.parcelArea = parcelArea;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getBuildingCount() {
        return buildingCount;
    }

    public void setBuildingCount(int buildingCount) {
        this.buildingCount = buildingCount;
    }

    public String getBuildingStatus() {
        return buildingStatus;
    }

    public void setBuildingStatus(String buildingStatus) {
        this.buildingStatus = buildingStatus;
    }
}
