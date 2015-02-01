package com.algorythms.callcenter;

/**
 * Created by Krzysztof Januś on 2015-02-01.
 */
public class CallGenerator {
    private final int maxCalls;
    private final CallCenter callCenter;
    private static final int MAX_DURATION = 1000;

    public CallGenerator(int maxCalls, CallCenter callCenter) {
        this.maxCalls   = maxCalls;
        this.callCenter = callCenter;
    }

    public void generateCalls() {
        for (int i=1; i<=maxCalls; i++) {
            try {
                Thread.sleep((int) (Math.random()*MAX_DURATION));
            } catch (InterruptedException ignored) { }

            callCenter.accept(new Call(i, (int) (Math.random()*MAX_DURATION)));
        }
    }
}
