package org.exercise.coffee.ui;

import com.google.inject.Inject;
import org.exercise.coffee.SimulationProperties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

abstract class AbstractSimulationGUI extends JFrame implements SimulationUi {

    @Inject
    SimulationProperties properties;

    public void init() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        doSetUp(panel);

        getContentPane().add(panel);

        setTitle(properties.getAppName());
        setSize(properties.getWindowWith(), properties.getWindowHeight());
        setVisible(true);
    }

    GridBagConstraints createGbConstraints(int gridx, int gridy, int gridwith, double weightx, double weighty, int fill) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = fill;
        gbc.gridy = gridy;
        gbc.gridx = gridx;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.gridheight = 1;
        gbc.gridwidth = gridwith;
        return gbc;
    }

    protected abstract void doSetUp(JPanel main);
}
