package com.buildledger.backend.model.ledger;

import com.buildledger.backend.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class PaymentSchema extends BaseEntity {


    private String title;
    private int installmentCount;

    @ElementCollection
    @CollectionTable(name = "payment_schema_installments", joinColumns = @JoinColumn(name = "payment_schema_id"))
    @Column(name = "percent")
    private List<Integer> percentOfInstallments;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getInstallmentCount() {
        return installmentCount;
    }

    public void setInstallmentCount(int installmentCount) {
        this.installmentCount = installmentCount;
    }

    public List<Integer> getPercentOfInstallments() {
        return percentOfInstallments;
    }

    public void setPercentOfInstallments(List<Integer> percentOfInstallments) {
        this.percentOfInstallments = percentOfInstallments;
    }
}
