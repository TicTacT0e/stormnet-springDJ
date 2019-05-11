package app.utils.time;

import app.utils.TimeUtil;
import org.junit.Assert;
import org.junit.Test;

public class TimeUtilTests {

    @Test(expected = IllegalArgumentException.class)
    public void hoursToMillisecondsIllegal() {
        TimeUtil.hoursToMillisecond(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void millisecondsToHoursIllegal() {
        TimeUtil.millisecondToHours(-1);
    }

    @Test
    public void hoursToMillisecondsNull() {
        Assert.assertEquals(0,
                TimeUtil.hoursToMillisecond(0));
    }

    @Test
    public void MillisecondsToHoursNull() {
        Assert.assertEquals(0,
                TimeUtil.millisecondToHours(0));
    }

    @Test
    public void hoursToMilliseconds() {
        Assert.assertEquals(201600000,
                TimeUtil.hoursToMillisecond(56));
        Assert.assertEquals(1850400000,
                TimeUtil.hoursToMillisecond(514));
    }

    @Test
    public void MillisecondsToHours() {
        Assert.assertEquals(14,
                TimeUtil.millisecondToHours(50000000));
        Assert.assertEquals(8,
                TimeUtil.millisecondToHours(25678965));
    }

}
