package org.exercise.coffee.machine;

import org.exercise.coffee.simulation.SimulationThread;

public interface CoffeeMachine extends SimulationThread {

    void setListener(CoffeeMachineListener listener);

}
