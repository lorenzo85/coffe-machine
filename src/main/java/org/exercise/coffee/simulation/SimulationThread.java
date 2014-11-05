package org.exercise.coffee.simulation;

public interface SimulationThread {

    void start();

    void stop();

    void doRun() throws InterruptedException;

    void onStopped();

    void checkPreconditions();

}
