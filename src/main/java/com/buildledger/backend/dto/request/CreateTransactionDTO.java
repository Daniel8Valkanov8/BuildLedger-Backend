package com.buildledger.backend.dto.request;

import com.buildledger.backend.enums.MyTransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateTransactionDTO {

    private Long id;
    private double amountEuro;

    private MyTransactionStatus myTransactionStatus;
    private String payStatus;
    private double payedAmountEuro;
    private double remainingAmountEuro;
    private String transactionLog;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmountEuro() {
        return amountEuro;
    }

    public void setAmountEuro(double amountEuro) {
        this.amountEuro = amountEuro;
    }

    public MyTransactionStatus getMyTransactionStatus() {
        return myTransactionStatus;
    }

    public void setMyTransactionStatus(MyTransactionStatus myTransactionStatus) {
        this.myTransactionStatus = myTransactionStatus;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
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

    public String getTransactionLog() {
        return transactionLog;
    }

    public void setTransactionLog(String transactionLog) {
        this.transactionLog = transactionLog;
    }
}
