package org.exercise.coffee.queue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * An abstraction on top af PriorityBlockingQueue to guarantee that
 * entries with the same priority are treated with FIFO order.
 */
public class SimulationQueueImpl<E extends Comparable<? super E>> implements SimulationQueue<E> {

    private final PriorityBlockingQueue<FIFOEntry<E>> queue = new PriorityBlockingQueue<FIFOEntry<E>>();

    @Override
    public void add(E entry) {
        queue.add(new FIFOEntry<E>(entry));
    }

    @Override
    public E take() throws InterruptedException {
        FIFOEntry<E> fifoEntry = queue.take();
        return fifoEntry.getEntry();
    }

    public void clear() {
        queue.clear();
    }

}
