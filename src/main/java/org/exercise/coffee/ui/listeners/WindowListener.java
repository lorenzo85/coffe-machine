package org.exercise.coffee.ui.listeners;

import org.exercise.coffee.simulation.Simulation;

import java.awt.event.WindowEvent;

import static java.lang.System.exit;

public class WindowListener implements java.awt.event.WindowListener {

    private final Simulation simulation;

    public WindowListener(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        if(simulation.isRunning()) {
            simulation.stop();
        }
        exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}