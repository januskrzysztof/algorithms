package com.algorythms;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Janek on 2015-01-26.
 */
public class ArrayIteratorTest {
    private String[] o = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
    private ArrayIterator sut;

    @Test
    public void testConstructorsWithoutExceptions() {
        sut = new ArrayIterator(o);

        assertEquals("A", sut.current());

        sut = new ArrayIterator(o, 5);
        assertEquals("F", sut.current());

        sut = new ArrayIterator(o, 3);
        assertEquals("D", sut.current());

        sut = new ArrayIterator(o, 3, 1);
        assertEquals("D", sut.current());
        assertFalse(sut.isDone());

        sut = new ArrayIterator(o, 2, 5);
        sut.last();
        assertEquals("G", sut.current());
        assertFalse(sut.isDone());
        sut.first();
        assertEquals("C", sut.current());
    }

    @Test
    public void testConstructorsThrowsExceptions() {
        try {
            sut = new ArrayIterator(o, 10, 2);
            fail();
        } catch (Exception ex) {
            assertEquals(IllegalArgumentException.class, ex.getClass());
            assertEquals("Length value can not be greater then array length", ex.getMessage());
        }

        try {
            sut = new ArrayIterator(o, -2);
            fail();
        } catch (Exception ex) {
            assertEquals(IllegalArgumentException.class, ex.getClass());
            assertEquals("Start index can not be less then 0!", ex.getMessage());
        }

        try {
            sut = new ArrayIterator(o, 0, -1);
            fail();
        } catch (Exception ex) {
            assertEquals(IllegalArgumentException.class, ex.getClass());
            assertEquals("Length value can not be less then 0!", ex.getMessage());
        }
    }

    @Test
    public void testActivity() {
        sut = new ArrayIterator(o);

        assertEquals("A", sut.current());
        assertFalse(sut.isDone());

        sut.first();
        assertEquals("A", sut.current());
        assertFalse(sut.isDone());

        sut.next();
        assertEquals("B", sut.current());
        assertFalse(sut.isDone());

        sut.previous();
        assertEquals("A", sut.current());
        assertFalse(sut.isDone());

        sut.last();
        assertEquals(o[10], sut.current());
        assertFalse(sut.isDone());

        sut.previous();
        assertEquals(o[9], sut.current());
        assertFalse(sut.isDone());

        sut = new ArrayIterator(o, 1, 1);

        assertEquals("B", sut.current());
        assertFalse(sut.isDone());

        sut.last();
        assertEquals("B", sut.current());
        sut.first();
        assertEquals("B", sut.current());

        sut = new ArrayIterator(o, 10);

        assertEquals(o[10], sut.current());
        assertFalse(sut.isDone());
    }

    @Test
    public void testAllItemsInArray() {
        sut = new ArrayIterator(o);

        int i=0;
        sut.first();
        while (!sut.isDone()) {
            assertEquals(o[i++], sut.current());
            sut.next();
        }

        sut.last();
        while (!sut.isDone()) {
            assertEquals(o[--i], sut.current());
            sut.previous();
        }
    }
}
