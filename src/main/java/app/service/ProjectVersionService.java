package app.service;

import app.entities.ProjectVersion;
import org.springframework.stereotype.Service;

@Service
public interface ProjectVersionService {
    ProjectVersion getProjectVersion();
}