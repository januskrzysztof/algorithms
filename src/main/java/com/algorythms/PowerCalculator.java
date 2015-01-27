package com.algorythms;

/**
 * Created by Janek on 2015-01-27.
 */
public class PowerCalculator {
    private PowerCalculator() {}

    public static long calculate(int x, int y) {
        long sum = 1;
        while (y-- > 0) {
            sum*=x;
        }

        return sum;
    }
}
