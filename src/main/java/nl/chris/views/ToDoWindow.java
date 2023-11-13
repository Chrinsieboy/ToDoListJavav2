package nl.chris.views;

import nl.chris.ToDoController;
import nl.chris.ToDoItem;
import nl.chris.ToDoList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ToDoWindow extends JFrame {
    private final ToDoController toDoController;
    private final ToDoList toDoList;
    private final JTextField textField = new JTextField(20);
    private final JPanel panel = new JPanel();
    private ArrayList<ToDoItem> selectedItems = new ArrayList<>();
    private Boolean inOrder;

    public ToDoWindow(ArrayList<ToDoItem> items, ToDoController toDoController) {
        super("ToDo App");

        this.toDoList = new ToDoList(items);
        this.toDoController = toDoController;

        add(inputField(), BorderLayout.NORTH);
        add(toDoList, BorderLayout.WEST);
        add(buttonPanel(), BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setVisible(true);
    }

    /**
     * Input field
     */
    public JTextField inputField() {
        textField.setSize(1, 1);
        textField.setVisible(true);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(textField);
        return textField;
    }

    /**
     * Get the items that are selected by checkbox
     */
    public void getSelectedItems() {
        selectedItems.clear();

        // Check if there are items selected
        for (Component component : toDoList.getComponents()) {
            if (component instanceof ToDoItem item) {
                if (item.getIsSelected()) {
                    selectedItems.add(item);
                }
            }
        }
    }

    /**
     * Button panel
     */
    public JPanel buttonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton());
        buttonPanel.add(editButton());
        buttonPanel.add(removeItem());
        buttonPanel.add(changeOrder());
        return buttonPanel;
    }

    /**
     * Add Button
     */
    public JPanel addButton() {
        JButton button = new JButton("Add");
        button.setSize(1, 1);
        button.setVisible(true);

        button.addActionListener(e -> {
            if (textField.getText().isEmpty()) {
                return;
            } else {
                toDoController.addItem(textField.getText(), 9);
                toDoList.refreshList(toDoController.getItems("none"));
                textField.setText("");
            }
            pack();
            this.revalidate();
            this.repaint();
        });

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(button);
        panel.setVisible(true);

        return panel;
    }

    /**
     * Edit Button
     */
    public JButton editButton() {
        JButton button = new JButton("Edit");
        button.setSize(1, 1);
        button.setVisible(true);

        button.addActionListener(e -> {
            getSelectedItems();
            if (selectedItems.size() > 1) {
                JOptionPane.showMessageDialog(null, "You can only edit 1 item at a time");
            } else if (selectedItems.isEmpty()) {
                JOptionPane.showMessageDialog(null, "You need to select an item to edit");
            } else {
                for (ToDoItem item : selectedItems) {
                    new ToDoDetail(item, toDoController);
                }
                pack();
                this.revalidate();
                this.repaint();
            }
        });

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(button);
        return button;
    }

    /**
     * Remove Button
     */
    public JButton removeItem() {
        JButton button = new JButton("Remove");
        button.setSize(1, 1);
        button.setVisible(true);

        button.addActionListener(e -> {
            getSelectedItems();
            if (selectedItems.isEmpty()) {
                JOptionPane.showMessageDialog(null, "You need to select 1 or more items to remove");
            } else {
                for (ToDoItem item : selectedItems) {
                    toDoController.removeItem(item.getId());
                    toDoList.refreshList(toDoController.getItems("none"));
                }
                pack();
                this.revalidate();
                this.repaint();
            }
        });

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(button);
        return button;
    }

    /**
     * Change order
     */
    public JButton changeOrder() {
        JButton button = new JButton("Change order");
        button.setSize(1, 1);
        button.setVisible(true);
        this.inOrder = false;

        button.addActionListener(e -> {
            this.inOrder = !this.inOrder;
            if (inOrder) {
                toDoList.refreshList(toDoController.getItems("status"));
            } else {
                toDoList.refreshList(toDoController.getItems("name"));
            }
            pack();
            this.revalidate();
            this.repaint();
        });

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(button);
        return button;
    }
}
