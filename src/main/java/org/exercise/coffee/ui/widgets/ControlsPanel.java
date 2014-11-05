package org.exercise.coffee.ui.widgets;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class ControlsPanel extends BasePanel {

    private static final String START_SIMULATION = "Start simulation";
    private static final String STOP_SIMULATION = "Stop simulation";

    private final JButton startBtn;
    private final JButton stopBtn;

    public ControlsPanel() {
        setLayout(new GridLayout(2, 1));

        startBtn = new JButton(START_SIMULATION);
        stopBtn = new JButton(STOP_SIMULATION);
        stopBtn.setEnabled(false);

        add(startBtn);
        add(stopBtn);
    }

    public void addStartBtnListener(ActionListener listener) {
        this.startBtn.addActionListener(listener);
    }

    public void addStopBtnListener(ActionListener listener) {
        this.stopBtn.addActionListener(listener);
    }

    public void setStartBtn(boolean enabled) {
        this.startBtn.setEnabled(enabled);
    }

    public void setStopBtn(boolean enabled) {
        this.stopBtn.setEnabled(enabled);
    }

    public void reset() {
        setStartBtn(true);
        setStopBtn(false);
    }
}
