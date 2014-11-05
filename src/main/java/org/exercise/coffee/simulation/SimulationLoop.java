package org.exercise.coffee.simulation;

import org.exercise.coffee.machine.CoffeeMachineListener;
import org.exercise.coffee.model.Engineer;

import java.util.Set;

public interface SimulationLoop extends CoffeeMachineListener, SimulationThread {

    void init(Set<Engineer> engineerSet);

    void setListener(SimulationListener listener);

    void removeListener();

    void loop();
}
