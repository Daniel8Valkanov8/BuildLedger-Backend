package com.buildledger.backend.dto.responce.building;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class ResponseByIdProjectDTO {

    private long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String eik;
    private String buildingStatus;
    private long parcel;

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

    public String getEik() {
        return eik;
    }

    public void setEik(String eik) {
        this.eik = eik;
    }

    public String getBuildingStatus() {
        return buildingStatus;
    }

    public void setBuildingStatus(String buildingStatus) {
        this.buildingStatus = buildingStatus;
    }

    public long getParcel() {
        return parcel;
    }

    public void setParcel(long parcel) {
        this.parcel = parcel;
    }

    public ResponseByIdProjectDTO(long id, String title, LocalDate startDate, LocalDate endDate, String eik, String buildingStatus, long parcel) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eik = eik;
        this.buildingStatus = buildingStatus;
        this.parcel = parcel;
    }
}
