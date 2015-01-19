package blink1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Blink1LightViaUrlApi implements Light {

    private static final Log log = LogFactory.getLog(Blink1LightViaUrlApi.class);

    private int id = 0;

    public Blink1LightViaUrlApi() {
        this(0);
    }

    public Blink1LightViaUrlApi(int id) {
        this.id = id;
        init();
    }

    private void init() {
        // todo
    }

    @Override
    public void setColor(Color color, boolean fade) {
        setColor(color, fade, 1);
    }

    private void setColor(Color color, boolean fade, int attempt) {
        if (attempt > 3) {
            return;
        }

        try {
            setRGB(color, fade);
        } catch (Exception e) {
            log.error("Error detected while setting color");
            setColor(color, fade, ++attempt);
        }
    }

    private void setRGB(Color color, boolean fade) throws Exception {
        StringBuilder s = new StringBuilder();

        s.append("http://localhost:8934/blink1/fadeToRGB?rgb=%23");

        String rgb = Integer.toHexString(color.getRGB());
        rgb = rgb.substring(2, rgb.length());

        s.append(rgb);

        if (fade) {
            s.append("&time=1.0");
        }

        URL url = new URL(s.toString());
        URLConnection conn = url.openConnection();
        try (InputStream in = conn.getInputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = reader.readLine();
            while (line != null) {
                log.debug(line);
                line = reader.readLine();
            }
        }
    }

}
