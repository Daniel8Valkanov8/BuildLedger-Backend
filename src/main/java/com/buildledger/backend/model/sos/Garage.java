package com.buildledger.backend.model.sos;

import com.buildledger.backend.model.building.Building;
import com.buildledger.backend.model.ledger.Sell;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "garages")
public class Garage extends Sos {

    private String description;


    @ManyToOne
    @JoinColumn(name = "floor_id")
    private Floor floor;

    @ManyToOne
    @JoinColumn(name = "sell_id")
    private Sell sell;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building cooperation;

    public Garage(String number) {
        setNumber(number); ;
    }

    public Garage() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Sell getSell() {
        return sell;
    }

    public void setSell(Sell sell) {
        this.sell = sell;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Building getCooperation() {
        return cooperation;
    }

    public void setCooperation(Building cooperation) {
        this.cooperation = cooperation;
    }
}
