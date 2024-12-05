package com.buildledger.backend.dto.responce.sell;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePaymentSchemaDTO {
    private long id;
    private String title;
    private int installmentCount;
    private List<Integer> percentOfInstallments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
