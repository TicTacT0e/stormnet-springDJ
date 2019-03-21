package app.dao.impl;

import app.dao.ProjectDao;
import app.entities.Project;
import app.exceptions.EntityAlreadyExistsException;
import app.exceptions.EntityNotFoundException;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;


@Repository
public class ProjectDaoImpl implements ProjectDao {

    private static List<Project> projects = new LinkedList<>();

    static {
        DateTime startDate = DateTime.parse("2018-10-05");
        startDate.toDate();

        DateTime endDate = DateTime.parse("2019-03-01");

        projects.add(new Project(0, "Cyber Pineapple",
                "someCyberPineAppleUrl",
                startDate.toDate(),
                endDate.toDate(),
                360));

        endDate = DateTime.parse("2019-05-01");

        projects.add(new Project(1, "Purple Warm&Cold Hammer",
                "somePurpleWarm&ColdHammerUrl",
                startDate.toDate(),
                endDate.toDate(),
                514));

    }

    @Override
    public synchronized List<Project> getAll() {
        return projects;
    }

    @Override
    public synchronized Project findById(int id) {
        for (Project project : projects) {
            if (project.getId() == id) {
                return project;
            }
        }
        throw new EntityNotFoundException();
    }


    @Override
    public synchronized void save(Project project) {
        for (Project savedProject : projects) {
            if (savedProject.getId() == project.getId()) {
                throw new EntityAlreadyExistsException();
            }
        }
        projects.add(project);
    }

    @Override
    public synchronized void delete(Project retiringProject) {
        if (!projects.contains(retiringProject)) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public synchronized void delete(int id) {
        delete(findById(id));
    }

    @Override
    public synchronized void edit(Project project) {
        Project oldProject = findById(project.getId());
        int index = projects.indexOf(oldProject);
        projects.remove(oldProject);
        projects.add(index, project);

    }
}
