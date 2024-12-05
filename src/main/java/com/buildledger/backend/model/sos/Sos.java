package com.buildledger.backend.model.sos;


import com.buildledger.backend.BaseEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Sos extends BaseEntity {


    private String number;
    private double priceEur;
    private boolean sold;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getPriceEur() {
        return priceEur;
    }

    public void setPriceEur(double priceEur) {
        this.priceEur = priceEur;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }
}
