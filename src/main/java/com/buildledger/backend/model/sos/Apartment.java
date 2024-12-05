package com.buildledger.backend.model.sos;

import com.buildledger.backend.model.building.Building;
import com.buildledger.backend.model.ledger.Sell;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Apartment extends Sos{

    private double area;
    private boolean sold;
    private String description;
    private int bedroomCount;
    private int bathroomCount;


    @ManyToOne
    @JoinColumn(name = "floor_id")
    private Floor floor;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building cooperation;

    @ManyToOne()
    @JoinColumn(name = "sell_id")
    private Sell sell;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Garage> garages = new HashSet<>();

    public Apartment(String number) {
        setNumber(number);
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Building getCooperation() {
        return cooperation;
    }

    public void setCooperation(Building cooperation) {
        this.cooperation = cooperation;
    }

    public Sell getSell() {
        return sell;
    }

    public void setSell(Sell sell) {
        this.sell = sell;
    }

    public Set<Garage> getGarages() {
        return garages;
    }

    public void setGarages(Set<Garage> garages) {
        this.garages = garages;
    }

    public Apartment() {
    }
}
