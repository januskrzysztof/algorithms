package com.algorythms.queues;

import com.algorythms.lists.LinkedList;
import com.algorythms.lists.List;

/**
 * Created by Krzysztof Januś on 2015-02-01.
 */
public class FifoQueue<E> implements Queue<E>  {
    private List<E> list;

    public FifoQueue() {
        this(new LinkedList<E>());
    }

    public FifoQueue(List<E> list) {
        this.list = list;
    }

    @Override
    public void enqueue(E value) {
        list.add(value);
    }

    @Override
    public E dequeue() throws EmptyQueueException {
        if (isEmpt()) {
            throw new EmptyQueueException();
        }

        return list.delete(0);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpt() {
        return list.isEmpty();
    }
}
