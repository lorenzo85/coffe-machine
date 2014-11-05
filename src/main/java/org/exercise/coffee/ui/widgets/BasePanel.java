package org.exercise.coffee.ui.widgets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import static javax.swing.BorderFactory.createEmptyBorder;

class BasePanel extends JPanel {

    static final int NO_PADDING = 0;
    static final int DEFAULT_PADDING = 10;

    private static final int SMALL_PADDING = 5;

    BasePanel() {
        setBorder(new EmptyBorder(DEFAULT_PADDING, NO_PADDING, NO_PADDING, NO_PADDING));
    }

    BasePanel(Border border) {
        setBorder(border);
    }

    JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        Border paddingBorder = createEmptyBorder(SMALL_PADDING, SMALL_PADDING, SMALL_PADDING, SMALL_PADDING);
        label.setBorder(paddingBorder);
        return label;
    }
}
