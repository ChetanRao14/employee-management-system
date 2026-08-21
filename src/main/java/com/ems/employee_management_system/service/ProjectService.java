package com.ems.employee_management_system.service;

import com.ems.employee_management_system.entity.Project;
import com.ems.employee_management_system.exception.ResourceNotFoundException;
import com.ems.employee_management_system.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
    }

    // Business Logic: Update an existing project
    public Project updateProject(Long id, Project projectDetails) {
        Project existingProject = getProjectById(id);

        existingProject.setTitle(projectDetails.getTitle());

        // Update relationship if provided
        if (projectDetails.getDepartment() != null) {
            existingProject.setDepartment(projectDetails.getDepartment());
        }

        return projectRepository.save(existingProject);
    }

    // Business Logic: Delete a project
    public void deleteProject(Long id) {
        Project existingProject = getProjectById(id);
        projectRepository.delete(existingProject);
    }
}