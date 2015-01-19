package lightclock.pomodoro;

import blink1.Light;
import lightclock.LightClock;
import lightclock.domain.TimePeriod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.awt.*;

public class Pomodoro extends LightClock {

    private static Log log = LogFactory.getLog(Pomodoro.class);

    private static final Color GREEN = Color.GREEN;
    private static final Color YELLOW = new Color(255, 210, 0); // this is a better yellow on my blink(1) device
    private static final Color RED = Color.RED;

    private TimePeriod length, shortBreak, longBreak;

    public Pomodoro(Light light, TimePeriod length, TimePeriod shortBreak, TimePeriod longBreak) {
        super(light);

        this.length = length;
        this.shortBreak = shortBreak;
        this.longBreak = longBreak;

        log.info("Pomodoro length: " + length.toString());
        log.info("Short break: " + shortBreak.toString());
        log.info("Long break: " + longBreak.toString());
    }

    @Override
    public void start() {
        long time = 0;
        for (int i = 1; i <= 4; i++) {
            setColorInFuture(RED, true, time);
            time += length.asMilliseconds();

            if (i < 4) {
                setColorInFuture(YELLOW, true, time);
                time += shortBreak.asMilliseconds();
            } else {
                setColorInFuture(GREEN, true, time);
                time += longBreak.asMilliseconds();
            }
        }

        stop(time);
    }

}