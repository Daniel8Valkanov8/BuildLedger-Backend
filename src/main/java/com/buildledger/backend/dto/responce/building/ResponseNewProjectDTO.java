package com.buildledger.backend.dto.responce.building;

import lombok.Getter;

import java.time.LocalDate;
@Getter
public class ResponseNewProjectDTO {
    private long id;
    private String title;
    private String eik;
    private String address;
    private String parcelArea;
    private LocalDate startDate;
    private LocalDate endDate;
    private int buildingCount;
    private String buildingStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getParcelArea() {
        return parcelArea;
    }

    public void setParcelArea(String parcelArea) {
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

    public ResponseNewProjectDTO() {
    }

    public ResponseNewProjectDTO(long id,
                                 String title,
                                 String eik,
                                 LocalDate startDate,
                                 LocalDate endDate) {
        this.id = id;
        this.title = title;
        this.eik = eik;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
