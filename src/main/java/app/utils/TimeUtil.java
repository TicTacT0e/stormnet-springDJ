package app.utils;

public class TimeUtil {

    private static final float MINUTES_IN_AN_HOUR = 60;
    private static final float SECONDS_IN_AN_MINUTE = 60;
    private static final float MILLISECONDS_PER_SECONDS = 1000;

    public static long hoursToMillisecond(int hours) {
        if (hours < 0) {
            throw new IllegalArgumentException("Negative hours!");
        }
        return (long) Math.ceil(hours * MINUTES_IN_AN_HOUR * SECONDS_IN_AN_MINUTE
                * MILLISECONDS_PER_SECONDS);
    }

    public static int millisecondToHours(int milliseconds) {
        if (milliseconds < 0) {
            throw new IllegalArgumentException("Negative milliseconds!");
        }
        return (int) Math.ceil(milliseconds / MILLISECONDS_PER_SECONDS / SECONDS_IN_AN_MINUTE
                / MINUTES_IN_AN_HOUR);
    }
}
