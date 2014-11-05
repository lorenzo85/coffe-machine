package org.exercise.coffee.machine;

import com.google.inject.Inject;
import org.exercise.coffee.SimulationProperties;
import org.exercise.coffee.model.Engineer;
import org.exercise.coffee.queue.SimulationQueue;
import org.exercise.coffee.simulation.AbstractSimulationThread;

import static com.google.inject.internal.util.$Preconditions.checkArgument;
import static com.google.inject.internal.util.$Preconditions.checkNotNull;
import static java.lang.Thread.sleep;

public class CoffeeMachineImpl extends AbstractSimulationThread implements CoffeeMachine {

    @Inject
    private SimulationProperties properties;
    @Inject
    private SimulationQueue<Engineer> queue;

    private CoffeeMachineListener listener;

    @Override
    public void doRun() throws InterruptedException {
        Engineer engineer = queue.take();

        listener.preparingCoffee(engineer);

        sleep(properties.getTimeToMakeCoffee());

        listener.onCoffeeReady(engineer);
    }

    @Override
    public void onStopped() {
        listener.onCoffeeMachineTerminated();
    }

    @Override
    public void setListener(CoffeeMachineListener listener) {
        checkNotNull(listener);
        this.listener = listener;
    }

    @Override
    public void checkPreconditions() {
        checkArgument(listener != null, "The listener was not set.");
    }
}