package lightclock;

import blink1.Light;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public abstract class LightClock {

    private static Log log = LogFactory.getLog(LightClock.class);

    private static final Color BLACK = Color.BLACK;

    protected Light light;

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    public LightClock(Light light) {
        this.light = light;
    }

    public abstract void start();

    public void stop() {
        setColorNow(BLACK, true);
    }

    public void stop(long time) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.exit(0);
            }
        };

        ScheduledFuture<?> future = scheduledExecutorService.schedule(runnable, time, TimeUnit.MILLISECONDS);
    }

    protected void setColorNow(Color color, boolean fade) {
        light.setColor(color, fade);
    }

    protected void setColorInFuture(final Color color, final boolean fade, long time) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                light.setColor(color, fade);
            }
        };

        ScheduledFuture<?> future = scheduledExecutorService.schedule(runnable, time, TimeUnit.MILLISECONDS);
    }

    protected void flashColorInFuture(final Color color1, final Color color2, final boolean fade, final long time, final long duration, final long flashTime) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int iterations = (int) (duration / (flashTime * 2));
                for (int i = 0; i < iterations; i++) {
                    light.setColor(color1, fade);
                    sleepForMilliseconds(flashTime);
                    light.setColor(color2, fade);
                    sleepForMilliseconds(flashTime);
                }
            }
        };

        ScheduledFuture<?> future = scheduledExecutorService.schedule(runnable, time, TimeUnit.MILLISECONDS);
    }

    private void sleepForMilliseconds(long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            log.info(e.getMessage());
        }
    }

}