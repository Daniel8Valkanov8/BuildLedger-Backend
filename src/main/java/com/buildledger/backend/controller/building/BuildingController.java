package com.buildledger.backend.controller.building;

import com.buildledger.backend.dto.request.building.UpdateCooperationDTO;
import com.buildledger.backend.dto.responce.building.ResponseBuildingDTO;
import com.buildledger.backend.dto.responce.building.ResponseBuildingImplementationDTO;
import com.buildledger.backend.service.impl.building.BuildingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/buildings")
public class BuildingController {
    private final BuildingService buildingService;


    @Autowired
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping("/allByParcel/{parcelId}")
    public ResponseEntity<List<ResponseBuildingDTO>> getAllBuildingsByParcel(
            @PathVariable long parcelId) {

        List<ResponseBuildingDTO> buildings = buildingService.getBuildingsByParcelId(parcelId);

        return new ResponseEntity<>(buildings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBuildingImplementationDTO> getBuildingById(@PathVariable long id) {
        ResponseBuildingImplementationDTO building = buildingService.getBuildingById(id);
        System.out.println(building.getTitle());
        System.out.println("in controller: "+building.getType());// Това вече трябва да печата правилното заглавие
        return new ResponseEntity<>(building, HttpStatus.OK);
    }

    @PutMapping("/update/cooperation")
    public ResponseEntity<?> updateCooperation(@Valid @RequestBody UpdateCooperationDTO updateCooperationDTO) {
        try {
            ResponseBuildingImplementationDTO updatedBuilding = buildingService.updateCooperation(updateCooperationDTO);
            return new ResponseEntity<>(updatedBuilding, HttpStatus.OK);
        } catch (Exception e) {
            // Обработване на грешката, например ако има дублиращи се етапи
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

