package app.entities;

import app.dto.ProjectDto;

import java.util.List;

public class ProjectPage {
    private ProjectDto projectDto;
    private List<ProjectPage> projectPages;

    public ProjectPage() {
    }

    public ProjectDto getProjectDto() {
        return projectDto;
    }

    public void setProjectDto(ProjectDto projectDto) {
        this.projectDto = projectDto;
    }

    public List<ProjectPage> getProjectPages() {
        return projectPages;
    }

    public void setProjectPages(List<ProjectPage> projectPages) {
        this.projectPages = projectPages;
    }
}

