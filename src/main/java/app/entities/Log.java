package app.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderColumn;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;
import java.sql.Timestamp;
import java.util.StringJoiner;

import static javax.json.bind.JsonbConfig.DATE_FORMAT;

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

    @Version
    private Long date;

    public Log() {
    }

    public Log( int id,int assignmentId, double time, int rowCount, String comment,long date) {
        this.id = id;
        this.assignmentId = assignmentId;
        this.time = time;
        this.rowCount = rowCount;
        this.comment = comment;
        this.date = date;
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

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
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
