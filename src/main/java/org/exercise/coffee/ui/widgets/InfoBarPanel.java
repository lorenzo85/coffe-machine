package org.exercise.coffee.ui.widgets;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;

import static java.awt.BorderLayout.CENTER;
import static javax.swing.BorderFactory.*;

public class InfoBarPanel extends BasePanel {

    private final JLabel infoLabel;
    private final String defaultText;

    public InfoBarPanel(String title, String defaultText) {
        super(new EmptyBorder(DEFAULT_PADDING, DEFAULT_PADDING, NO_PADDING, DEFAULT_PADDING));
        this.defaultText = defaultText;
        setLayout(new BorderLayout());

        infoLabel = createLabel(defaultText);
        infoLabel.setBorder(
                createCompoundBorder(createTitledBorder(title),
                createEmptyBorder(DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING)));

        add(infoLabel, CENTER);
    }

    public void setText(String text) {
        this.infoLabel.setText(text);
    }

    public void reset() {
        infoLabel.setText(defaultText);
    }
}
