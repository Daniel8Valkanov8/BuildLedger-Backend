package com.buildledger.backend.dto.responce.objects;


import com.buildledger.backend.model.persons.Broker;
import com.buildledger.backend.model.persons.Purchaser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseApartmentInformationDTO {
    private long id;
    private String number;
    private double area;
    private double priceEur;
    private long floorId;
    private boolean sold;
    private String description;
    private int bedroomCount;
    private int bathroomCount;


    private double discountInEuro;
    private double totalPriceInEuro;
    private double brokerProfitInEuro;
    private double brokerProfitInPercentage;


    private String broker;
    private String purchaser;
    private LocalDate contractDate;
    private int installmentCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPriceEur() {
        return priceEur;
    }

    public void setPriceEur(double priceEur) {
        this.priceEur = priceEur;
    }

    public long getFloorId() {
        return floorId;
    }

    public void setFloorId(long floorId) {
        this.floorId = floorId;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBedroomCount() {
        return bedroomCount;
    }

    public void setBedroomCount(int bedroomCount) {
        this.bedroomCount = bedroomCount;
    }

    public int getBathroomCount() {
        return bathroomCount;
    }

    public void setBathroomCount(int bathroomCount) {
        this.bathroomCount = bathroomCount;
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

    public double getBrokerProfitInEuro() {
        return brokerProfitInEuro;
    }

    public void setBrokerProfitInEuro(double brokerProfitInEuro) {
        this.brokerProfitInEuro = brokerProfitInEuro;
    }

    public double getBrokerProfitInPercentage() {
        return brokerProfitInPercentage;
    }

    public void setBrokerProfitInPercentage(double brokerProfitInPercentage) {
        this.brokerProfitInPercentage = brokerProfitInPercentage;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public int getInstallmentCount() {
        return installmentCount;
    }

    public void setInstallmentCount(int installmentCount) {
        this.installmentCount = installmentCount;
    }
}
