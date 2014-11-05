package org.exercise.coffee.ui;

import org.exercise.coffee.model.Engineer;
import org.exercise.coffee.ui.widgets.ControlsPanel;
import org.exercise.coffee.ui.widgets.InfoBarPanel;
import org.exercise.coffee.ui.widgets.ScrollPanel;
import org.exercise.coffee.ui.widgets.UserInputPanel;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;

import static java.awt.GridBagConstraints.*;
import static java.lang.String.valueOf;
import static javax.swing.JOptionPane.showMessageDialog;

public class SimulationUiImpl extends AbstractSimulationGUI {

    private InfoBarPanel infoBar;
    private ControlsPanel controls;
    private UserInputPanel userInputs;
    private ScrollPanel<Engineer> leftScroll;
    private ScrollPanel<Engineer> rightScroll;

    @Override
    protected void doSetUp(JPanel panel) {
        setUpInfoBar(panel);
        setUpControls(panel);
        setUpLeftScroll(panel);
        setUpUserInputs(panel);
        setUpRightScroll(panel);
    }

    @Override
    public void addStartSimulationListener(ActionListener listener) {
        controls.addStartBtnListener(listener);
    }

    @Override
    public void addStopSimulationListener(ActionListener listener) {
        controls.addStopBtnListener(listener);
    }

    @Override
    public void reset() {
        rightScroll.removeAllElements();
        leftScroll.removeAllElements();
        userInputs.reset();
        controls.reset();
        infoBar.reset();
    }

    @Override
    public void onEngineerIdle(Engineer engineer) {
        leftScroll.addElement(engineer);
        rightScroll.removeElement(engineer);
    }

    @Override
    public void onEngineerInQueue(Engineer next) {
        leftScroll.removeElement(next);
        rightScroll.addElement(next);
    }

    @Override
    public void onInfo(final String text) {
        infoBar.setText(text);
    }

    @Override
    public String getHighPriorityProbability() {
        return userInputs.getHighPriorityProbability();
    }

    @Override
    public String getHighPrioritySteps() {
        return userInputs.getHighPrioritySteps();
    }

    @Override
    public String getNumberOfEngineers() {
        return userInputs.getNumberOfEngineers();
    }

    @Override
    public void showDialog(String message) {
        showMessageDialog(this, message);
    }

    @Override
    public void setStartBtn(boolean enabled) {
        controls.setStartBtn(enabled);
    }

    @Override
    public void setStopBtn(boolean enabled) {
        controls.setStopBtn(enabled);
    }

    private void setUpInfoBar(JPanel panel) {
        infoBar = new InfoBarPanel("Coffee machine display", "Idle");
        panel.add(infoBar, createGbConstraints(0, 1, 2, 0, 0, BOTH));
    }

    private void setUpLeftScroll(JPanel panel) {
        leftScroll = new ScrollPanel<Engineer>("Idle engineers");
        leftScroll.setPreferredSize(getScrollPreferredSize());
        panel.add(leftScroll, createGbConstraints(0, 2, 1, 1.0, 1.0, BOTH));
    }

    private void setUpRightScroll(JPanel panel) {
        rightScroll = new ScrollPanel<Engineer>("Engineers in queue");
        rightScroll.setPreferredSize(getScrollPreferredSize());
        panel.add(rightScroll, createGbConstraints(1, 2, 1, 1.0, 1.0, BOTH));
    }

    private void setUpControls(JPanel panel) {
        controls = new ControlsPanel();
        GridBagConstraints gbc = createGbConstraints(1, 0, 1, 0.01, 0.01, HORIZONTAL);
        gbc.anchor = NORTH;
        panel.add(controls, gbc);
    }

    private void setUpUserInputs(JPanel panel) {
        String numEngineers = valueOf(properties.getNumEngineers());
        String highPriorityTimeSteps = valueOf(properties.getHighPriorityTimeSteps());
        String highPriorityProbability = valueOf(properties.getHighPriorityProbability());
        userInputs = new UserInputPanel(numEngineers, highPriorityTimeSteps, highPriorityProbability);

        panel.add(userInputs, createGbConstraints(0, 0, 1, 0.5, 0.01, BOTH));
    }

    private Dimension getScrollPreferredSize() {
        // Forces scroll preferred size to 50% of the window width and 75% of the window height.
        return new Dimension(properties.getWindowWith() / 2, (properties.getWindowHeight() / 3) * 2);
    }
}