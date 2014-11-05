package org.exercise.coffee.simulation;

import org.exercise.coffee.model.Engineer;

public interface SimulationListener {

    void onSimulationLoopTerminated();

    void onCoffeeMachineTerminated();

    void onCoffeePreparing(Engineer engineer);

    void onCoffeeReady(Engineer engineer);

    void onEngineerIdle(Engineer engineer);

    void onEngineerInQueue(Engineer next);
}
