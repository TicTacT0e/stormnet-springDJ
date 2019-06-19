package app.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Users")
public class User {

    @Id
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String photoUrl;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Employee.class,
            mappedBy = "user")
    private Employee employee;

    public User() {
    }

    public User(int id, String name, String phone,
                String email, String photoUrl) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.photoUrl = photoUrl;
    }

    public User(User user) {
        this(user.getId(),
                user.getName(),
                user.getPhone(),
                user.getEmail(),
                user.getPhotoUrl());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof User)) {
            return false;
        }
        User employee = (User) object;
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
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", photo='" + phone + '\''
                + ", email='" + email + '\''
                + ", email='" + photoUrl + '\''
                + "}";
    }
}

