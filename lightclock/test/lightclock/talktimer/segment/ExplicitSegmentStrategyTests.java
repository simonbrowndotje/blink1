package lightclock.talktimer.segment;

import lightclock.domain.TimePeriod;
import lightclock.domain.Talk;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExplicitSegmentStrategyTests {

    @Test
    public void testGetSegments() {
        Talk talk = new Talk(new TimePeriod(60, 0));
        ExplicitSegmentStrategy ss = new ExplicitSegmentStrategy();
        ss.setSegment1(new TimePeriod(30, 0));
        ss.setSegment2(new TimePeriod(10, 0));
        ss.setSegment3(new TimePeriod(10, 0));
        ss.setSegment4(new TimePeriod(7, 30));
        ss.setSegment5(new TimePeriod(2, 30));
        ss.setTalk(talk);

        assertTrue(ss.isValid());

        assertEquals(1800000, ss.getFirstSegmentInMilliseconds());
        assertEquals(600000, ss.getSecondSegmentInMilliseconds());
        assertEquals(600000, ss.getThirdSegmentInMilliseconds());
        assertEquals(450000, ss.getFourthSegmentInMilliseconds());
        assertEquals(150000, ss.getFifthSegmentInMilliseconds());
    }

    @Test
    public void testIsValid_ReturnsFalse_WhenSegmentsDontMatchTalkLength() {
        Talk talk = new Talk(new TimePeriod(60, 0));
        ExplicitSegmentStrategy ss = new ExplicitSegmentStrategy();
        ss.setSegment1(new TimePeriod(30, 0));
        ss.setSegment2(new TimePeriod(10, 0));
        ss.setSegment3(new TimePeriod(10, 0));
        ss.setSegment4(new TimePeriod(7, 30));
        ss.setSegment5(new TimePeriod(7, 30));
        ss.setTalk(talk);

        assertFalse(ss.isValid());
    }

}
