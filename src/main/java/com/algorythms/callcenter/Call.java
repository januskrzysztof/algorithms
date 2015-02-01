package com.algorythms.callcenter;

/**
 * Created by Krzysztof Januś on 2015-02-01.
 */
public class Call {
    private final long id;
    private final long startTime;
    private final int duration;

    public Call(int id, int duration) {
        assert duration >= 0 : "Duration time cannot be less them 0";
        this.id        = id;
        this.duration  = duration;
        this.startTime = System.currentTimeMillis();
    }

    public void answer() {
        System.out.println(this+" answer in "+(System.currentTimeMillis()-startTime)+" millis.");
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ignored) {}
    }

    @Override
    public String toString() {
        return "Call id: "+id;
    }
}
