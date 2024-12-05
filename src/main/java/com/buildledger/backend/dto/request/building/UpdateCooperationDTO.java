package com.buildledger.backend.dto.request.building;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCooperationDTO {
    private long id;
    private String description;
    private double rsp;
    private String stage;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRsp() {
        return rsp;
    }

    public void setRsp(double rsp) {
        this.rsp = rsp;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}
