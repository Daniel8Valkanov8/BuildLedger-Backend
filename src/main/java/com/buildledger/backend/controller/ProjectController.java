package com.buildledger.backend.controller;


import com.buildledger.backend.dto.request.building.CreateNewProjectDTO;
import com.buildledger.backend.dto.responce.building.ResponseByIdProjectDTO;
import com.buildledger.backend.dto.responce.building.ResponseNewProjectDTO;
import com.buildledger.backend.service.impl.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseNewProjectDTO> createNewProject(
            @Valid @RequestBody CreateNewProjectDTO createNewProjectDTO) {
        ResponseNewProjectDTO response = projectService.createNewProject(createNewProjectDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseByIdProjectDTO> getProjectById(@PathVariable Long id) {
        ResponseByIdProjectDTO project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    @GetMapping("/parcel/{id}")
    public ResponseEntity<String> getProjectByParcelId(@PathVariable Long id) {
        System.out.println("in controller");
        String projectTitle = projectService.getProjectByParcelId(id);
        return ResponseEntity.ok(projectTitle);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteProjectById(@PathVariable Long id){
        String response = projectService.deleteProjectById(id);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResponseNewProjectDTO>> getAllProjects() {
        List<ResponseNewProjectDTO> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }
}
