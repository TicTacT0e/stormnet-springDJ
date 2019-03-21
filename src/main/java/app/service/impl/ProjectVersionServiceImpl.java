package app.service.impl;

import app.entities.ProjectVersion;
import app.service.ProjectVersionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Properties;

@Service
public class ProjectVersionServiceImpl implements ProjectVersionService {
    @Value("${version}")
    String version;
    @Override
    public ProjectVersion getProjectVersion() {
//        String version;
//        Properties properties = new Properties();
//        try {
//            properties.load(this.getClass().getResourceAsStream("/project.properties"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        version = properties.getProperty("version");
//
//        return new ProjectVersion(version);
        return new ProjectVersion(version);
    }
}
