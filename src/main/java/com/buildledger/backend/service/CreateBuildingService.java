package com.buildledger.backend.service;


import com.buildledger.backend.dto.request.building.CreateIntermediateDTO;
import com.buildledger.backend.dto.request.building.CreateNewProjectDTO;
import com.buildledger.backend.model.Parcel;
import com.buildledger.backend.model.building.Building;

public interface CreateBuildingService
        <T extends Building, ID> {

    void createBuildingByProjectDTO(CreateNewProjectDTO createNewProjectDTO, Parcel parcel);

    void addEntryToCooperation(CreateIntermediateDTO createIntermediateDTO);
}

