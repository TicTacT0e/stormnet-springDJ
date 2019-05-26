package app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OrderColumn;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.StringJoiner;

@Entity
@Table(name = "Logs")
public class Log {

    @Id
    @Column(name = "id")
    private Long id;

    private Integer assignmentId;

    private Double time;

    @OrderColumn(name = "rowCount")
    private Integer rowCount;

    private String comment;

    private Timestamp date;

    public Log() {
    }

    public Log(long id, int assignmentId, double time, int rowCount, String comment, Timestamp date) {
        this.id = id;
        this.assignmentId = assignmentId;
        this.time = time;
        this.rowCount = rowCount;
        this.comment = comment;
        this.date = date;
    }

    @PrePersist
    protected void onCreate() {
        date = new Timestamp(new java.util.Date().getTime());
    }

    @PreUpdate
    protected void onUpdate() {
        date = new Timestamp(new java.util.Date().getTime());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", Log.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("assignmentId=" + assignmentId)
                .add("time=" + time)
                .add("rowCount=" + rowCount)
                .add("comment='" + comment + "'")
                .add("date=" + date)
                .toString();
    }
}
