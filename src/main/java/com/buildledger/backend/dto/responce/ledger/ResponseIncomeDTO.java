package com.buildledger.backend.dto.responce.ledger;

import com.buildledger.backend.enums.PayStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ResponseIncomeDTO {
    private long id;
    private double amountEuro;
    private LocalDate date;
    private PayStatus payStatus;
    private double payedAmountEuro;
    private double remainingAmountEuro;
    private String log;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
