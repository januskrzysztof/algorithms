package com.algorythms.lists;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Janek on 2015-01-29.
 */
public class ArrayListTest extends AbstractListTest {
    @Override
    protected List<Object> createList() {
        return new ArrayList<>();
    }

    @Test
    public void testRaisedInitialCapacity() {
        List<Object> list = new ArrayList<>(1);

        list.add(A);
        list.add(A);
        list.add(A);

        assertEquals(3, list.size());
        assertEquals(A, list.get(0));
        assertEquals(A, list.get(1));
        assertEquals(A, list.get(2));
    }

    @Test
    public void testDeleteFromLastElementInArray() {
        List<Object> list = new ArrayList<>(1);

        list.add(A);
        list.delete(0);
    }
}
