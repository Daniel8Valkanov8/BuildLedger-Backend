package com.buildledger.backend.model.ledger.accounting;

import com.buildledger.backend.BaseEntity;
import com.buildledger.backend.enums.PayStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class BaseLedger extends BaseEntity {

    private double amountEuro;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private PayStatus payStatus;

    private double payedAmountEuro;
    private double remainingAmountEuro;

    public double getAmountEuro() {
        return amountEuro;
    }

    public void setAmountEuro(double amountEuro) {
        this.amountEuro = amountEuro;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public PayStatus getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PayStatus payStatus) {
        this.payStatus = payStatus;
    }

    public double getPayedAmountEuro() {
        return payedAmountEuro;
    }

    public void setPayedAmountEuro(double payedAmountEuro) {
        this.payedAmountEuro = payedAmountEuro;
    }

    public double getRemainingAmountEuro() {
        return remainingAmountEuro;
    }

    public void setRemainingAmountEuro(double remainingAmountEuro) {
        this.remainingAmountEuro = remainingAmountEuro;
    }
}
