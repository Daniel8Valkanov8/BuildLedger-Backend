package com.buildledger.backend.model.ledger;

import com.buildledger.backend.BaseEntity;
import com.buildledger.backend.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Payment extends BaseEntity {


    @Column()
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private double amountReceived;
    private double amountRemaining;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Installment> installments = new HashSet<>();

    private int installmentCount;

    @OneToOne()
    @JoinColumn(name = "sell_id")
    private Sell sell;

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public double getAmountReceived() {
        return amountReceived;
    }

    public void setAmountReceived(double amountReceived) {
        this.amountReceived = amountReceived;
    }

    public double getAmountRemaining() {
        return amountRemaining;
    }

    public void setAmountRemaining(double amountRemaining) {
        this.amountRemaining = amountRemaining;
    }

    public Set<Installment> getInstallments() {
        return installments;
    }

    public void setInstallments(Set<Installment> installments) {
        this.installments = installments;
    }

    public int getInstallmentCount() {
        return installmentCount;
    }

    public void setInstallmentCount(int installmentCount) {
        this.installmentCount = installmentCount;
    }

    public Sell getSell() {
        return sell;
    }

    public void setSell(Sell sell) {
        this.sell = sell;
    }
}
