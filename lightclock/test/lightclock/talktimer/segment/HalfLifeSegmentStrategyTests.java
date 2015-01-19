package lightclock.talktimer.segment;

import lightclock.domain.TimePeriod;
import lightclock.domain.Talk;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HalfLifeSegmentStrategyTests {

    @Test
    public void testGetSegments() {
        Talk talk = new Talk(new TimePeriod(60, 0));
        HalfLifeSegmentStrategy ss = new HalfLifeSegmentStrategy();
        ss.setTalk(talk);

        assertTrue(ss.isValid());

        assertEquals(1800000, ss.getFirstSegmentInMilliseconds());
        assertEquals(900000, ss.getSecondSegmentInMilliseconds());
        assertEquals(450000, ss.getThirdSegmentInMilliseconds());
        assertEquals(225000, ss.getFourthSegmentInMilliseconds());
        assertEquals(225000, ss.getFifthSegmentInMilliseconds());
    }

}
