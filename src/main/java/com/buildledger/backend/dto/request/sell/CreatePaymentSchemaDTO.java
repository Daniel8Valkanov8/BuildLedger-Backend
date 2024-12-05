package com.buildledger.backend.dto.request.sell;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentSchemaDTO {
    private String title;
    private int installmentCount;
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
