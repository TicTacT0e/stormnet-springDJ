package app.service.impl;

import app.entities.Project;
import app.entities.ProjectVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class Version {
    @Autowired
    private Environment environment;

    public ProjectVersion getVersion(String version){
        System.out.println(version);
        System.out.println(environment.getProperty("version"));
        return new ProjectVersion(version);
    }
}
