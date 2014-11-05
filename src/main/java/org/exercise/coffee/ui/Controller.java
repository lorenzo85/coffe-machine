package org.exercise.coffee.ui;

import org.exercise.coffee.model.Engineer;
import org.exercise.coffee.simulation.Simulation;
import org.exercise.coffee.simulation.SimulationListener;
import org.exercise.coffee.ui.listeners.StartBtnListener;
import org.exercise.coffee.ui.listeners.StopBtnListener;
import org.exercise.coffee.ui.listeners.WindowListener;

import static java.lang.System.out;

public class Controller implements SimulationListener {

    private SimulationUi view;
    private Simulation simulation;

    public void init(SimulationUi view, Simulation simulation) {
        this.view = view;
        this.simulation = simulation;
        this.simulation.setListener(this);

        addListeners();
    }

    @Override
    public void onCoffeePreparing(Engineer engineer) {
        view.onInfo("Preparing coffee for " + engineer);
    }

    @Override
    public void onCoffeeReady(Engineer engineer) {
        view.onInfo("Coffee ready for " + engineer);
    }

    @Override
    public void onEngineerIdle(Engineer engineer) {
        view.onEngineerIdle(engineer);
    }

    @Override
    public void onEngineerInQueue(Engineer next) {
        view.onEngineerInQueue(next);
    }

    @Override
    public void onSimulationLoopTerminated() {
        out.println("Simulation loop terminated.");
    }

    @Override
    public void onCoffeeMachineTerminated() {
        out.println("Coffee Machine Terminated.");
    }

    private void addListeners() {
        view.addWindowListener(new WindowListener(simulation));
        view.addStopSimulationListener(new StopBtnListener(simulation, view));
        view.addStartSimulationListener(new StartBtnListener(simulation, view));
    }
}