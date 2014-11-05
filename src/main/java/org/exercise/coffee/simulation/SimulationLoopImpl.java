package org.exercise.coffee.simulation;


import com.google.inject.Inject;
import org.exercise.coffee.SimulationProperties;
import org.exercise.coffee.model.Engineer;
import org.exercise.coffee.queue.SimulationQueue;

import java.util.Map;
import java.util.Random;
import java.util.Set;

import static com.google.inject.internal.util.$Preconditions.checkArgument;
import static com.google.inject.internal.util.$Preconditions.checkNotNull;
import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.sleep;

public class SimulationLoopImpl extends AbstractSimulationThread implements SimulationLoop {

    @Inject
    private Random random;
    @Inject
    private SimulationProperties properties;
    @Inject
    private SimulationQueue<Engineer> queue;
    @Inject
    private Map<Integer, Engineer> engineers;

    private int numEngineers;
    private SimulationListener listener;


    @Override
    public void init(Set<Engineer> engineers) {
        checkArgument(this.listener != null, "Error, the listener was not set.");
        this.numEngineers = engineers.size();

        for (Engineer engineer : engineers) {
            this.engineers.put(engineer.getId(), engineer);
            this.listener.onEngineerIdle(engineer);
        }
    }

    @Override
    public void doRun() throws InterruptedException {
        long start = currentTimeMillis();

        do {

            loop();

            sleep(random.nextInt(NEXT_LOOP_WAITING_TIME));

        } while (currentTimeMillis() - start < properties.getSimulationTimeStep());
    }

    @Override
    public void onStopped() {
        listener.onSimulationLoopTerminated();
    }

    @Override
    public void onCoffeeMachineTerminated() {
        listener.onCoffeeMachineTerminated();
    }

    @Override
    public void preparingCoffee(Engineer engineer) {
        listener.onCoffeePreparing(engineer);
    }

    @Override
    public synchronized void onCoffeeReady(Engineer engineer) {
        engineers.put(engineer.getId(), engineer);

        listener.onCoffeeReady(engineer);
        listener.onEngineerIdle(engineer);
    }

    @Override
    public void setListener(SimulationListener listener) {
        checkNotNull(listener);
        this.listener = listener;
    }

    @Override
    public void removeListener() {
        this.listener = null;
    }

    @Override
    public void checkPreconditions() {
        checkArgument(listener != null, "The listener was not set");
    }

    public synchronized void loop() {
        int id = random.nextInt(numEngineers);

        Engineer next = engineers.remove(id);

        if(next == null) return;

        next.nextStep();

        listener.onEngineerInQueue(next);

        queue.add(next);
    }

    private static final int NEXT_LOOP_WAITING_TIME = 1000;
}