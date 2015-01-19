package lightclock.talktimer.segment;

import lightclock.domain.TimePeriod;
import lightclock.domain.Talk;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EqualSegmentStrategyTests {

    @Test
    public void testGetSegments() {
        Talk talk = new Talk(new TimePeriod(60, 0));
        EqualSegmentStrategy ss = new EqualSegmentStrategy();
        ss.setTalk(talk);

        assertEquals(720000, ss.getFirstSegmentInMilliseconds());
        assertEquals(720000, ss.getSecondSegmentInMilliseconds());
        assertEquals(720000, ss.getThirdSegmentInMilliseconds());
        assertEquals(720000, ss.getFourthSegmentInMilliseconds());
        assertEquals(720000, ss.getFifthSegmentInMilliseconds());
    }

}
