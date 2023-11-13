package nl.chris.views;

import nl.chris.ToDoItem;

import javax.swing.*;
import java.util.ArrayList;

public class ToDoList extends JPanel {
    private final JPanel panel;

    public ToDoList(ArrayList<ToDoItem> items) {
        super();

        this.panel = this;

        for (ToDoItem item : items) {
            panel.add(item.ItemPanel());
        }

        // Set box layout on the left side
        panel.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        panel.setVisible(true);
    }

    public void refreshList(ArrayList<ToDoItem> items) {
        removeAll();
        for (ToDoItem item : items) {
            this.panel.add(item.ItemPanel());
        }
    }
}
