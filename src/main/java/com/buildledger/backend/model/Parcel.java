package com.buildledger.backend.model;

import com.buildledger.backend.BaseEntity;
import com.buildledger.backend.model.building.Building;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "parcels")
public class Parcel extends BaseEntity {

    private String eik;

    private String address;

    private double area;

    private boolean isCompensated;

    private boolean isRegular;

    private double percentageOfCompensation;

    private boolean withElectricityAndWater;


    private double priceEur;


    @OneToMany(mappedBy = "parcel",
            cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Building> buildings = new HashSet<>();


    @OneToOne
    @JoinColumn(name = "project_id")
    private Project project;

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

    public Set<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(Set<Building> buildings) {
        this.buildings = buildings;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
