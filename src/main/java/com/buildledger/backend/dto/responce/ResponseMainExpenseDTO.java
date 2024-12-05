package com.buildledger.backend.dto.responce;

import java.time.LocalDate;

public class ResponseMainExpenseDTO {
    private long id;
    private String factureNumber;
    private String title;
    private String status;


    private double amountEuro;
    private LocalDate paymentDate;
    private double paid;
    private double remain;

    private String project;
    private String cooperation;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFactureNumber() {
        return factureNumber;
    }

    public void setFactureNumber(String factureNumber) {
        this.factureNumber = factureNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmountEuro() {
        return amountEuro;
    }

    public void setAmountEuro(double amountEuro) {
        this.amountEuro = amountEuro;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public double getRemain() {
        return remain;
    }

    public void setRemain(double remain) {
        this.remain = remain;
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
