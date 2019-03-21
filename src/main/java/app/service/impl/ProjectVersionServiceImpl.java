package app.service.impl;

import app.entities.ProjectVersion;
import app.service.ProjectVersionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class ProjectVersionServiceImpl implements ProjectVersionService {

    @Value("${version}")
    String version;

    @Override
    public ProjectVersion getProjectVersion() {
        System.out.println("version = " + version);

        return new ProjectVersion(version);
    }
}
