package app.entities;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Settings")
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;
    private Integer companyId;
    private String type;
    private String value;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Company.class,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "companyId",
            updatable = false, insertable = false)
    @JsonbTransient
    private Company company;

    public Settings() {
    }

    public Settings(Integer id, Integer companyId, String type, String value) {
        this.id = id;
        this.companyId = companyId;
        this.type = type;
        this.value = value;
    }

    public Settings(Integer companyId, String type, String value) {
        this.companyId = companyId;
        this.type = type;
        this.value = value;
    }

    public Settings(Settings settings) {
        this(
                settings.getId(),
                settings.getCompanyId(),
                settings.getType(),
                settings.getValue()
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Settings)) {
            return false;
        }
        Settings settings = (Settings) object;
        return getId().equals(settings.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
