package com.buildledger.backend.dto.request.sos;


import java.util.ArrayList;
import java.util.List;

public class ResponseApartmentTableRowDTO {
    private long id;

    private String number;
    private double priceEur;

    private double area;
    private boolean sold;
    private double cleanArea;
    private double commonPartsPercentage;
    private double commonParts;
    private double adjoiningTerrace;
    private double adjoiningYardRoof;

    private double pricePerSquareMeter;
    private double priceYard;

    private double discountInEuro;
    private String compensation;

    private double contractPriceInEuro;
    private double totalPriceInEuro;
    private String purchaser;

    private double amountReceived;
    private double amountRemaining;
    private int installmentsCount;
    private List<Double> installments = new ArrayList<>();



    private double brokerProfitInEuro;
    private double brokerProfitInPercentage;

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getPriceEur() {
        return priceEur;
    }

    public void setPriceEur(double priceEur) {
        this.priceEur = priceEur;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public double getCleanArea() {
        return cleanArea;
    }

    public void setCleanArea(double cleanArea) {
        this.cleanArea = cleanArea;
    }

    public double getCommonPartsPercentage() {
        return commonPartsPercentage;
    }

    public void setCommonPartsPercentage(double commonPartsPercentage) {
        this.commonPartsPercentage = commonPartsPercentage;
    }

    public double getCommonParts() {
        return commonParts;
    }

    public void setCommonParts(double commonParts) {
        this.commonParts = commonParts;
    }

    public double getAdjoiningTerrace() {
        return adjoiningTerrace;
    }

    public void setAdjoiningTerrace(double adjoiningTerrace) {
        this.adjoiningTerrace = adjoiningTerrace;
    }

    public double getAdjoiningYardRoof() {
        return adjoiningYardRoof;
    }

    public void setAdjoiningYardRoof(double adjoiningYardRoof) {
        this.adjoiningYardRoof = adjoiningYardRoof;
    }

    public double getPricePerSquareMeter() {
        return pricePerSquareMeter;
    }

    public void setPricePerSquareMeter(double pricePerSquareMeter) {
        this.pricePerSquareMeter = pricePerSquareMeter;
    }

    public double getPriceYard() {
        return priceYard;
    }

    public void setPriceYard(double priceYard) {
        this.priceYard = priceYard;
    }

    public double getDiscountInEuro() {
        return discountInEuro;
    }

    public void setDiscountInEuro(double discountInEuro) {
        this.discountInEuro = discountInEuro;
    }

    public String getCompensation() {
        return compensation;
    }

    public void setCompensation(String compensation) {
        this.compensation = compensation;
    }

    public double getContractPriceInEuro() {
        return contractPriceInEuro;
    }

    public void setContractPriceInEuro(double contractPriceInEuro) {
        this.contractPriceInEuro = contractPriceInEuro;
    }

    public double getTotalPriceInEuro() {
        return totalPriceInEuro;
    }

    public void setTotalPriceInEuro(double totalPriceInEuro) {
        this.totalPriceInEuro = totalPriceInEuro;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
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

    public int getInstallmentsCount() {
        return installmentsCount;
    }

    public void setInstallmentsCount(int installmentsCount) {
        this.installmentsCount = installmentsCount;
    }

    public List<Double> getInstallments() {
        return installments;
    }

    public void setInstallments(List<Double> installments) {
        this.installments = installments;
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
}