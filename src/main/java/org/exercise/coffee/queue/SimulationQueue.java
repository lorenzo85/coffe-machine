package org.exercise.coffee.queue;

public interface SimulationQueue<E extends Comparable<? super E>> {

    void add(E entry);

    E take() throws InterruptedException;

    void clear();
}
