package lightclock.domain;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TimePeriod {

    private int minutes;
    private int seconds;

    public TimePeriod(long milliseconds) {
        this.minutes = (int)(milliseconds / (1000 * 60));
        this.seconds = (int)((milliseconds / 1000) % 60);
    }

    public TimePeriod(int minutes, int seconds) {
        init(minutes, seconds);
    }

    public TimePeriod(String minutesAndSeconds) {
        String[] values = minutesAndSeconds.split(":");
        init(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
    }

    private void init(int minutes, int seconds) {
        if (minutes < 0) {
            this.minutes = 0;
        } else {
            this.minutes = minutes;
        }

        if (seconds < 0) {
            this.seconds = 0;
        } else if (seconds > 59) {
            this.seconds = 59;
        } else {
            this.seconds = seconds;
        }
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public long asMilliseconds() {
        return (minutes * 60 * 1000) + (seconds * 1000);
    }

    @Override
    public String toString() {
        NumberFormat twoDigits = new DecimalFormat("00");
        return twoDigits.format(minutes) + ":" + twoDigits.format(seconds);
    }

}
