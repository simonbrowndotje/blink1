package lightclock.talktimer.segment;

import lightclock.domain.Talk;

public interface SegmentStrategy {

    public void setTalk(Talk talk);

    public long getFirstSegmentInMilliseconds();

    public long getSecondSegmentInMilliseconds();

    public long getThirdSegmentInMilliseconds();

    public long getFourthSegmentInMilliseconds();

    public long getFifthSegmentInMilliseconds();

    public boolean isValid();

}
