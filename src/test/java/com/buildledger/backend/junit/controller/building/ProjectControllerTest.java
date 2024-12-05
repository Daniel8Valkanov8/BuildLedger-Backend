package com.buildledger.backend.junit.controller.building;

import com.buildledger.backend.controller.ProjectController;
import com.buildledger.backend.dto.request.building.CreateNewProjectDTO;
import com.buildledger.backend.dto.responce.building.ResponseByIdProjectDTO;
import com.buildledger.backend.dto.responce.building.ResponseNewProjectDTO;
import com.buildledger.backend.service.impl.ProjectService;
import com.buildledger.backend.service.micro.ProjectDeleteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProjectService projectService;
    @MockBean
    private ProjectDeleteService projectDeleteService;
    @Autowired
    private ObjectMapper objectMapper;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testCreateNewProject() throws Exception {
        CreateNewProjectDTO newProjectDTO = new CreateNewProjectDTO(
                "New Project",
                "123456",
                "123 Main St",
                500.0,
                LocalDate.now(),
                LocalDate.now().plusDays(30),
                3,
                "In Progress"
        );
        ResponseNewProjectDTO response = new ResponseNewProjectDTO(1L, "New Project", "123456", LocalDate.now(), LocalDate.now().plusDays(30));
        when(projectService.createNewProject(any(CreateNewProjectDTO.class))).thenReturn(response);
        mockMvc.perform(post("/projects/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newProjectDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("New Project"))
                .andExpect(jsonPath("$.eik").value("123456"))
                .andExpect(jsonPath("$.startDate").isNotEmpty())
                .andExpect(jsonPath("$.endDate").isNotEmpty());
    }
    @Test
    void testGetProjectById() throws Exception {
        Long projectId = 1L;
        ResponseByIdProjectDTO response = new ResponseByIdProjectDTO(
                projectId,
                "Test Project",
                LocalDate.now(),
                LocalDate.now().plusDays(30),
                "123456",
                "In Progress",
                2L
        );
        when(projectService.getProjectById(projectId)).thenReturn(response);
        mockMvc.perform(get("/projects/{id}", projectId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(projectId))
                .andExpect(jsonPath("$.title").value("Test Project"))
                .andExpect(jsonPath("$.eik").value("123456"))
                .andExpect(jsonPath("$.buildingStatus").value("In Progress"))
                .andExpect(jsonPath("$.parcel").value(2L));
    }
}