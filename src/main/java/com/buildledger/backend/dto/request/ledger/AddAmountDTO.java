package com.buildledger.backend.dto.request.ledger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddAmountDTO {
    private long id;
    private double addAmountInEuro;
    private String payStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAddAmountInEuro() {
        return addAmountInEuro;
    }

    public void setAddAmountInEuro(double addAmountInEuro) {
        this.addAmountInEuro = addAmountInEuro;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }
}
