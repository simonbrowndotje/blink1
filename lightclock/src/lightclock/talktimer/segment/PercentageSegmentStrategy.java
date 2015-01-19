package lightclock.talktimer.segment;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PercentageSegmentStrategy extends AbstractSegmentStrategy {

    private static final Log log = LogFactory.getLog(PercentageSegmentStrategy.class);

    private double segment1Percentage, segment2Percentage, segment3Percentage, segment4Percentage, segment5Percentage;

    public PercentageSegmentStrategy(double segment1Percentage, double segment2Percentage, double segment3Percentage, double segment4Percentage, double segment5Percentage) {
        this.segment1Percentage = segment1Percentage;
        this.segment2Percentage = segment2Percentage;
        this.segment3Percentage = segment3Percentage;
        this.segment4Percentage = segment4Percentage;
        this.segment5Percentage = segment5Percentage;
    }

    @Override
    public long getFirstSegmentInMilliseconds() {
        return calculatePercentage(segment1Percentage);
    }

    @Override
    public long getSecondSegmentInMilliseconds() {
        return calculatePercentage(segment2Percentage);
    }

    @Override
    public long getThirdSegmentInMilliseconds() {
        return calculatePercentage(segment3Percentage);
    }

    @Override
    public long getFourthSegmentInMilliseconds() {
        return calculatePercentage(segment4Percentage);
    }

    @Override
    public long getFifthSegmentInMilliseconds() {
        return calculatePercentage(segment5Percentage);
    }

    private int calculatePercentage(double percentage) {
        return (int)(talk.getTimeInMilliseconds() * (percentage / 100.0));
    }

    @Override
    public boolean isValid() {
        double total = segment1Percentage + segment2Percentage + segment3Percentage + segment4Percentage + segment5Percentage;

        if (total != 100) {
            log.error("Total percentage should be 100, actual value is " + total);
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Percentage (" + segment1Percentage + "%, " + segment2Percentage + "%, " + segment3Percentage + "%, " + segment4Percentage + "%, " + segment5Percentage + "%)";
    }

}