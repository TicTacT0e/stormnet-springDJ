/*package app.resources;

import javax.ws.rs.WebApplicationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RestTimestampParam {
    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private java.sql.Timestamp timestamp;

    public RestTimestampParam(String timestampStr) throws WebApplicationException {

        try {
            timestamp = new java.sql.Timestamp(df.parse(timestampStr).getTime());
        } catch (final ParseException e) {
            throw new WebApplicationException(e);
        }
    }

    public java.sql.Timestamp getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        if (timestamp != null) {
            return timestamp.toString();
        } else {
            return "";
        }
    }
}*/
