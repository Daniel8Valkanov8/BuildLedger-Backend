package com.buildledger.backend.controller.building;

import com.buildledger.backend.dto.request.building.CreateIntermediateDTO;
import com.buildledger.backend.dto.responce.ResponseMessageDTO;
import com.buildledger.backend.service.impl.building.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class IntermediateController {
    private final CooperationService cooperationService;
    private final GarageService garageService;
    private final FloorService floorService;
    private final ApartmentService apartmentService;
    private final ParkingPlaceService parkingPlaceService;

    public IntermediateController(CooperationService cooperationService, GarageService garageService, FloorService floorService, ApartmentService apartmentService, ParkingPlaceService parkingPlaceService) {
        this.cooperationService = cooperationService;
        this.garageService = garageService;
        this.floorService = floorService;
        this.apartmentService = apartmentService;
        this.parkingPlaceService = parkingPlaceService;
    }


    @PostMapping("/quick-create")
    public ResponseEntity<List<ResponseMessageDTO>> createIntermediate( @RequestBody CreateIntermediateDTO createIntermediateDTO) {

        List<ResponseMessageDTO> response = new ArrayList<>();
        cooperationService.addEntryToCooperation(createIntermediateDTO);
        response.add(new ResponseMessageDTO("Entrance added successfully"));
        //todo print
        parkingPlaceService.createParkingPlaceByCount(createIntermediateDTO);
        response.add(new ResponseMessageDTO("Parking place created successfully"));
        //Floor creation in cooperation
        apartmentService.createApartmentByCount(createIntermediateDTO);
        response.add(new ResponseMessageDTO("Apartment created successfully"));

        garageService.createGarageByCount(createIntermediateDTO);
        response.add(new ResponseMessageDTO("Garage created successfully"));

        floorService.createFloorByCount(createIntermediateDTO);
        response.add(new ResponseMessageDTO("Floor created successfully"));

        floorService.createUndergroundFloor(createIntermediateDTO);
        response.add(new ResponseMessageDTO("Underground floor created successfully"));


        System.out.println(createIntermediateDTO.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/cooperation/{id}/delete-objects")
    public ResponseEntity<List<ResponseMessageDTO>> deleteAllObjectsInCooperation(@PathVariable Long id) {
        List<ResponseMessageDTO> response = new ArrayList<>();
        apartmentService.deleteAllApartmentsByCooperationId(id);
        response.add(new ResponseMessageDTO("Apartments deleted successfully"));
        garageService.deleteAllGaragesByCooperationId(id);
        response.add(new ResponseMessageDTO("Garages deleted successfully"));
        floorService.deleteAllFloorsByCooperationId(id);
        response.add(new ResponseMessageDTO("Floors deleted successfully"));
        parkingPlaceService.deleteAllParkingPlacesByCooperationId(id);
        response.add(new ResponseMessageDTO("Objects deleted successfully"));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
