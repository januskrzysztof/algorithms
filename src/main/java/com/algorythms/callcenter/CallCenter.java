package com.algorythms.callcenter;

import com.algorythms.queues.BlockingQueue;
import com.algorythms.queues.FifoQueue;

/**
 * Created by Krzysztof Januś on 2015-02-01.
 */
public class CallCenter {
    private final BlockingQueue<Call> queue = new BlockingQueue<>(new FifoQueue<>());
    private final int numberOfAgents;

    public CallCenter(int numberOfAgents) {
        this.numberOfAgents = numberOfAgents;
    }

    public void open() {
        System.out.println("Call center is opened.");
        for (int i=1; i<=numberOfAgents; i++) {
            Thread thread = new Thread(new CustomerServiceAgent(i, queue));
            thread.start();
        }
    }

    public void accept(Call call) {
        queue.enqueue(call);
        System.out.println(call+" add to queue");
    }

    public void close() {
        System.out.println("Call center is closed.");
    }
}
