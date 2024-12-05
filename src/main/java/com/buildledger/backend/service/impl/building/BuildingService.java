package com.buildledger.backend.service.impl.building;

import com.buildledger.backend.dto.request.building.UpdateCooperationDTO;
import com.buildledger.backend.dto.responce.building.ResponseBuildingDTO;
import com.buildledger.backend.dto.responce.building.ResponseBuildingImplementationDTO;
import com.buildledger.backend.enums.Stage;
import com.buildledger.backend.model.building.Building;
import com.buildledger.backend.model.building.Cooperation;
import com.buildledger.backend.model.building.House;
import com.buildledger.backend.repository.BuildingRepository;
import com.buildledger.backend.repository.CooperationRepository;
import com.buildledger.backend.repository.HouseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BuildingService {
    private final BuildingRepository buildingRepository;
    private final CooperationRepository cooperationRepository;
    private final HouseRepository houseRepository;

    public BuildingService(BuildingRepository buildingRepository, CooperationRepository cooperationRepository, HouseRepository houseRepository) {
        this.buildingRepository = buildingRepository;
        this.cooperationRepository = cooperationRepository;
        this.houseRepository = houseRepository;
    }

    //когато натиснем върху съответния парцел за да му видим всички сград
    public List<ResponseBuildingDTO> getBuildingsByParcelId(long parcelId) {

        List<Building> buildings = buildingRepository.findByParcelId(parcelId);
        List<ResponseBuildingDTO> responseBuildingDTOS = new ArrayList<>();

        for (Building building : buildings) {

            ResponseBuildingDTO
                    responseBuildingDTO = new ResponseBuildingDTO(
                    building.getId(), building.getTitle(), building.getDescription()
            );
            responseBuildingDTOS.add(responseBuildingDTO);

        }

        if (responseBuildingDTOS.isEmpty()) {
            System.out.println("No buildings found for parcelId: " + parcelId);
        }

        return responseBuildingDTOS;
    }


    //когато натиснем върху съответната сграда, проверим дали е кооперация или къща, и върнем данни за статута
    public ResponseBuildingImplementationDTO getBuildingById(long id) {
        // Полиморфната логика за обработка на Cooperation
        Optional<Cooperation> cooperation = cooperationRepository.findById(id);
        if (cooperation.isPresent()) {
            return convertFromCooperation(cooperation.get()); // Връщаме конвертирания Cooperation обект
        }

        // Полиморфната логика за обработка на House
        Optional<House> house = houseRepository.findById(id);
        if (house.isPresent()) {
            return convertFromHouse(house.get()); // Връщаме конвертирания House обект
        }

        // Ако не е намерен нито един тип сграда, връщаме празен обект или хвърляме изключение
        return new ResponseBuildingImplementationDTO();
    }
    public ResponseBuildingImplementationDTO updateCooperation(UpdateCooperationDTO updateCooperationDTO) {
         Optional<Cooperation> cooperation = cooperationRepository.findById(updateCooperationDTO.getId());

        if (cooperation.isPresent()) {
            if(updateCooperationDTO.getDescription() != null) {
                cooperation.get().setDescription(updateCooperationDTO.getDescription());
            }
            if (updateCooperationDTO.getRsp() != 0) cooperation.get().setRsp(updateCooperationDTO.getRsp());


            cooperationRepository.save(cooperation.get());
            return convertFromCooperation(cooperation.get());
        }
        return new ResponseBuildingImplementationDTO("Cooperation not found");
    }
    private ResponseBuildingImplementationDTO convertFromCooperation(Cooperation cooperation) {
        ResponseBuildingImplementationDTO cooperationDTO = new ResponseBuildingImplementationDTO();
        cooperationDTO.setTitle(cooperation.getTitle());
        cooperationDTO.setType("Cooperation");
        cooperationDTO.setDescription(cooperation.getDescription());
        cooperationDTO.setEntranceCount(cooperation.getEntranceCount());
        cooperationDTO.setRsp(cooperation.getRsp());
        cooperationDTO.setProjectName(cooperation.getParcel().getProject().getTitle());
        // Установяваме типа като "Cooperation"
        cooperationDTO.setId(cooperation.getId());
        return cooperationDTO;
    }

    private ResponseBuildingImplementationDTO convertFromHouse(House house) {
        ResponseBuildingImplementationDTO houseDTO = new ResponseBuildingImplementationDTO();
        houseDTO.setTitle(house.getTitle());
        houseDTO.setType("House");  // Установяваме типа като "House"
        return houseDTO;
    }


}
