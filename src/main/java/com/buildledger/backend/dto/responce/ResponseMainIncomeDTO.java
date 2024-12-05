package com.buildledger.backend.dto.responce;

import com.buildledger.backend.enums.PayStatus;

import java.time.LocalDate;

public class ResponseMainIncomeDTO {
    private long id;

    private LocalDate date;
    private double amountEuro;
    private PayStatus payStatus;
    private double payedAmountEuro;
    private double remainingAmountEuro;
    private String log;
    private String project;
    private String cooperation;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmountEuro() {
        return amountEuro;
    }

    public void setAmountEuro(double amountEuro) {
        this.amountEuro = amountEuro;
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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getCooperation() {
        return cooperation;
    }

    public void setCooperation(String cooperation) {
        this.cooperation = cooperation;
    }
}
