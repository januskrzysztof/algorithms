package com.algorythms.lists;

import com.algorythms.iterators.Iterator;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Janek on 2015-01-28.
 */
public abstract class AbstractListTest {
    protected static final Object A = "A";
    protected static final Object B = "B";
    protected static final Object C = "C";

    protected abstract List<Object> createList();

    @Test
    public void testInsertIntoEmptyList() {
        List<Object> list = createList();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.insert(0, A);
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals(A, list.get(0));
    }

    @Test
    public void testInsertBetweenElements() {
        List<Object> list = createList();

        list.insert(0, A);
        list.insert(1, B);
        list.insert(1, C);

        assertEquals(3, list.size());
        assertEquals(A, list.get(0));
        assertEquals(C, list.get(1));
        assertEquals(B, list.get(2));
    }

    @Test
    public void testInsertBeforeFirstElement() {
        List<Object> list = createList();

        list.insert(0, A);
        list.insert(0, B);

        assertEquals(2, list.size());
        assertEquals(B, list.get(0));
        assertEquals(A, list.get(1));
    }

    @Test
    public void testInsertAfterLastElement() {
        List<Object> list = createList();

        list.insert(0, A);
        list.insert(1, B);

        assertEquals(2, list.size());
        assertEquals(A, list.get(0));
        assertEquals(B, list.get(1));
    }

    @Test
    public void testInsertOutOfBounds() {
        List<Object> list = createList();

        try {
            list.insert(-1, A);
            fail();
        } catch (Exception ex) {
            assertEquals(IndexOutOfBoundsException.class, ex.getClass());
        }

        try {
            list.insert(1, B);
            fail();
        } catch (Exception ex) {
            assertEquals(IndexOutOfBoundsException.class, ex.getClass());
        }
    }

    @Test
    public void testAdd() {
        List<Object> list = createList();

        list.add(A);
        list.add(B);
        list.add(C);

        assertEquals(3, list.size());
        assertEquals(A, list.get(0));
        assertEquals(B, list.get(1));
        assertEquals(C, list.get(2));
    }

    @Test
    public void testSet() {
        List<Object> list = createList();

        list.insert(0, A);
        assertSame(A, list.get(0));

        assertEquals(A, list.set(0, B));
        assertEquals(B, list.get(0));
    }

    @Test
    public void testGetOutOfBounds() {
        List<Object> list = createList();

        try {
            list.get(-1);
            fail();
        } catch (Exception ex) {
            assertEquals(IndexOutOfBoundsException.class, ex.getClass());
        }

        try {
            list.get(0);
            fail();
        } catch (Exception ex) {
            assertEquals(IndexOutOfBoundsException.class, ex.getClass());
        }

        list.add(A);

        try {
            list.get(1);
            fail();
        } catch (Exception ex) {
            assertEquals(IndexOutOfBoundsException.class, ex.getClass());
        }
    }

    @Test
    public void testSetOutOfBounds() {
        List<Object> list = createList();

        try {
            list.set(-1, A);
            fail();
        } catch (Exception ex) {
            assertEquals(IndexOutOfBoundsException.class, ex.getClass());
        }

        try {
            list.set(0, B);
            fail();
        } catch (Exception ex) {
            assertEquals(IndexOutOfBoundsException.class, ex.getClass());
        }

        list.insert(0, C);
        try {
            list.set(1, C);
            fail();
        } catch (Exception ex) {
            assertEquals(IndexOutOfBoundsException.class, ex.getClass());
        }
    }

    @Test
    public void testDeleteOnlyElement() {
        List<Object>  list = createList();

        list.add(A);
        assertEquals(1, list.size());
        assertEquals(A, list.delete(0));
        assertEquals(0, list.size());
    }

    @Test
    public void testDeleteFirstElement() {
        List<Object> list = createList();

        list.add(A);
        list.add(B);
        list.add(C);

        assertEquals(A, list.delete(0));

        assertEquals(2, list.size());
        assertEquals(B, list.get(0));
        assertEquals(C, list.get(1));
    }

    @Test
    public void testDeleteLastElement() {
        List<Object> list = createList();

        list.add(A);
        list.add(B);
        list.add(C);

        assertEquals(3, list.size());

        assertEquals(C, list.delete(2));
        assertEquals(2, list.size());
        assertEquals(A, list.get(0));
        assertEquals(B, list.get(1));
    }

    @Test
    public void testDeleteMiddleElement() {
        List<Object> list = createList();

        list.add(A);
        list.add(C);
        list.add(B);

        assertEquals(3, list.size());

        assertEquals(C, list.delete(1));
        assertEquals(2, list.size());
        assertEquals(A, list.get(0));
        assertEquals(B, list.get(1));
    }

    @Test
    public void testDeleteOutOfBound() {
        List<Object> list = createList();

        try {
            list.delete(-1);
            fail();
        } catch (Exception ex) {
            assertEquals(IndexOutOfBoundsException.class, ex.getClass());
        }

        try {
            list.delete(0);
            fail();
        } catch (Exception ex) {
            assertEquals(IndexOutOfBoundsException.class, ex.getClass());
        }
    }

    @Test
    public void testDeleteByValue() {
        List<Object> list = createList();

        list.add(A);
        list.add(B);
        list.add(A);

        assertEquals(3, list.size());
        assertTrue(list.delete(A));

        assertEquals(2, list.size());
        assertEquals(B, list.get(0));
        assertEquals(A, list.get(1));

        assertTrue(list.delete(A));

        assertEquals(1, list.size());
        assertEquals(B, list.get(0));

        assertTrue(list.delete(B));
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    public void testEmptyIterator() {
        List<Object> list = createList();

        Iterator iterator = list.iterator();
        assertTrue(iterator.isDone());

        try {
            iterator.current();
            fail();
        } catch (Exception ex) {
            assertEquals(IndexOutOfBoundsException.class, ex.getClass());
        }
    }

    @Test
    public void testForwardIteration() {
        List<Object> list = createList();

        list.add(A);
        list.add(B);
        list.add(C);

        Iterator iterator = list.iterator();

        iterator.first();
        assertFalse(iterator.isDone());
        assertEquals(A, iterator.current());

        iterator.next();
        assertEquals(B, iterator.current());

        iterator.next();
        iterator.next();
        assertTrue(iterator.isDone());
        try {
            iterator.current();
            fail();
        } catch (Exception ex) {
            assertEquals(IndexOutOfBoundsException.class, ex.getClass());
        }
    }

    @Test
    public void testIndexOf() {
        List<Object> list = createList();

        list.add(A);
        list.add(B);
        list.add(C);

        assertEquals(0, list.indexOf(A));
        assertEquals(1, list.indexOf(B));
        assertEquals(2, list.indexOf(C));
    }

    @Test
    public void testContains() {
        List<Object> list = createList();


        list.add(A);
        list.add(B);
        list.add(C);

        assertTrue(list.contains(A));
        assertTrue(list.contains(B));
        assertTrue(list.contains(C));
    }

    @Test
    public void testClear() {
        List<Object> list = createList();

        list.add(A);
        list.add(B);
        list.add(C);

        assertFalse(list.isEmpty());
        assertEquals(3, list.size());

        list.clear();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }


}
