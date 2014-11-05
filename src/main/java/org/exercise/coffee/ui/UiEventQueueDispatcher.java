package org.exercise.coffee.ui;

import com.google.inject.Inject;
import org.exercise.coffee.model.Engineer;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import static javax.swing.SwingUtilities.invokeLater;

// A Decorator for SimulationUi implementations which dispatches Ui operations to the event queue.
public class UiEventQueueDispatcher implements SimulationUi {
    
    private final SimulationUi simulationUi;

    @Inject
    public UiEventQueueDispatcher(SimulationUi simulationUi) {
        this.simulationUi = simulationUi;
    }
    
    @Override
    public void onEngineerIdle(final Engineer engineer) {
        invokeLater(new Runnable() {
            @Override
            public void run() {
                simulationUi.onEngineerIdle(engineer);
            }
        });
    }

    @Override
    public void onEngineerInQueue(final Engineer next) {
       invokeLater(new Runnable() {
           @Override
           public void run() {
               simulationUi.onEngineerInQueue(next);
           }
       });
    }

    @Override
    public void onInfo(final String text) {
        invokeLater(new Runnable() {
            @Override
            public void run() {
                simulationUi.onInfo(text);
            }
        });
    }

    @Override
    public void reset() {
        invokeLater(new Runnable() {
            @Override
            public void run() {
                simulationUi.reset();
            }
        });
    }

    @Override
    public void showDialog(final String s) {
        invokeLater(new Runnable() {
            @Override
            public void run() {
                simulationUi.showDialog(s);
            }
        });
    }

    @Override
    public void init() {
        simulationUi.init();
    }

    @Override
    public void setStartBtn(boolean b) {
        simulationUi.setStartBtn(b);
    }

    @Override
    public void setStopBtn(boolean b) {
        simulationUi.setStopBtn(b);
    }

    @Override
    public String getHighPriorityProbability() {
        return simulationUi.getHighPriorityProbability();
    }

    @Override
    public String getHighPrioritySteps() {
        return simulationUi.getHighPrioritySteps();
    }

    @Override
    public String getNumberOfEngineers() {
        return simulationUi.getNumberOfEngineers();
    }

    @Override
    public void addWindowListener(WindowListener windowListener) {
         simulationUi.addWindowListener(windowListener);
    }

    @Override
    public void addStopSimulationListener(ActionListener listener) {
        simulationUi.addStopSimulationListener(listener);
    }

    @Override
    public void addStartSimulationListener(ActionListener listener) {
        simulationUi.addStartSimulationListener(listener);
    }
}
