package com.buildledger.backend.model.sos;

import com.buildledger.backend.model.building.Building;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,unique = false)
    private String number;

    private Double commonArea;

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Apartment> apartments = new HashSet<>();

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Garage> garages = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building cooperation;

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

    public Double getCommonArea() {
        return commonArea;
    }

    public void setCommonArea(Double commonArea) {
        this.commonArea = commonArea;
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

    public Building getCooperation() {
        return cooperation;
    }

    public void setCooperation(Building cooperation) {
        this.cooperation = cooperation;
    }

    public Floor(String number) {
        this.number = number;
    }

    public Floor() {

    }
}
