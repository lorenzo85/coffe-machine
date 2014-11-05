package org.exercise.coffee.ui.listeners;

import org.exercise.coffee.simulation.Simulation;
import org.exercise.coffee.ui.SimulationUi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.google.inject.internal.util.$Preconditions.checkArgument;
import static java.lang.System.err;

public class StartBtnListener implements ActionListener {

    private final Simulation simulation;
    private final SimulationUi view;

    public StartBtnListener(Simulation simulation, SimulationUi view) {
        this.simulation = simulation;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Float highPriorityProbability = Float.valueOf(view.getHighPriorityProbability());
            Integer highPrioritySteps = Integer.valueOf(view.getHighPrioritySteps());
            Integer numberOfEngineers = Integer.valueOf(view.getNumberOfEngineers());

            checkArguments(numberOfEngineers, highPrioritySteps, highPriorityProbability);

            simulation.start(numberOfEngineers, highPrioritySteps, highPriorityProbability);

            view.setStartBtn(false);
            view.setStopBtn(true);

        } catch (NumberFormatException ex) {
            handleUserInputError(ex);

        } catch (IllegalArgumentException ex) {
            handleUserInputError(ex);
        }
    }


    private void checkArguments(Integer numberOfEngineers, Integer highPrioritySteps, Float highPriorityProbability) {
        checkArgument(numberOfEngineers != null && numberOfEngineers >= 0);
        checkArgument(highPrioritySteps != null && highPrioritySteps >= 0);
        checkArgument(highPriorityProbability != null && highPriorityProbability >= 0.0f);
    }

    private void handleUserInputError(Exception ex) {
        err.println(ex);
        view.reset();
        view.showDialog("User input was not valid.");
    }
}