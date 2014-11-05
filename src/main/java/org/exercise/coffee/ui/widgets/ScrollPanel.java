package org.exercise.coffee.ui.widgets;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS;
import static javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS;

public class ScrollPanel<T> extends BasePanel {

    private final DefaultListModel listModel = new DefaultListModel();

    public ScrollPanel(String title) {
        setLayout(new BorderLayout());
        add(createLabel(title), NORTH);

        JList list = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(list, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane, CENTER);
    }

    public void addElement(T element) {
        listModel.addElement(element);
    }

    public void removeElement(T element) {
        listModel.removeElement(element);
    }

    public void removeAllElements() {
        listModel.removeAllElements();
    }
}
