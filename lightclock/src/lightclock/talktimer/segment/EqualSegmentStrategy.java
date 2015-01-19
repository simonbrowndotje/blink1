package lightclock.talktimer.segment;

public class EqualSegmentStrategy extends AbstractSegmentStrategy {

    @Override
    public long getFirstSegmentInMilliseconds() {
        return talk.getTimeInMilliseconds() / NUMBER_OF_SEGMENTS;
    }

    @Override
    public long getSecondSegmentInMilliseconds() {
        return getFirstSegmentInMilliseconds();
    }

    @Override
    public long getThirdSegmentInMilliseconds() {
        return getFirstSegmentInMilliseconds();
    }

    @Override
    public long getFourthSegmentInMilliseconds() {
        return getFirstSegmentInMilliseconds();
    }

    @Override
    public long getFifthSegmentInMilliseconds() {
        return getFirstSegmentInMilliseconds();
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public String toString() {
        return "Equal (5 segments of equal length)";
    }
}