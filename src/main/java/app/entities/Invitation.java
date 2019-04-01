package app.entities;

import java.util.Date;

public class Invitation {
    private int companyId;
    private int employeeId;
    private String email;
    private String invitationsCode;
    private Date dateEnd;
    private String status;

    public Invitation(int employeeId, int companyId, String email, String invitationsCode, Date dateEnd, String status) {
        this.companyId = companyId;
        this.employeeId = employeeId;
        this.email = email;
        this.invitationsCode = invitationsCode;
        this.dateEnd = dateEnd;
        this.status = status;
    }

    public Invitation(){
    }

    public Invitation(Invitation invitation){
        this (
                invitation.getCompanyId(), invitation.getEmployeeId(), invitation.getEmail(),
                invitation.getInvitationsCode(), invitation.getDateEnd(), invitation.getStatus()
        );
    }

    public int getCompanyId() {
        return companyId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmail() {
        return email;
    }

    public String getInvitationsCode() {
        return invitationsCode;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setInvitationsCode(String invitationsCode) {
        this.invitationsCode = invitationsCode;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invitation that = (Invitation) o;

        if (companyId != that.companyId) return false;
        if (employeeId != that.employeeId) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (invitationsCode != null ? !invitationsCode.equals(that.invitationsCode) : that.invitationsCode != null)
            return false;
        if (dateEnd != null ? !dateEnd.equals(that.dateEnd) : that.dateEnd != null) return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = companyId;
        result = 31 * result + employeeId;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (invitationsCode != null ? invitationsCode.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Invitation{" +
                "companyId=" + companyId +
                ", employeeId=" + employeeId +
                ", email='" + email + '\'' +
                ", invitationsCode='" + invitationsCode + '\'' +
                ", dateEnd=" + dateEnd +
                ", status='" + status + '\'' +
                '}';
    }
}