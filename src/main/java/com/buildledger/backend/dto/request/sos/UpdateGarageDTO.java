package com.buildledger.backend.dto.request.sos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateGarageDTO {

    private long id;
    private double priceEur;
    private long floorId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPriceEur() {
        return priceEur;
    }

    public void setPriceEur(double priceEur) {
        this.priceEur = priceEur;
    }

    public long getFloorId() {
        return floorId;
    }

    public void setFloorId(long floorId) {
        this.floorId = floorId;
    }
}
