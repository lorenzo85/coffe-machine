package org.exercise.coffee.ui.listeners;

import org.exercise.coffee.simulation.Simulation;
import org.exercise.coffee.ui.SimulationUi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopBtnListener implements ActionListener {

    private final Simulation simulation;
    private final SimulationUi view;

    public StopBtnListener(Simulation simulation, SimulationUi view) {
        this.simulation = simulation;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        simulation.stop();
        view.reset();
    }
}
