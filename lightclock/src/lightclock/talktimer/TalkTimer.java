package lightclock.talktimer;

import blink1.Light;
import lightclock.LightClock;
import lightclock.domain.TimePeriod;
import lightclock.domain.Talk;
import lightclock.talktimer.segment.SegmentStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TalkTimer extends LightClock {

    private static Log log = LogFactory.getLog(TalkTimer.class);

    private static final Color GREEN = Color.GREEN;
    private static final Color YELLOW = new Color(255, 210, 0); // this is a better yellow on my blink(1) device
    private static final Color ORANGE = new Color(255, 153, 0); // this is a better orange on my blink(1) device
    private static final Color RED = Color.RED;
    private static final Color DARK_RED = new Color(100, 0, 0);
    private static final Color BLACK = Color.BLACK;

    private static final int SLOW_FLASH = 1000;
    private static final int FAST_FLASH = 100;

    private Talk talk;
    private SegmentStrategy segmentStrategy;
    private TimePeriod warningPeriod = new TimePeriod(1, 0);

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    public TalkTimer(Talk talk, SegmentStrategy segmentStrategy, Light light, TimePeriod warningPeriod) {
        super(light);

        this.talk = talk;
        this.segmentStrategy = segmentStrategy;
        segmentStrategy.setTalk(talk);
        this.warningPeriod = warningPeriod;

        calculateTimes();
    }

    private void calculateTimes() {
        long totalTimeInMilliseconds = talk.getTimeInMilliseconds();

        log.info("Talk time: " + new TimePeriod(totalTimeInMilliseconds));
        log.info("Segment strategy: " + segmentStrategy.toString());

        log.info("1st segment time: " + new TimePeriod(segmentStrategy.getFirstSegmentInMilliseconds()));
        log.info("2nd segment time: " + new TimePeriod(segmentStrategy.getSecondSegmentInMilliseconds()));
        log.info("3rd segment time: " + new TimePeriod(segmentStrategy.getThirdSegmentInMilliseconds()));
        log.info("4th segment time: " + new TimePeriod(segmentStrategy.getFourthSegmentInMilliseconds()));
        log.info("5th segment time: " + new TimePeriod(segmentStrategy.getFifthSegmentInMilliseconds()));
        log.info("Warning time: " + warningPeriod);

        if (!segmentStrategy.isValid()) {
            System.exit(-1);
        }
    }

    @Override
    public void start() {
        setColorNow(GREEN, true);

        long time = segmentStrategy.getFirstSegmentInMilliseconds();
        setColorInFuture(YELLOW, true, time);

        time += segmentStrategy.getSecondSegmentInMilliseconds();
        setColorInFuture(ORANGE, true, time);

        time += segmentStrategy.getThirdSegmentInMilliseconds();
        setColorInFuture(RED, true, time);

        time += segmentStrategy.getFourthSegmentInMilliseconds();
        flashColorInFuture(RED, DARK_RED, true, time, segmentStrategy.getFifthSegmentInMilliseconds(), SLOW_FLASH);

        time += segmentStrategy.getFifthSegmentInMilliseconds();
        flashColorInFuture(RED, BLACK, false, time, warningPeriod.asMilliseconds(), FAST_FLASH);

        stop(time + warningPeriod.asMilliseconds());
    }

}