package com.buildledger.backend.service.impl;


import com.buildledger.backend.dto.request.building.CreateNewProjectDTO;
import com.buildledger.backend.dto.request.building.UpdateParcelDTO;
import com.buildledger.backend.dto.responce.building.ResponseParcelDTO;
import com.buildledger.backend.model.Parcel;
import com.buildledger.backend.model.Project;
import com.buildledger.backend.repository.CooperationRepository;
import com.buildledger.backend.repository.HouseRepository;
import com.buildledger.backend.repository.ParcelRepository;
import com.buildledger.backend.repository.ProjectRepository;
import com.buildledger.backend.service.CreateBuildingService;
import com.buildledger.backend.service.impl.building.CooperationService;
import com.buildledger.backend.service.impl.building.HouseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ParcelService {
    private final ParcelRepository parcelRepository;
    private final CooperationRepository cooperationRepository;
    private final HouseRepository houseRepository;
    private final ProjectRepository projectRepository;
    private CreateBuildingService buildingService;
    public ParcelService(ParcelRepository parcelRepository, CooperationRepository buildingRepository, HouseRepository houseRepository, ProjectRepository projectRepository) {
        this.parcelRepository = parcelRepository;
        this.cooperationRepository = buildingRepository;
        this.houseRepository = houseRepository;
        this.projectRepository = projectRepository;
    }


    public Parcel createParcelByProject(CreateNewProjectDTO createNewProjectDTO, Project project) {
        // Създаване и запазване на Parcel
        Parcel parcel = new Parcel();

        parcel.setProject(project);
        parcel.setEik(createNewProjectDTO.getEik());
        parcel.setAddress(createNewProjectDTO.getAddress());
        parcel.setArea(createNewProjectDTO.getParcelArea());
        parcel.setCompensated(true);
        parcel.setRegular(true);
        parcel.setWithElectricityAndWater(true);
        parcel.setPercentageOfCompensation(25);

        Parcel savedParcel = parcelRepository.saveAndFlush(parcel);  // Запазваме Parcel

        // Създаване на сграда
        return createBuilding(createNewProjectDTO, savedParcel);
    }

    private Parcel createBuilding(CreateNewProjectDTO createNewProjectDTO, Parcel savedParcel) {
        if (createNewProjectDTO.getBuildingStatus().equals("Cooperation")) {
            this.buildingService = new CooperationService(cooperationRepository);

        } else if (createNewProjectDTO.getBuildingStatus().equals("House")) {
            this.buildingService = new HouseService(houseRepository);

        }
        this.buildingService.createBuildingByProjectDTO(createNewProjectDTO, savedParcel);
        return savedParcel;
    }


    public List<ResponseParcelDTO> findAllParcels() {
        // Извличаме всички парцели от базата
        List<Parcel> parcels = parcelRepository.findAll();

        // Преобразуваме списъка от Parcel в ResponseParcelDto
        return parcels.stream()
                .map(this::convertToDto) // Извикваме метода за конвертиране
                .collect(Collectors.toList());
    }


    public Optional<ResponseParcelDTO> findParcelById(Long id) {
        // Търсим парцела по неговото id
        Optional<Parcel> parcel = parcelRepository.findById(id);

        // Ако парцелът е намерен, преобразуваме го в ResponseParcelDto и връщаме резултата
        if (parcel.isPresent()) {
            ResponseParcelDTO responseParcelDto = convertToDto(parcel.get());
            return Optional.of(responseParcelDto);
        }

        // Ако не е намерен, връщаме празно Optional
        return Optional.empty();
    }

    public void deleteParcelById(Long id) {
        // Проверяваме дали парцелът съществува преди да го изтрием
        if (parcelRepository.existsById(id)) {
            parcelRepository.deleteById(id);
        } else {
            // Можем да хвърлим изключение, ако искаме да обработваме случая, когато парцелът не е намерен
            throw new EntityNotFoundException("Parcel with id " + id + " not found.");
        }
    }

    // Метод за конвертиране на Parcel към ResponseParcelDto
    private ResponseParcelDTO convertToDto(Parcel parcel) {
        ResponseParcelDTO dto = new ResponseParcelDTO();

        dto.setId(parcel.getId());
        dto.setEik(parcel.getEik());
        dto.setAddress(parcel.getAddress());
        dto.setArea(parcel.getArea());
        dto.setCompensated(parcel.isCompensated());
        dto.setRegular(parcel.isRegular());
        dto.setPercentageOfCompensation(parcel.getPercentageOfCompensation());
        dto.setWithElectricityAndWater(parcel.isWithElectricityAndWater());
        dto.setProjectTitle(parcel.getProject().getTitle());
        dto.setStatus(parcel.getProject().getBuildingStatus());
        return dto;
    }

    public ResponseParcelDTO updateParcelByProjectId(Long id, UpdateParcelDTO parcelDTO) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));

        Parcel parcel = project.getParcel();

        if (parcelDTO.getAddress() != null && !parcelDTO.getAddress().isEmpty()) {
            parcel.setAddress(parcelDTO.getAddress());
        }
        if (parcelDTO.getArea() > 0) {
            parcel.setArea(parcelDTO.getArea());
        }
        if (parcelDTO.getPercentageOfCompensation() > 0) {
            parcel.setPercentageOfCompensation(parcelDTO.getPercentageOfCompensation());
        }
        parcel.setPriceEur(parcelDTO.getPriceEur());

        parcel.setCompensated(parseBoolean(parcelDTO.getIsCompensated()));
        parcel.setRegular(parseBoolean(parcelDTO.getIsRegular()));
        parcel.setWithElectricityAndWater(parseBoolean(parcelDTO.getWithElectricityAndWater()));

        Parcel saved = parcelRepository.saveAndFlush(parcel);
        ResponseParcelDTO response = new ResponseParcelDTO();
        BeanUtils.copyProperties(saved, response);
        return response;
    }

    private Boolean parseBoolean(String value) {
        return "Yes".equalsIgnoreCase(value);
    }

}