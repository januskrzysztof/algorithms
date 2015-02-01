package com.algorythms.queues;

/**
 * Created by Krzysztof Januś on 2015-02-01.
 */
public interface Queue<E> {
    public void enqueue(E value);
    public E dequeue() throws EmptyQueueException;
    public void clear();
    public int size();
    public boolean isEmpt();
}
