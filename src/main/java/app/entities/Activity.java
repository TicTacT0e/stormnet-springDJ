package app.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Activity")
public class Activity {

    @Id
    private int id;
    private String name;

    public Activity() {
    }

    public Activity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Activity(Activity activity) {
        this(
                activity.getId(),
                activity.getName()
        );
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
}
