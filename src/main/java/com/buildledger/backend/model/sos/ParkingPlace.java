package com.buildledger.backend.model.sos;

import com.buildledger.backend.model.building.Building;
import com.buildledger.backend.model.ledger.Sell;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "parking_places")

@Getter
@Setter
public class ParkingPlace extends Sos{


    private String description;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building cooperation;
    @ManyToOne
    @JoinColumn(name = "sell_id")
    private Sell sell;
    public ParkingPlace(String number) {
        setNumber(number);
    }

    public ParkingPlace() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}