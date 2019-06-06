package app.dao.impl;

import app.entities.User;
import app.entities.Project;

public class UserDaoImpl extends BasicCrudDaoImpl<User> {

    public synchronized void assignToProject(
            User user, Project project) {
        user.assignToProject(project);
    }
}
