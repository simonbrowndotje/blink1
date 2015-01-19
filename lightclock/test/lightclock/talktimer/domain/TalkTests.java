package lightclock.talktimer.domain;

import lightclock.domain.Talk;
import lightclock.domain.TimePeriod;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TalkTests {

    @Test
    public void testConstruction() {
        Talk talk = new Talk(new TimePeriod(60, 0));

        assertEquals(60, talk.getTimePeriod().getMinutes());
        assertEquals(0, talk.getTimePeriod().getSeconds());
        assertEquals(3600000, talk.getTimeInMilliseconds());
    }

}
