package lightclock.domain;

import lightclock.domain.TimePeriod;

public class Talk {

    private TimePeriod timePeriod;

    public Talk(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public long getTimeInMilliseconds() {
        return timePeriod.asMilliseconds();
    }

    public TimePeriod getTimePeriod() {
        return this.timePeriod;
    }

}