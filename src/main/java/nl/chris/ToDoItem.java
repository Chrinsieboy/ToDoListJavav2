package nl.chris;

import javax.swing.*;

public class ToDoItem extends JPanel {
    // Instance variables
    private Integer id;
    private String name;
    private Boolean isDone;
    private Boolean isSelected;
    private JCheckBox checkBox;

    /**
     * Constructor
     */
    public ToDoItem(String name, Boolean isDone, Integer id) {
        super();

        this.name = name;
        this.isDone = isDone;
        this.id = id;
        this.isSelected = false;
    }

    public JPanel ItemPanel() {
        JPanel panel = this;

        checkBox = new JCheckBox();
        if (getIsDone()) {
            checkBox.setText("✔ " + getName());
        } else {
            checkBox.setText("❌ " + getName());
        }

        checkBox.addActionListener(e -> {
            if (checkBox.isSelected()) {
                setSelected(true);
            } else {
                setSelected(false);
            }
        });

        panel.add(checkBox);
        panel.setVisible(true);

        return panel;
    }

    // Adders

    /**
     * Set name
     * @param name - name of the item that will be set
     * @return - Returns the new name of the item
     */
    public String setItemName(String name) {
        this.name = name;
        if (getIsDone()) {
            checkBox.setText("✔ " + name);;
        } else {
            checkBox.setText("❌ " + name);
        }

        return this.name;
    }

    /**
     * Set isDone
     * @param isDone - isDone of the item that will be set
     * @return - Returns the new isDone of the item
     */
    public Boolean setIsDone(Boolean isDone) {
        this.isDone = isDone;
        if (getIsDone()) {
            checkBox.setText("✔ " + getName());;
        } else {
            checkBox.setText("❌ " + getName());
        }
        return this.isDone;
    }

    public Boolean setSelected(Boolean isSelected) {
        this.isSelected = isSelected;
        return this.isSelected;
    }

    // Getters

    /**
     * Get name
     * @return - Returns the name of the item
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get isDone
     * @return - Returns the isDone of the item
     */
    public Boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Get id
     */
    public Integer getId() {
        return this.id;
    }

    public Boolean getIsSelected() {
        return this.isSelected;
    }
}
