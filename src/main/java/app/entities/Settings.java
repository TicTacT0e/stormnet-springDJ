package app.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Setting")
public class Settings {

    @Id
    private int id;
    private int companyId;
    private String type;
    private String value;

    public Settings() {
    }

    public Settings(int id, int companyId, String type, String value) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
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
}
