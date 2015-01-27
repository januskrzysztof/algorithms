package com.algorythms;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.Assert.*;
import static com.algorythms.PowerCalculator.calculate;

/**
 * Created by Janek on 2015-01-27.
 */
public class PowerCalculateTest {
    @Test
    public void testPrivateConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<PowerCalculator> constructor = PowerCalculator.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void testAnythingRaisedToThePowerOfZeroIsOne() {
        assertEquals(1, calculate(0,0));
        assertEquals(1, calculate(1,0));
        assertEquals(1, calculate(27,0));
        assertEquals(1, calculate(143, 0));
    }

    @Test
    public void testAnythingRaisedToThePowerOfOneIsItself() {
        assertEquals(0, calculate(0,1));
        assertEquals(1, calculate(1,1));
        assertEquals(27, calculate(27,1));
        assertEquals(143, calculate(143,1));
    }

    @Test
    public void testArbitrary() {
        assertEquals(0, calculate(0,2));
        assertEquals(1, calculate(1,2));
        assertEquals(4, calculate(2,2));
        assertEquals(8, calculate(2,3));
        assertEquals(27, calculate(3,3));
    }
}
