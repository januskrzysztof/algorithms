package com.algorythms.callcenter;

import com.algorythms.queues.BlockingQueue;

/**
 * Created by Krzysztof Januś on 2015-02-01.
 */
public class CustomerServiceAgent implements Runnable {
    private static final long CLOSE_AFTER_MILLIS = 10000;
    private final long id;
    private final BlockingQueue<Call> queue;

    public CustomerServiceAgent(long id, BlockingQueue<Call> queue) {
        this.id    = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        long startMillis = System.currentTimeMillis();
        System.out.println(this+" salutate.");
        while (true) {
            if (startMillis-System.currentTimeMillis() >= CLOSE_AFTER_MILLIS) {
                break;
            }

            System.out.println(this+" wait...");
            Call call = queue.dequeue();
            System.out.println(this+" process: "+call);
            call.answer();
        }

        System.out.println(this+" bye bye.");
    }

    @Override
    public String toString() {
        return "Customer service agnet: "+id;
    }
}
