package com.buildledger.backend.model.ledger;

import com.buildledger.backend.BaseEntity;
import com.buildledger.backend.enums.SellStatus;
import com.buildledger.backend.model.abstraction.Person;
import com.buildledger.backend.model.building.Building;
import com.buildledger.backend.model.sos.Apartment;
import com.buildledger.backend.model.sos.Garage;
import com.buildledger.backend.model.persons.Broker;
import com.buildledger.backend.model.persons.Purchaser;
import com.buildledger.backend.model.sos.ParkingPlace;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Entity
@Getter
@Setter
public class Sell extends BaseEntity {

    private Long cooperationId;

    private double discountInEuro;
    private double totalPriceInEuro;
    private double contractPriceInEuro;
    private double brokerProfitInEuro;
    private double brokerProfitInPercentage;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    private String description;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    //todo file upload
    private String filePath;
    private LocalDate contractDate;


    @ManyToOne()
    @JoinColumn(name = "broker_id")
    private Broker broker;


    @ManyToOne()
    @JoinColumn(name = "purchaser_id") // Променете името на колоната
    private Purchaser purchaser;

    @OneToMany(mappedBy = "sell")

    private Set<Apartment> apartments = new HashSet<>();

    @OneToMany(mappedBy = "sell")
    private Set<Garage> garages = new HashSet<>();

    @OneToMany(mappedBy = "sell")
    private Set<ParkingPlace> parkingPlaces = new HashSet<>();

    @JoinColumn(name = "payment_id")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Payment payment;

    public Long getCooperationId() {
        return cooperationId;
    }

    public double getContractPriceInEuro() {
        return contractPriceInEuro;
    }

    public void setContractPriceInEuro(double contractPriceInEuro) {
        this.contractPriceInEuro = contractPriceInEuro;
    }

    public void setCooperationId(Long cooperationId) {
        this.cooperationId = cooperationId;
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

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public Broker getBroker() {
        return broker;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    public Purchaser getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(Purchaser purchaser) {
        this.purchaser = purchaser;
    }

    public Set<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(Set<Apartment> apartments) {
        this.apartments = apartments;
    }

    public Set<Garage> getGarages() {
        return garages;
    }

    public void setGarages(Set<Garage> garages) {
        this.garages = garages;
    }

    public Set<ParkingPlace> getParkingPlaces() {
        return parkingPlaces;
    }

    public void setParkingPlaces(Set<ParkingPlace> parkingPlaces) {
        this.parkingPlaces = parkingPlaces;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
