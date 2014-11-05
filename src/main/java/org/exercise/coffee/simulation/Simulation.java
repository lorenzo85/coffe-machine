package org.exercise.coffee.simulation;

import com.google.inject.Inject;
import org.exercise.coffee.machine.CoffeeMachine;
import org.exercise.coffee.model.Engineer;
import org.exercise.coffee.queue.SimulationQueue;

import java.util.Set;

import static com.google.inject.internal.util.$Preconditions.*;

public class Simulation {

    @Inject
    private Set<Engineer> engineers;
    @Inject
    private CoffeeMachine coffeeMachine;
    @Inject
    private SimulationLoop simulationLoop;
    @Inject
    private SimulationQueue<Engineer> queue;

    private SimulationListener listener;

    private boolean running = false;


    public void start(Integer numEngineers, Integer highPriorityTimeSteps, Float highPriorityProbability) {
        checkState(running == false, "The simulation must be stopped before being restarted.");

        checkArgument(numEngineers != null, "The number of engineers must be provided.");
        checkArgument(highPriorityTimeSteps != null, "The high priority time steps value must be provided.");
        checkArgument(highPriorityProbability != null, "The high priority probability must be provided.");

        initialize(numEngineers, highPriorityTimeSteps, highPriorityProbability);

        simulationLoop.setListener(listener);
        coffeeMachine.setListener(simulationLoop);

        simulationLoop.init(engineers);
        coffeeMachine.start();
        simulationLoop.start();

        running = true;
    }

    public void stop() {
        checkState(running == true, "The simulation cannot be stopped if it is not running.");
        checkNotNull(simulationLoop);
        checkNotNull(coffeeMachine);
        checkNotNull(engineers);
        checkNotNull(queue);

        simulationLoop.stop();
        coffeeMachine.stop();

        engineers.clear();
        queue.clear();

        running = false;
    }

    public void setListener(SimulationListener listener) {
        checkNotNull(listener);
        this.listener = listener;
    }

    public boolean isRunning() {
        return running;
    }

    private void initialize(Integer numEngineers, Integer highPriorityTimeSteps, Float highPriorityProbability) {
        for(int i = 0; i < numEngineers; i++) {
            Engineer engineer = new Engineer(i, highPriorityTimeSteps, highPriorityProbability);
            engineers.add(engineer);
        }
    }
}
