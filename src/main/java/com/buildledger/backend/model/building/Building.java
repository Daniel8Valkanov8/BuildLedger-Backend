package com.buildledger.backend.model.building;

import com.buildledger.backend.BaseEntity;
import com.buildledger.backend.enums.Stage;
import com.buildledger.backend.model.Parcel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)  // Или можете да използвате SINGLE_TABLE или TABLE_PER_CLASS
@Table(name = "buildings")
public abstract class Building extends BaseEntity {


    @Column(nullable = false)
    private String title;

    private String description;
    private double rsp;
    private int entranceCount = 1;


    @ManyToOne
    @JoinColumn(name = "parcel_id")
    private Parcel parcel;

    public Parcel getParcel() {
        return parcel;
    }

    public void setParcel(Parcel parcel) {
        this.parcel = parcel;
    }

    public int getEntranceCount() {
        return entranceCount;
    }

    public void setEntranceCount(int entranceCount) {
        this.entranceCount = entranceCount;
    }

    public double getRsp() {
        return rsp;
    }

    public void setRsp(double rsp) {
        this.rsp = rsp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
