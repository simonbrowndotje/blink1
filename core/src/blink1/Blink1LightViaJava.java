package blink1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import thingm.blink1.Blink1;

import java.awt.*;
import java.io.Closeable;

public class Blink1LightViaJava implements Light, Closeable {

    private static final Log log = LogFactory.getLog(Blink1LightViaJava.class);

    private static int FADE_TIME = 1100;

    private int id = 0;
    private Blink1 blink1;

    public Blink1LightViaJava() {
        this(0);
    }

    public Blink1LightViaJava(int id) {
        this.id = id;
        init();
    }

    private void init() {
        Blink1.enumerate();
        int count = Blink1.getCount();

        if (count > 0) {
            log.debug("Found " + count + " devices");
            log.debug("Device paths:");

            String paths[] = Blink1.getDevicePaths();
            String serials[] = Blink1.getDeviceSerials();
            for (int i = 0; i < paths.length; i++) {
                log.debug(i + ": " + serials[i] + " : " + paths[i]);
            }

            blink1 = Blink1.openById(id);
            int version = blink1.getFirmwareVersion();
            log.debug("Firmware version: " + version);
        } else {
            log.fatal("Couldn't find any blink(1) devices");
        }
    }

    @Override
    public void setColor(Color color, boolean fade) {
        setColor(color, fade, 1);
    }

    private void setColor(Color color, boolean fade, int attempt) {
        if (attempt > 3) {
            return;
        }

        int rc;
        setColor(color, fade, ++attempt);

        if (fade) {
            rc = blink1.fadeToRGB(FADE_TIME, color);
        } else {
            rc = blink1.setRGB(color);
        }
        if (rc == -1) {
            log.error("Error detected while setting color");
            setColor(color, fade, ++attempt);
        }
    }

    public void close() {
        try {
            blink1.close();
        } catch (Exception e) {
            log.error(e);
        }
    }

}
