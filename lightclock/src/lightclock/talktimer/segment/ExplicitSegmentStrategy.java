package lightclock.talktimer.segment;

import lightclock.domain.TimePeriod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ExplicitSegmentStrategy extends AbstractSegmentStrategy {

    private static final Log log = LogFactory.getLog(ExplicitSegmentStrategy.class);

    private TimePeriod segment1, segment2, segment3, segment4, segment5;

    public void setSegment1(TimePeriod segment1) {
        this.segment1 = segment1;
    }

    public void setSegment2(TimePeriod segment2) {
        this.segment2 = segment2;
    }

    public void setSegment3(TimePeriod segment3) {
        this.segment3 = segment3;
    }

    public void setSegment4(TimePeriod segment4) {
        this.segment4 = segment4;
    }

    public void setSegment5(TimePeriod segment5) {
        this.segment5 = segment5;
    }

    @Override
    public long getFirstSegmentInMilliseconds() {
        return segment1.asMilliseconds();
    }

    @Override
    public long getSecondSegmentInMilliseconds() {
        return segment2.asMilliseconds();
    }

    @Override
    public long getThirdSegmentInMilliseconds() {
        return segment3.asMilliseconds();
    }

    @Override
    public long getFourthSegmentInMilliseconds() {
        return segment4.asMilliseconds();
    }

    @Override
    public long getFifthSegmentInMilliseconds() {
        return segment5.asMilliseconds();
    }

    @Override
    public boolean isValid() {
        long total = getFirstSegmentInMilliseconds() + getSecondSegmentInMilliseconds() + getThirdSegmentInMilliseconds() + getFourthSegmentInMilliseconds() + getFifthSegmentInMilliseconds();

        if (total != talk.getTimeInMilliseconds()) {
            log.error("Total segment time of " + new TimePeriod(total) + " doesn't match talk time of " + talk.getTimePeriod());
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Explicit (" + segment1 + ", " + segment2 + ", " + segment3 + ", " + segment4 + ", " + segment5 + ")";
    }

}
