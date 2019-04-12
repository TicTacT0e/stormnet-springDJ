package app.entities;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


public class Company {

    private int id;
    private String name;
    private String logoUrl;
    private int ownerId;

    private List<Employee> employees = new LinkedList<>();
    private List<Project> projects = new LinkedList<>();

    public Company() {
    }

    public Company(int id, String name, String logoUrl, int ownerId) {
        this.id = id;
        this.name = name;
        this.logoUrl = logoUrl;
        this.ownerId = ownerId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public Company(Company company) {
        this(company.getId(),
                company.getName(),
                company.getLogoUrl(),
                company.ownerId);
        this.employees = company.getEmployees();
        this.projects = company.getProjects();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Company)) {
            return false;
        }
        Company company = (Company) object;
        return id == company.id
                && Objects.equals(name, company.name)
                && Objects.equals(logoUrl, company.logoUrl)
                && Objects.equals(employees, company.employees)
                && Objects.equals(projects, company.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, logoUrl, employees, projects);
    }

    @Override
    public String toString() {
        return "Company{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", logoUrl='" + logoUrl + '\''
                + '\n'
                + ", employees=" + employees
                + '\n'
                + ", projects=" + projects
                + '}';
    }
}
