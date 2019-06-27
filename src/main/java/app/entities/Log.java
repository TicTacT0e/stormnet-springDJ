package app.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;
import java.sql.Timestamp;
import java.util.StringJoiner;

@Entity
@Table(name = "Logs")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer assignmentId;
    private Double time;

    @OrderColumn(name = "rowCount")
    private Integer rowCount;
    private String comment;
    private Timestamp date;

    @Version
    private Long version;

    @ManyToOne(targetEntity = Assignment.class)
    @JoinColumn(name = "assignmentId",
            updatable = false, insertable = false)
    private Assignment assignment;

    public Log() {
    }

    public Log(int id, int assignmentId, double time,
               int rowCount, String comment, long version) {
        this.id = id;
        this.assignmentId = assignmentId;
        this.time = time;
        this.rowCount = rowCount;
        this.comment = comment;
        this.version = version;
    }

    @PrePersist
    protected void onCreate() {
        date = new Timestamp(new java.util.Date().getTime());
    }

    @PreUpdate
    protected void onUpdate() {
        date = new Timestamp(new java.util.Date().getTime());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }


    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Log.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("assignmentId=" + assignmentId)
                .add("time=" + time)
                .add("rowCount=" + rowCount)
                .add("comment='" + comment + "'")
                .add("date=" + date)
                .add("version=" + version)
                .toString();
    }
}
