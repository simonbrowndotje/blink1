package lightclock.talktimer.segment;

import lightclock.domain.Talk;

public abstract class AbstractSegmentStrategy implements SegmentStrategy {

    protected final static int NUMBER_OF_SEGMENTS = 5;

    protected Talk talk;

    @Override
    public void setTalk(Talk talkType) {
        this.talk = talkType;
    }
}
