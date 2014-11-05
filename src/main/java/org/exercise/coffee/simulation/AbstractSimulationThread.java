package org.exercise.coffee.simulation;

import com.google.inject.Inject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public abstract class AbstractSimulationThread implements Runnable, SimulationThread {

    @Inject
    private ExecutorService executorService;

    private boolean run;
    private Future<?> future;

    @Override
    public void start() {
        run = true;
        future = executorService.submit(this);
    }

    @Override
    public void stop() {
        run = false;
        future.cancel(true);
    }

    @Override
    public void run() {
        checkPreconditions();

        while (run) {

            try {

                doRun();

            } catch (InterruptedException e) {
                run = false;
            }
        }

        onStopped();
    }

    public abstract void onStopped();
    public abstract void checkPreconditions();
    public abstract void doRun() throws InterruptedException;
}
