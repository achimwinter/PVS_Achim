package utils;

import java.util.concurrent.TimeUnit;

public class TimeWatch {
    private long starts;

    public static TimeWatch start() {
        return new TimeWatch();
    }

    private TimeWatch() {
        this.reset();
    }

    public TimeWatch reset() {
        this.starts = System.nanoTime();
        return this;
    }

    public long time() {
        long ends = System.nanoTime();
        return ends - this.starts;
    }

    public long time(TimeUnit unit) {
        return unit.convert(this.time(), TimeUnit.NANOSECONDS);
    }
}
