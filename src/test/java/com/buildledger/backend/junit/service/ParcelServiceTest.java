package com.buildledger.backend.junit.service;


import com.buildledger.backend.dto.request.building.CreateNewProjectDTO;
import com.buildledger.backend.dto.request.building.UpdateParcelDTO;
import com.buildledger.backend.dto.responce.building.ResponseParcelDTO;
import com.buildledger.backend.model.Parcel;
import com.buildledger.backend.model.Project;
import com.buildledger.backend.repository.CooperationRepository;
import com.buildledger.backend.repository.HouseRepository;
import com.buildledger.backend.repository.ParcelRepository;
import com.buildledger.backend.repository.ProjectRepository;
import com.buildledger.backend.service.impl.ParcelService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParcelServiceTest {

    @InjectMocks
    private ParcelService parcelService;

    @Mock
    private ParcelRepository parcelRepository;

    @Mock
    private CooperationRepository cooperationRepository;

    @Mock
    private HouseRepository houseRepository;

    @Mock
    private ProjectRepository projectRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateParcelByProject() {
        CreateNewProjectDTO createNewProjectDTO = new CreateNewProjectDTO();
        createNewProjectDTO.setEik("12345");
        createNewProjectDTO.setAddress("Test Address");
        createNewProjectDTO.setParcelArea(500);
        createNewProjectDTO.setBuildingStatus("Cooperation");

        Project project = new Project();
        project.setTitle("Test Project");

        Parcel mockSavedParcel = new Parcel();
        mockSavedParcel.setId(1L);
        mockSavedParcel.setEik("12345");
        mockSavedParcel.setProject(project);

        when(parcelRepository.saveAndFlush(any(Parcel.class))).thenReturn(mockSavedParcel);

        Parcel result = parcelService.createParcelByProject(createNewProjectDTO, project);

        assertNotNull(result);
        assertEquals("12345", result.getEik());  // Проверка за стойността "Eik"
        verify(parcelRepository, times(1)).saveAndFlush(any(Parcel.class));
    }


    @Test
    void testFindAllParcels() {
        // Създаване на проекти за всеки парцел
        Project project1 = new Project();
        project1.setTitle("Project 1");

        Project project2 = new Project();
        project2.setTitle("Project 2");

        // Създаване на парцели
        Parcel parcel1 = new Parcel();
        parcel1.setId(1L);
        parcel1.setEik("111");
        parcel1.setProject(project1); // Задаване на проект на парцела

        Parcel parcel2 = new Parcel();
        parcel2.setId(2L);
        parcel2.setEik("222");
        parcel2.setProject(project2); // Задаване на проект на парцела

        // Мокиране на поведение на repository
        when(parcelRepository.findAll()).thenReturn(Arrays.asList(parcel1, parcel2));

        // Извикване на метода
        List<ResponseParcelDTO> result = parcelService.findAllParcels();

        // Проверки
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("111", result.get(0).getEik());
        assertEquals("222", result.get(1).getEik());
        assertEquals("Project 1", result.get(0).getProjectTitle());
        assertEquals("Project 2", result.get(1).getProjectTitle());
    }


    @Test
    void testFindParcelById_Found() {
        Parcel parcel = new Parcel();
        parcel.setId(1L);
        parcel.setEik("111");

        // Създаваме мокнат проект
        Project project = new Project();
        project.setTitle("Test Project");
        project.setBuildingStatus("Cooperation");

        // Свързваме проекта с парцела
        parcel.setProject(project);

        when(parcelRepository.findById(1L)).thenReturn(Optional.of(parcel));

        Optional<ResponseParcelDTO> result = parcelService.findParcelById(1L);

        assertTrue(result.isPresent());
        assertEquals("111", result.get().getEik());
        assertEquals("Test Project", result.get().getProjectTitle());
        assertEquals("Cooperation", result.get().getStatus());
    }

   // @Test
   // void testUpdateParcelByProjectId() {
   //     UpdateParcelDTO parcelDTO = new UpdateParcelDTO();
   //     parcelDTO.setWithElectricityAndWater("Yes"); // Уверете се, че не е null
   //     parcelDTO.setAddress("Updated Address");
   //     parcelDTO.setArea(600);
//
   //     Project project = new Project();
   //     Parcel parcel = new Parcel();
   //     project.setParcel(parcel);
//
   //     when(projectRepository.findById(anyLong())).thenReturn(Optional.of(project));
   //     when(parcelRepository.saveAndFlush(any(Parcel.class))).thenReturn(parcel);
//
   //     ResponseParcelDTO result = parcelService.updateParcelByProjectId(1L, parcelDTO);
//
   //     assertNotNull(result);
   //     assertTrue(result.isWithElectricityAndWater());
   // }

    @Test
    void testFindParcelById_NotFound() {
        when(parcelRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<ResponseParcelDTO> result = parcelService.findParcelById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void testDeleteParcelById_Found() {
        when(parcelRepository.existsById(1L)).thenReturn(true);

        parcelService.deleteParcelById(1L);

        verify(parcelRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteParcelById_NotFound() {
        when(parcelRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> parcelService.deleteParcelById(1L));
        verify(parcelRepository, never()).deleteById(1L);
    }


}
