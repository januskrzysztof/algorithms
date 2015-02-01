package com.algorythms.queues;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Krzysztof Januś on 2015-02-01.
 */
public abstract class AbstractQueueTest {
    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private Queue<Object> queue;

    @Before
    public void setUp() {
        queue = createFifoQueue();
    }

    protected abstract Queue<Object> createFifoQueue();

    @Test
    public void testAccessAnEmptyQueue() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpt());

        try {
            queue.dequeue();
            fail();
        } catch (Exception ex) {
            assertEquals(EmptyQueueException.class, ex.getClass());
        }
    }

    @Test
    public void testEnqueueDequeue() {
        queue.enqueue(B);
        queue.enqueue(A);
        queue.enqueue(C);

        assertEquals(3, queue.size());
        assertFalse(queue.isEmpt());

        assertEquals(B, queue.dequeue());
        assertFalse(queue.isEmpt());
        assertEquals(2, queue.size());

        assertEquals(A, queue.dequeue());
        assertFalse(queue.isEmpt());
        assertEquals(1, queue.size());

        assertEquals(C, queue.dequeue());
        assertTrue(queue.isEmpt());
        assertEquals(0, queue.size());

        try {
            queue.dequeue();
            fail();
        } catch (Exception ex) {
            assertEquals(EmptyQueueException.class, ex.getClass());
        }
    }

    @Test
    public void testClear() {
        queue.enqueue(A);
        queue.enqueue(B);
        queue.enqueue(C);

        assertFalse(queue.isEmpt());

        queue.clear();

        assertTrue(queue.isEmpt());

        try {
            queue.dequeue();
            fail();
        } catch (Exception ex) {
            assertEquals(EmptyQueueException.class, ex.getClass());
        }
    }
}
