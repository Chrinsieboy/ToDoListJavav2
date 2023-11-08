package nl.chris;

import javax.swing.*;

public class ToDoItem extends JPanel {
    // Instance variables
    private Integer id;
    private String name;
    private Boolean isDone;

    /**
     * Constructor
     */
    public ToDoItem() {
        //
    }

    // Adders

    /**
     * Add new item
     * @param name - name of the item
     * @return - Returns the name of the item
     */
    public String addItem(String name, Boolean isDone, Integer id) {
        this.name = name;
        this.isDone = isDone;
        this.id = id;
        return this.name;
    }

    /**
     * Set name
     * @param name - name of the item that will be set
     * @return - Returns the new name of the item
     */
    public String setItemName(String name) {
        return this.name;
    }

    /**
     * Set isDone
     * @param isDone - isDone of the item that will be set
     * @return - Returns the new isDone of the item
     */
    public Boolean setIsDone(Boolean isDone) {
        return this.isDone;
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
}
