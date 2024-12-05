package com.buildledger.backend.model.ledger.accounting;

import com.buildledger.backend.enums.MyTransactionStatus;
import com.buildledger.backend.model.building.Building;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction extends BaseLedger {
    private String transactionLog;
    private MyTransactionStatus transactionStatus;
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    public String getTransactionLog() {
        return transactionLog;
    }

    public void setTransactionLog(String transactionLog) {
        this.transactionLog = transactionLog;
    }

    public MyTransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(MyTransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}