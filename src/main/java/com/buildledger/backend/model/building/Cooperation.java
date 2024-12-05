package com.buildledger.backend.model.building;

import com.buildledger.backend.model.ledger.Sell;
import com.buildledger.backend.model.ledger.accounting.Expense;
import com.buildledger.backend.model.ledger.accounting.Income;
import com.buildledger.backend.model.ledger.accounting.Transaction;
import com.buildledger.backend.model.sos.Apartment;
import com.buildledger.backend.model.sos.Floor;
import com.buildledger.backend.model.sos.Garage;
import com.buildledger.backend.model.sos.ParkingPlace;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "cooperations")
public class Cooperation extends Building {

    private final String TYPE = "Cooperation";


    @OneToMany(mappedBy = "cooperation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Floor> floors = new HashSet<>();

    @OneToMany(mappedBy = "cooperation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Apartment> apartments = new HashSet<>();

    @OneToMany(mappedBy = "cooperation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Garage> garages = new HashSet<>();

    @OneToMany(mappedBy = "cooperation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ParkingPlace> parkingPlaces = new HashSet<>();


    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Sell> sells = new HashSet<>();

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Income>  incomes = new HashSet<>();


    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Expense> expenses = new HashSet<>();

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Transaction>  transactions = new HashSet<>();

    public String getTYPE() {
        return TYPE;
    }

    public Set<Floor> getFloors() {
        return floors;
    }

    public void setFloors(Set<Floor> floors) {
        this.floors = floors;
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

    public Set<Sell> getSells() {
        return sells;
    }

    public void setSells(Set<Sell> sells) {
        this.sells = sells;
    }

    public Set<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(Set<Income> incomes) {
        this.incomes = incomes;
    }

    public Set<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(Set<Expense> expenses) {
        this.expenses = expenses;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
}
