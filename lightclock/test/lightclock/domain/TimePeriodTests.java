package lightclock.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimePeriodTests {

    @Test
    public void testConstruction() {
        assertEquals(60, new TimePeriod(60, 0).getMinutes());
        assertEquals(30, new TimePeriod(0, 30).getSeconds());
        assertEquals(60, new TimePeriod("60:00").getMinutes());
        assertEquals(30, new TimePeriod("00:30").getSeconds());
    }

    @Test
    public void testConstruction_SetMinutesToZero_WhenMinutesAreNegative() {
        assertEquals(0, new TimePeriod(-1, 0).getMinutes());
    }

    @Test
    public void testConstruction_SetSecondsToZero_WhenSecondsAreNegative() {
        assertEquals(0, new TimePeriod(60, -1).getSeconds());
    }

    @Test
    public void testConstruction_SetSecondsTo59_WhenSecondsAreMoreThan59() {
        assertEquals(59, new TimePeriod(60, 60).getSeconds());
    }

    @Test
    public void testAsMillisecondsFromMinutesAndSeconds() {
        assertEquals(1000, new TimePeriod(0, 1).asMilliseconds());
        assertEquals(30000, new TimePeriod(0, 30).asMilliseconds());
        assertEquals(60000, new TimePeriod(1, 0).asMilliseconds());
        assertEquals(120000, new TimePeriod(2, 0).asMilliseconds());
        assertEquals(150000, new TimePeriod(2, 30).asMilliseconds());
    }

    @Test
    public void testAsMinutesAndSecondsFromMilliseconds() {
        assertEquals("00:01", new TimePeriod(1000).toString());
        assertEquals("00:30", new TimePeriod(30000).toString());
        assertEquals("01:00", new TimePeriod(60000).toString());
        assertEquals("02:00", new TimePeriod(120000).toString());
        assertEquals("02:30", new TimePeriod(150000).toString());
    }

    @Test
    public void testToString() {
        assertEquals("00:01", new TimePeriod(1000).toString());
        assertEquals("00:30", new TimePeriod(30000).toString());
        assertEquals("01:00", new TimePeriod(60000).toString());
        assertEquals("02:00", new TimePeriod(120000).toString());
        assertEquals("02:30", new TimePeriod(150000).toString());
    }

}
