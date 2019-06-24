package app.dto;

import java.sql.Timestamp;

public class TimesheetPendingApprovalDto {

    private Timestamp from;
    private Timestamp to;
    private Double planned;
    private Double actual;

    public TimesheetPendingApprovalDto() {
    }

    public Timestamp getFrom() {
        return from;
    }

    public void setFrom(Timestamp from) {
        this.from = from;
    }

    public Timestamp getTo() {
        return to;
    }

    public void setTo(Timestamp to) {
        this.to = to;
    }

    public Double getPlanned() {
        return planned;
    }

    public void setPlanned(Double planned) {
        this.planned = planned;
    }

    public Double getActual() {
        return actual;
    }

    public void setActual(Double actual) {
        this.actual = actual;
    }
}
