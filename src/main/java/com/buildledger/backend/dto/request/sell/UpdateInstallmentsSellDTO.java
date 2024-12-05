package com.buildledger.backend.dto.request.sell;

import com.buildledger.backend.dto.nested.InstallmentAndDate;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateInstallmentsSellDTO {
    private int installmentsCount;
    private List<InstallmentAndDate> installmentAndDates;

    public int getInstallmentsCount() {
        return installmentsCount;
    }

    public void setInstallmentsCount(int installmentsCount) {
        this.installmentsCount = installmentsCount;
    }

    public List<InstallmentAndDate> getInstallmentAndDates() {
        return installmentAndDates;
    }

    public void setInstallmentAndDates(List<InstallmentAndDate> installmentAndDates) {
        this.installmentAndDates = installmentAndDates;
    }
}
