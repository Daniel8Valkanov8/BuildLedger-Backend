package com.buildledger.backend.dto.request.sell;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor@Getter
@Setter
public class ResponseSellForEditDTO {
    private long id;
    private String objects;
    private LocalDate contractDate;
    private String purchaserName;
    private String brokerName;
    private double totalPriceInEuro;
    private double discountInEuro;
    private int installmentsCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getObjects() {
        return objects;
    }

    public void setObjects(String objects) {
        this.objects = objects;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public double getTotalPriceInEuro() {
        return totalPriceInEuro;
    }

    public void setTotalPriceInEuro(double totalPriceInEuro) {
        this.totalPriceInEuro = totalPriceInEuro;
    }

    public double getDiscountInEuro() {
        return discountInEuro;
    }

    public void setDiscountInEuro(double discountInEuro) {
        this.discountInEuro = discountInEuro;
    }

    public int getInstallmentsCount() {
        return installmentsCount;
    }

    public void setInstallmentsCount(int installmentsCount) {
        this.installmentsCount = installmentsCount;
    }
}
