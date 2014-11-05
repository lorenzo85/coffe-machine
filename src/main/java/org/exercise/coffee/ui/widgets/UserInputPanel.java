package org.exercise.coffee.ui.widgets;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

public class UserInputPanel extends BasePanel {

    private static final int COLUMNS = 3;

    private JTextField highPriorityStepsTextField;
    private JTextField numberOfEngineersTextField;
    private JTextField highPriorityProbabilityTextField;

    private final String numberOfEngineers;
    private final String highPrioritySteps;
    private final String highPriorityProbability;

    public UserInputPanel(String numberOfEngineers, String highPrioritySteps, String highPriorityProbability) {
        super(new EmptyBorder(DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING));

        this.numberOfEngineers = numberOfEngineers;
        this.highPrioritySteps = highPrioritySteps;
        this.highPriorityProbability = highPriorityProbability;

        setUpLayout();
    }

    public String getHighPrioritySteps() {
        return highPriorityStepsTextField.getText();
    }

    public String getNumberOfEngineers() {
        return numberOfEngineersTextField.getText();
    }

    public String getHighPriorityProbability() {
        return highPriorityProbabilityTextField.getText();
    }

    public void reset() {
        numberOfEngineersTextField.setText(numberOfEngineers);
        highPriorityStepsTextField.setText(highPrioritySteps);
        highPriorityProbabilityTextField.setText(highPriorityProbability);
    }

    private JTextField createUserInput(String label, String defaultValue) {
        JTextField field = new JTextField(defaultValue);
        field.setColumns(COLUMNS);

        add(createLabel(label));
        add(field);

        return field;
    }

    private void setUpLayout() {
        setLayout(new GridLayout(3, 2));

        numberOfEngineersTextField = createUserInput("Number of engineers: ", numberOfEngineers);
        highPriorityStepsTextField = createUserInput("High priority steps: ", highPrioritySteps);
        highPriorityProbabilityTextField = createUserInput("High priority probability: ", highPriorityProbability);
    }
}