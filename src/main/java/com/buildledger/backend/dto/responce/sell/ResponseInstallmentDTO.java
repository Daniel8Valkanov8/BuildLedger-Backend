package com.buildledger.backend.dto.responce.sell;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ResponseInstallmentDTO {
    private long id;
    private String sell;
    private double amount;
    private LocalDate installmentDate;
    private String installmentLog;
    private boolean isPayStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getInstallmentDate() {
        return installmentDate;
    }

    public void setInstallmentDate(LocalDate installmentDate) {
        this.installmentDate = installmentDate;
    }

    public String getInstallmentLog() {
        return installmentLog;
    }

    public void setInstallmentLog(String installmentLog) {
        this.installmentLog = installmentLog;
    }

    public boolean isPayStatus() {
        return isPayStatus;
    }

    public void setPayStatus(boolean payStatus) {
        isPayStatus = payStatus;
    }
}
