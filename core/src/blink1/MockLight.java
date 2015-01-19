package blink1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.awt.*;

public class MockLight implements Light {

    private static final Log log = LogFactory.getLog(MockLight.class);

    @Override
    public void setColor(Color color, boolean fade) {
        log.info("Color is now " + color.toString());
    }

}
