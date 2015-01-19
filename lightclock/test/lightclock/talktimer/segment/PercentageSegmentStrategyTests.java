package lightclock.talktimer.segment;

import lightclock.domain.TimePeriod;
import lightclock.domain.Talk;
import org.junit.Test;

import static org.junit.Assert.*;

public class PercentageSegmentStrategyTests {

    @Test
    public void testGetSegments() {
        Talk talk = new Talk(new TimePeriod(60, 0));
        PercentageSegmentStrategy ss = new PercentageSegmentStrategy(60.0, 20.0, 10.0, 7.5, 2.5);
        ss.setTalk(talk);

        assertTrue(ss.isValid());

        assertEquals(2160000, ss.getFirstSegmentInMilliseconds());
        assertEquals(720000, ss.getSecondSegmentInMilliseconds());
        assertEquals(360000, ss.getThirdSegmentInMilliseconds());
        assertEquals(270000, ss.getFourthSegmentInMilliseconds());
        assertEquals(90000, ss.getFifthSegmentInMilliseconds());
    }

    @Test
    public void testIsValid_ReturnsFalse_WhenSegmentsDontAddUpTo100Percent() {
        Talk talk = new Talk(new TimePeriod(60, 0));
        PercentageSegmentStrategy ss = new PercentageSegmentStrategy(60.0, 20.0, 10.0, 7.5, 10.0);
        ss.setTalk(talk);

        assertFalse(ss.isValid());
    }

}
