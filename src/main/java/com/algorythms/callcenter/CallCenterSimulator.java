package com.algorythms.callcenter;

/**
 * Created by Krzysztof Januś on 2015-02-01.
 */
public class CallCenterSimulator {
    public static void main(String[] args) {
        CallCenter callCenter = new CallCenter(10);
        CallGenerator callGenerator = new CallGenerator(10, callCenter);

        long startTime = System.currentTimeMillis();

        callCenter.open();
        try {
            callGenerator.generateCalls();
        } finally {
            callCenter.close();
            System.out.println("All: "+(System.currentTimeMillis()-startTime));
        }
    }
}
