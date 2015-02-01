package com.algorythms.queues;

/**
 * Created by Krzysztof Januś on 2015-02-01.
 */
public class BlockingQueue<E> implements Queue<E> {
    private final Object mutex = new Object();
    private final Queue queue;

    private final int max;

    public BlockingQueue(Queue queue, int max) {
        this.queue = queue;
        this.max = max;
    }

    public BlockingQueue(Queue queue) {
        this(queue, Integer.MAX_VALUE);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void enqueue(E value) {
        synchronized (mutex) {
            while (size() == max) {
                waitForNotification();
            }
            queue.enqueue(value);
            mutex.notifyAll();
        }

    }

    private void waitForNotification() {
        try {
            mutex.wait();
        } catch (InterruptedException ignored) { }
    }

    @Override
    @SuppressWarnings("unchecked")
    public E dequeue() throws EmptyQueueException {
        synchronized (mutex) {
            while (isEmpt()) {
                waitForNotification();
            }
            Object value = queue.dequeue();
            mutex.notifyAll();
            return (E) value;
        }
    }

    @Override
    public void clear() {
        synchronized (mutex) {
            queue.clear();
            mutex.notifyAll();
        }
    }

    @Override
    public int size() {
        synchronized (mutex) {
            return queue.size();
        }
    }

    @Override
    public boolean isEmpt() {
        synchronized (mutex) {
            return queue.isEmpt();
        }
    }
}
