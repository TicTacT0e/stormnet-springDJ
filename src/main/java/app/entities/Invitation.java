package app.entities;

import java.sql.Date;

public class Invitation {
    private int id;
    private int partnerId;
    private String invitationsCode;
    private Date dateEnd;
    private String status;

    public Invitation() {
    }

    public Invitation(int id, int partnerId, String invitationsCode,
                      Date dateEnd, String status) {
        this.id = id;
        this.partnerId = partnerId;
        this.invitationsCode = invitationsCode;
        this.dateEnd = dateEnd;
        this.status = status;
    }

    public Invitation(Invitation invitation) {
        this(
                invitation.getId(),
                invitation.getPartnerId(),
                invitation.getInvitationsCode(),
                invitation.getDateEnd(),
                invitation.getStatus()
        );
    }

    public int getId() {
        return id;
    }

    public int getPartnerId() {
        return partnerId;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
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
    public String toString() {
        return "Invitation{" +
                "id=" + id +
                ", partnerId=" + partnerId +
                ", invitationsCode='" + invitationsCode + '\'' +
                ", dateEnd=" + dateEnd +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Invitation that = (Invitation) o;

        if (id != that.id) {
            return false;
        }
        if (partnerId != that.partnerId) {
            return false;
        }
        if (invitationsCode != null ? !invitationsCode
                .equals(that.invitationsCode) : that.invitationsCode != null) {
            return false;
        }
        if (dateEnd != null ? !dateEnd
                .equals(that.dateEnd) : that.dateEnd != null) {
            return false;
        }
        return status != null ? status
                .equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + partnerId;
        result = 31 * result + (invitationsCode != null
                ? invitationsCode.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}