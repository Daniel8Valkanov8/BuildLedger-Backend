package com.buildledger.backend.junit.service.create;

import com.buildledger.backend.dto.request.building.CreateNewProjectDTO;
import com.buildledger.backend.dto.responce.building.ResponseByIdProjectDTO;
import com.buildledger.backend.dto.responce.building.ResponseNewProjectDTO;
import com.buildledger.backend.model.Parcel;
import com.buildledger.backend.model.Project;
import com.buildledger.backend.repository.ParcelRepository;
import com.buildledger.backend.repository.ProjectRepository;
import com.buildledger.backend.service.impl.ParcelService;
import com.buildledger.backend.service.impl.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreateProjectTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ParcelRepository parcelRepository;

    @Mock
    private ParcelService parcelService;

    @InjectMocks
    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        // Инициализация на мока и анотации
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateNewProjectSuccessfully() {
        // Тест за успешно създаване на нов проект и запис на свързан парцел
        CreateNewProjectDTO createNewProjectDTO = new CreateNewProjectDTO(
                "Project Title",
                "123456789",
                "123 Main Street",
                500.0,
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2025, 1, 1),
                10,
                "Cooperation",
                "Company"
        );

        Project savedProject = new Project();
        savedProject.setId(1L);
        Parcel savedParcel = new Parcel();
        savedParcel.setId(1L);

        when(projectRepository.saveAndFlush(any(Project.class))).thenReturn(savedProject);
        when(parcelService.createParcelByProject(any(CreateNewProjectDTO.class), any(Project.class)))
                .thenReturn(savedParcel);
        when(parcelRepository.saveAndFlush(any(Parcel.class))).thenReturn(savedParcel);

        ResponseNewProjectDTO response = projectService.createNewProject(createNewProjectDTO);

        assertNotNull(response, "Response should not be null");
        assertEquals(savedProject.getId(), response.getId(), "Project IDs should match");
        verify(projectRepository, times(2)).saveAndFlush(any(Project.class));
        verify(parcelService, times(1)).createParcelByProject(createNewProjectDTO, savedProject);
        verify(parcelRepository, times(1)).saveAndFlush(any(Parcel.class));
    }

    @Test
    void testGetProjectById() {
        // Тест за извличане на проект по ID
        Long projectId = 1L;
        Project project = new Project();
        project.setId(projectId);
        project.setTitle("Test Project");
        project.setEik("123456");
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now().plusDays(30));
        Parcel parcel = new Parcel();
        parcel.setId(2L); // Добавяне на ID
        project.setParcel(parcel);


        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        ResponseByIdProjectDTO response = projectService.getProjectById(projectId);

        assertNotNull(response, "Response should not be null");
        assertEquals(projectId, response.getId(), "Project IDs should match");
        verify(projectRepository, times(1)).findById(projectId);
    }

    @Test
    void testGetAllProjects() {
        // Тест за извличане на всички проекти
        Project project1 = new Project();
        project1.setId(1L);
        project1.setTitle("Project 1");

        Project project2 = new Project();
        project2.setId(2L);
        project2.setTitle("Project 2");

        when(projectRepository.findAll()).thenReturn(List.of(project1, project2));

        List<ResponseNewProjectDTO> responseList = projectService.getAllProjects();

        assertNotNull(responseList, "Response list should not be null");
        assertEquals(2, responseList.size(), "Response list size should match");
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    void testGetProjectByParcelId() {
        // Тест за извличане на проект по Parcel ID
        Long parcelId = 1L;
        Project project = new Project();
        project.setTitle("Test Project");

        when(projectRepository.findByParcelId(parcelId)).thenReturn(project);

        String projectTitle = projectService.getProjectByParcelId(parcelId);

        assertNotNull(projectTitle, "Project title should not be null");
        assertEquals("Test Project", projectTitle, "Project titles should match");
        verify(projectRepository, times(1)).findByParcelId(parcelId);
    }
}
