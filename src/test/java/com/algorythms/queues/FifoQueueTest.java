package com.algorythms.queues;

/**
 * Created by Krzysztof Januś on 2015-02-01.
 */
public class FifoQueueTest extends AbstractQueueTest {
    @Override
    protected Queue<Object> createFifoQueue() {
        return new FifoQueue<>();
    }
}
