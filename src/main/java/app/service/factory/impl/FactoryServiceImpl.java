package app.service.factory.impl;

import app.service.*;
import app.service.factory.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactoryServiceImpl implements FactoryService {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LogsService logsService;

    @Autowired
    private ProjectService projectService;

    @Override
    public CompanyService getCompanyService() {
        return companyService;
    }

    @Override
    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    @Override
    public LogsService getLogsService() {
        return logsService;
    }

    @Override
    public ProjectService getProjectService() {
        return projectService;
    }
}