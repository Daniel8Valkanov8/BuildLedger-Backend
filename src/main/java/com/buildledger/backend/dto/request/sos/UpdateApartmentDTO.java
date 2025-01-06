package com.buildledger.backend.dto.request.sos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateApartmentDTO {
    private Long id; // Long вместо long
    private Long cooperationId;
    private String number;
    private Double area; // Double вместо double

    private Double cleanArea;
    private Double commonPartsPercentage;
    private Double commonParts;
    private Double adjoiningTerrace;
    private Double adjoiningYardRoof;
    private String compensation;
    private Double pricePerSquareMeter;
    private Double priceYard;

    private Integer bedroomCount; // Integer вместо int
    private Integer bathroomCount;
    private Long floorId;
    private Double priceEur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCooperationId() {
        return cooperationId;
    }

    public void setCooperationId(Long cooperationId) {
        this.cooperationId = cooperationId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getCleanArea() {
        return cleanArea;
    }

    public void setCleanArea(Double cleanArea) {
        this.cleanArea = cleanArea;
    }

    public Double getCommonPartsPercentage() {
        return commonPartsPercentage;
    }

    public void setCommonPartsPercentage(Double commonPartsPercentage) {
        this.commonPartsPercentage = commonPartsPercentage;
    }

    public Double getCommonParts() {
        return commonParts;
    }

    public void setCommonParts(Double commonParts) {
        this.commonParts = commonParts;
    }

    public Double getAdjoiningTerrace() {
        return adjoiningTerrace;
    }

    public void setAdjoiningTerrace(Double adjoiningTerrace) {
        this.adjoiningTerrace = adjoiningTerrace;
    }

    public Double getAdjoiningYardRoof() {
        return adjoiningYardRoof;
    }

    public void setAdjoiningYardRoof(Double adjoiningYardRoof) {
        this.adjoiningYardRoof = adjoiningYardRoof;
    }

    public String getCompensation() {
        return compensation;
    }

    public void setCompensation(String compensation) {
        this.compensation = compensation;
    }

    public Double getPricePerSquareMeter() {
        return pricePerSquareMeter;
    }

    public void setPricePerSquareMeter(Double pricePerSquareMeter) {
        this.pricePerSquareMeter = pricePerSquareMeter;
    }

    public Double getPriceYard() {
        return priceYard;
    }

    public void setPriceYard(Double priceYard) {
        this.priceYard = priceYard;
    }

    public Integer getBedroomCount() {
        return bedroomCount;
    }

    public void setBedroomCount(Integer bedroomCount) {
        this.bedroomCount = bedroomCount;
    }

    public Integer getBathroomCount() {
        return bathroomCount;
    }

    public void setBathroomCount(Integer bathroomCount) {
        this.bathroomCount = bathroomCount;
    }

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public Double getPriceEur() {
        return priceEur;
    }

    public void setPriceEur(Double priceEur) {
        this.priceEur = priceEur;
    }
}


