package org.exercise.coffee.ui;

import org.exercise.coffee.model.Engineer;

import java.awt.event.ActionListener;

public interface SimulationUi {

    public void onEngineerIdle(final Engineer engineer);

    public void onEngineerInQueue(final Engineer next);

    public void onInfo(final String text);

    public void reset();

    void init();

    void showDialog(String s);

    void setStartBtn(boolean b);

    void setStopBtn(boolean b);

    String getHighPriorityProbability();

    String getHighPrioritySteps();

    String getNumberOfEngineers();

    void addWindowListener(java.awt.event.WindowListener windowListener);

    void addStopSimulationListener(ActionListener listener);

    void addStartSimulationListener(ActionListener listener);
}
