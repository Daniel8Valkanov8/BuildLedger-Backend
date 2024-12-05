package com.buildledger.backend.service.micro;

import com.buildledger.backend.repository.CooperationRepository;
import com.buildledger.backend.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectDeleteService {
    private final ProjectRepository projectRepository;
    private final CooperationRepository cooperationRepository;
    public ProjectDeleteService(ProjectRepository projectRepository, CooperationRepository cooperationRepository) {
        this.projectRepository = projectRepository;
        this.cooperationRepository = cooperationRepository;
    }
    public String deleteProject(long projectId) {
        projectRepository.deleteById(projectId);
        return "Project deleted successfully";
    }
}
