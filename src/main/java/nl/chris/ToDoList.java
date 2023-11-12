package nl.chris;

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

    public void refreshList(ArrayList<ToDoItem> list) {
        removeAll();
        for (ToDoItem item : list) {
            this.panel.add(item.ItemPanel());
        }
    }
}
