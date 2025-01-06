package com.buildledger.backend.dto.request.sell;

import com.buildledger.backend.dto.nested.InstallmentAndDate;
import com.buildledger.backend.dto.nested.SelfContainedUnits;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CreateSellDTO {
    private double discountInEuro;
    private double totalPriceInEuro;
    private double contractPriceInEuro;
    private String paymentSchema;  // paymentSchemaId -> paymentSchema
    private InstallmentAndDate[] installmentAndDates; // installments -> installmentAndDates
    private SelfContainedUnits[] selfContainedUnits;


    private double brokerProfitInPercentage;
    private double brokerProfitInEuro;
    
    private LocalDate contractDate;
    private String purchaserFirstName;
    private String purchaserLastName;
    private String purchaserEmail;
    private String brokerFirstName;
    private String brokerLastName;
    private String brokerEmail;
    private String description;

    public double getContractPriceInEuro() {
        return contractPriceInEuro;
    }

    public void setContractPriceInEuro(double contractPriceInEuro) {
        this.contractPriceInEuro = contractPriceInEuro;
    }

    public double getDiscountInEuro() {
        return discountInEuro;
    }

    public void setDiscountInEuro(double discountInEuro) {
        this.discountInEuro = discountInEuro;
    }

    public double getTotalPriceInEuro() {
        return totalPriceInEuro;
    }

    public void setTotalPriceInEuro(double totalPriceInEuro) {
        this.totalPriceInEuro = totalPriceInEuro;
    }

    public String getPaymentSchema() {
        return paymentSchema;
    }

    public void setPaymentSchema(String paymentSchema) {
        this.paymentSchema = paymentSchema;
    }

    public InstallmentAndDate[] getInstallmentAndDates() {
        return installmentAndDates;
    }

    public void setInstallmentAndDates(InstallmentAndDate[] installmentAndDates) {
        this.installmentAndDates = installmentAndDates;
    }

    public SelfContainedUnits[] getSelfContainedUnits() {
        return selfContainedUnits;
    }

    public void setSelfContainedUnits(SelfContainedUnits[] selfContainedUnits) {
        this.selfContainedUnits = selfContainedUnits;
    }

    public double getBrokerProfitInPercentage() {
        return brokerProfitInPercentage;
    }

    public void setBrokerProfitInPercentage(double brokerProfitInPercentage) {
        this.brokerProfitInPercentage = brokerProfitInPercentage;
    }

    public double getBrokerProfitInEuro() {
        return brokerProfitInEuro;
    }

    public void setBrokerProfitInEuro(double brokerProfitInEuro) {
        this.brokerProfitInEuro = brokerProfitInEuro;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public String getPurchaserFirstName() {
        return purchaserFirstName;
    }

    public void setPurchaserFirstName(String purchaserFirstName) {
        this.purchaserFirstName = purchaserFirstName;
    }

    public String getPurchaserLastName() {
        return purchaserLastName;
    }

    public void setPurchaserLastName(String purchaserLastName) {
        this.purchaserLastName = purchaserLastName;
    }

    public String getPurchaserEmail() {
        return purchaserEmail;
    }

    public void setPurchaserEmail(String purchaserEmail) {
        this.purchaserEmail = purchaserEmail;
    }

    public String getBrokerFirstName() {
        return brokerFirstName;
    }

    public void setBrokerFirstName(String brokerFirstName) {
        this.brokerFirstName = brokerFirstName;
    }

    public String getBrokerLastName() {
        return brokerLastName;
    }

    public void setBrokerLastName(String brokerLastName) {
        this.brokerLastName = brokerLastName;
    }

    public String getBrokerEmail() {
        return brokerEmail;
    }

    public void setBrokerEmail(String brokerEmail) {
        this.brokerEmail = brokerEmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{\n");

        jsonBuilder.append("  \"paymentSchema\": \"").append(paymentSchema).append("\",\n");
        for (InstallmentAndDate installmentAndDate : this.installmentAndDates) {
            jsonBuilder.append("  \"installmentAndDate\": ").append(installmentAndDate).append(",\n");
            jsonBuilder.append("  \"sumInEuros\": ").append(installmentAndDate.getSumInEuros()).append(",\n");
            jsonBuilder.append("  \"date\": \"").append(installmentAndDate.getDate().toString()).append("\",\n");

        }

        for (SelfContainedUnits selfContainedUnit : selfContainedUnits) {
            jsonBuilder.append("  \"selfContainedUnit\": ").append(selfContainedUnit).append(",\n");
            jsonBuilder.append("  \"id\": ").append(selfContainedUnit.getId()).append(",\n");
            jsonBuilder.append("  \"number\": \"").append(selfContainedUnit.getNumber()).append("\",\n");
            jsonBuilder.append("  \"priceEur\": ").append(selfContainedUnit.getPriceEur()).append(",\n");
        }
        jsonBuilder.append("  \"discountInEuro\": ").append(discountInEuro).append(",\n");
        jsonBuilder.append("  \"totalPriceInEuro\": ").append(totalPriceInEuro).append(",\n");
        jsonBuilder.append("  \"brokerProfitInPercentage\": ").append(brokerProfitInPercentage).append(",\n");
        jsonBuilder.append("  \"brokerProfitInEuro\": ").append(brokerProfitInEuro).append(",\n");


        jsonBuilder.append("  \"purchaserFirstName\": \"").append(purchaserFirstName).append("\",\n");
        jsonBuilder.append("  \"purchaserLastName\": \"").append(purchaserLastName).append("\",\n");
        jsonBuilder.append("  \"purchaserEmail\": \"").append(purchaserEmail).append("\",\n");
        jsonBuilder.append("  \"brokerFirstName\": \"").append(brokerFirstName).append("\",\n");
        jsonBuilder.append("  \"brokerLastName\": \"").append(brokerLastName).append("\",\n");
        jsonBuilder.append("  \"brokerEmail\": \"").append(brokerEmail).append("\",\n");
        jsonBuilder.append("  \"description\": \"").append(description).append("\",\n");

        jsonBuilder.append("}");

        return jsonBuilder.toString();
    }
}
