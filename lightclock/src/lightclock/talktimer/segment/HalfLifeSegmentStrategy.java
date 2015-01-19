package lightclock.talktimer.segment;

public class HalfLifeSegmentStrategy extends AbstractSegmentStrategy {

    @Override
    public long getFirstSegmentInMilliseconds() {
        return (long)(0.5 * talk.getTimeInMilliseconds());
    }

    @Override
    public long getSecondSegmentInMilliseconds() {
        return (long)(0.25 * talk.getTimeInMilliseconds());
    }

    @Override
    public long getThirdSegmentInMilliseconds() {
        return (long)(0.125 * talk.getTimeInMilliseconds());
    }

    @Override
    public long getFourthSegmentInMilliseconds() {
        return (long)(0.0625 * talk.getTimeInMilliseconds());
    }

    @Override
    public long getFifthSegmentInMilliseconds() {
        return (long)(0.0625 * talk.getTimeInMilliseconds());
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public String toString() {
        return "Half-life (50%, 25%, 12.5%, 6.5%, 6.5%)";
    }

}
