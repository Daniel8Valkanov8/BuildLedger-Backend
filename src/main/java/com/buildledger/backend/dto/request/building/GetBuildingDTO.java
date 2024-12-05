package com.buildledger.backend.dto.request.building;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetBuildingDTO {

    private Long parcelId;
    private String buildingStatus;

    public Long getParcelId() {
        return parcelId;
    }

    public void setParcelId(Long parcelId) {
        this.parcelId = parcelId;
    }

    public String getBuildingStatus() {
        return buildingStatus;
    }

    public void setBuildingStatus(String buildingStatus) {
        this.buildingStatus = buildingStatus;
    }
}
