package com.buildledger.backend.model.ledger;


import com.buildledger.backend.BaseEntity;
import com.buildledger.backend.model.ledger.accounting.Income;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
    public class Installment extends BaseEntity {

    private double installmentAmount;

    @Column
    private LocalDate installmentDate;

    private String installmentLog;
    @Column
    private boolean isPayStatus;

    @OneToOne(mappedBy = "installment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "income_id")
    private Income  income;
    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    public double getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(double installmentAmount) {
        this.installmentAmount = installmentAmount;
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

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
