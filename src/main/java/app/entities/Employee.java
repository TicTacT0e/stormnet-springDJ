package app.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Employees")
public class Employee {

    @Id
    private int id;
    private String name;
    private String phone;
    private String email;
    private String photoUrl;

    public Employee() {
    }

    public Employee(int id, String name, String phone,
                    String email, String photoUrl) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.photoUrl = photoUrl;
    }

    public Employee(Employee employee) {
        this(employee.getId(),
                employee.getName(),
                employee.getPhone(),
                employee.getEmail(),
                employee.getPhotoUrl());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

        @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) object;
        return id == employee.id
                && Objects.equals(name, employee.name)
                && Objects.equals(phone, employee.phone)
                && Objects.equals(email, employee.email)
                && Objects.equals(photoUrl, employee.photoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, email, photoUrl);
    }

    @Override
    public String toString() {
        return "Employee{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", photo='" + phone + '\''
                + ", email='" + email + '\''
                + ", email='" + photoUrl + '\''
                + '\n'
                + '}';
    }
}

