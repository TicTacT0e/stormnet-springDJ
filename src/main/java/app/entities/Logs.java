package app.entities;

import java.util.Date;
import java.util.Objects;

public class Logs {

    private int id;
    private int assignmentId;
    private int order;
    private double time;
    private String comment;
    private Date date;

    public Logs (){
    }

    public Logs(int id, int assignmentId, int order, double time,
                String comment, Date date) {
        this.id = id;
        this.assignmentId = assignmentId;
        this.time = time;
        this.order = order;
        this.comment = comment;
        this.date = date;
    }

    public Logs(int assignmentId,int order, double time,  String comment, Date date) {
        this.assignmentId = assignmentId;
        this.time = time;
        this.order = order;
        this.comment = comment;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        Logs logs = (Logs) object;
        return id == logs.id &&
                assignmentId == logs.assignmentId &&
                Double.compare(logs.time, time) == 0 &&
                order == logs.order &&
                Objects.equals(comment, logs.comment) &&
                Objects.equals(date, logs.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, assignmentId, time, order, comment, date);
    }

    @Override
    public String toString() {
        return "Log{"
                + "assignmentId: " + assignmentId +';'
                + "order: " + order + ';'
                + " time: " + time + ';'
                + "comment: '" + comment + '\''
                + " Date: " + date + ';'
                + '}';
    }
}
