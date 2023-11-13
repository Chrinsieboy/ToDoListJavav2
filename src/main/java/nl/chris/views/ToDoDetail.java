package nl.chris.views;

import nl.chris.Database;
import nl.chris.ToDoController;
import nl.chris.ToDoItem;
import nl.chris.ToDoList;

import javax.swing.*;
import java.util.ArrayList;

public class ToDoDetail extends JFrame {
    private final Database database = new Database();
    private final ToDoList list = new ToDoList(database.getToDoItems());
    public ToDoDetail(ToDoItem item, ToDoController toDoController) {
        super("ToDo Detail");
        // Create a new window
        JFrame editFrame = new JFrame("Edit item");

        // Create a new panel
        JPanel editPanel = new JPanel();

        // Create a new text field
        JTextField editTextField = new JTextField(20);
        editTextField.setText(item.getName());

        // Create a new checkbox
        JCheckBox editCheckBox = new JCheckBox("Done");
        editCheckBox.setSelected(item.getIsDone());

        // Create a save button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
//            item.setItemName(editTextField.getText());
//            item.setIsDone(editCheckBox.isSelected());
            toDoController.editItem(editTextField.getText(), editCheckBox.isSelected(), item);
//            database.editToDoItem(item.getId(), item.getName(), item.getIsDone());

            ArrayList<ToDoItem> items = database.getToDoItems();
            list.refreshList(items);
            editFrame.dispose();
        });

        // Create a cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            editFrame.dispose();
        });

        // Add all components to the panel
        editPanel.add(editTextField);
        editPanel.add(editCheckBox);
        editPanel.add(saveButton);
        editPanel.add(cancelButton);

        // Add the panel to the frame
        editFrame.add(editPanel);

        editFrame.setSize(300, 100);
        editFrame.setLayout(new BoxLayout(editFrame.getContentPane(), BoxLayout.Y_AXIS));
        editFrame.setLocation(200, 200);
        editFrame.setVisible(true);
    }
}
