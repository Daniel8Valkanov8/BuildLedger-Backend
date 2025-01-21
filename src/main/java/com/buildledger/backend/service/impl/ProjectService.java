package com.buildledger.backend.service.impl;

import com.buildledger.backend.dto.request.building.CreateNewProjectDTO;
import com.buildledger.backend.dto.request.building.UpdateProjectDTO;
import com.buildledger.backend.dto.responce.building.ResponseByIdProjectDTO;
import com.buildledger.backend.dto.responce.building.ResponseNewProjectDTO;
import com.buildledger.backend.model.Parcel;
import com.buildledger.backend.model.Project;
import com.buildledger.backend.repository.ParcelRepository;
import com.buildledger.backend.repository.ProjectRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ParcelService parcelService;

    private final ParcelRepository parcelRepository;



    public ProjectService(ProjectRepository projectRepository,
                          ParcelService parcelService, ParcelRepository parcelRepository, ParcelRepository parcelRepository1) {
        this.projectRepository = projectRepository;
        this.parcelService = parcelService;
        this.parcelRepository = parcelRepository1;
    }


    public String deleteProjectById(Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return "Проектът с ID " + id + " беше успешно изтрит.";
        } else {
            return "Проектът с ID " + id + " не беше намерен.";
        }
    }


    public ResponseNewProjectDTO createNewProject(CreateNewProjectDTO newProjectDTO) {
        Project project = new Project();
        project.setTitle(newProjectDTO.getTitle());
        project.setCompanyName(newProjectDTO.getCompanyName());
        project.setEik(newProjectDTO.getEik());
        project.setStartDate(newProjectDTO.getStartDate());
        project.setEndDate(newProjectDTO.getEndDate());
        project.setBuildingStatus(newProjectDTO.getBuildingStatus());
        Project savedProject = projectRepository.saveAndFlush(project);

        Parcel parcel = parcelService.createParcelByProject(newProjectDTO, savedProject);
        savedProject.setParcel(parcel);
        parcel.setProject(savedProject);

        projectRepository.saveAndFlush(savedProject);
        parcelRepository.saveAndFlush(parcel);

        ResponseNewProjectDTO response = new ResponseNewProjectDTO();
        response.setId(savedProject.getId());
        response.setTitle(savedProject.getTitle());
        response.setCompanyName(savedProject.getCompanyName());
        response.setEik(savedProject.getEik());
        response.setStartDate(savedProject.getStartDate());
        response.setEndDate(savedProject.getEndDate());
        return response;
    }



    public ResponseByIdProjectDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Long parcelId = project.getParcel() != null ? project.getParcel().getId() : null;

        return new ResponseByIdProjectDTO(
                project.getId(),
                project.getTitle(),
                project.getStartDate(),
                project.getEndDate(),
                project.getEik(),
                project.getBuildingStatus(),
                parcelId);
    }


    public List<ResponseNewProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(project -> new ResponseNewProjectDTO(project.getId(),
                        project.getTitle(),
                        project.getCompanyName(),
                        project.getEik(), project.getStartDate(),
                        project.getEndDate()))
                .collect(Collectors.toList());
    }

    public String getProjectByParcelId(Long id) {
        String project = projectRepository.findByParcelId(id).getTitle();

        return project;
    }

    public ResponseNewProjectDTO updateProject(Long id, UpdateProjectDTO updateProjectDTO) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (updateProjectDTO.getTitle() != null  && !updateProjectDTO.getTitle().isEmpty()) {
        project.setTitle(updateProjectDTO.getTitle());
    }

        if (updateProjectDTO.getEik() != null  && !updateProjectDTO.getEik().isEmpty()) {
        project.setEik(updateProjectDTO.getEik());
        Parcel parcel = project.getParcel();
        parcel.setEik(updateProjectDTO.getEik());
        parcelRepository.saveAndFlush(parcel);
    }

        if (updateProjectDTO.getStartDate() != null) {
        project.setStartDate(updateProjectDTO.getStartDate());
    }

        if (updateProjectDTO.getEndDate() != null) {
        project.setEndDate(updateProjectDTO.getEndDate());
    }

        if (updateProjectDTO.getCompany() != null) {
            project.setCompanyName(updateProjectDTO.getCompany());
        }
       ResponseNewProjectDTO response = new ResponseNewProjectDTO();
        Project savedProject = projectRepository.saveAndFlush(project);
        BeanUtils.copyProperties(savedProject, response);

        return response;
    }
}