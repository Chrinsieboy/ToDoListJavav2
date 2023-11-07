package nl.chris;

import java.util.ArrayList;

public class ToDoController {
    /**
     * The controller is the middleman between the view and the model.
     * It handles the logic and updates the view.
     */

    // Instance variables
    // private ToDoWindow toDoWindow;
    // private Database database = new Database();
    private ArrayList<Object> toDoItems = new ArrayList<>();

    /**
     * Constructor
     */
    public ToDoController() {
        System.out.println("ToDoController constructor called");
        dummyData();
        System.out.println(getToDoItems());

        addItem("Item 4");

        System.out.println(getToDoItems());

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        editItem("Item 4", "Item 5");

        System.out.println(getToDoItems());

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        removeItem("Item 1");
        removeItem("Item 0");

        System.out.println(getToDoItems());
    }

    /**
     * Dummy data
     */
    public void dummyData() {
        toDoItems.add("Item 1");
        toDoItems.add("Item 2");
        toDoItems.add("Item 3");
    }

    /**
     * Get to do items
     * @return - Returns an ArrayList of to do items
     */
    public ArrayList<Object> getToDoItems() {
        System.out.println("---=== ToDoController.getToDoItems called ===---");
        return toDoItems;

        // TODO: Get items from database
        // return database.getToDoItems();
    }

    /**
     * Add a to do item
     * @param name - The name of the to do item
     */
    public void addItem(String name) {
        System.out.println("---=== ToDoController.addItem called ===---");
        System.out.println("Name: " + name);

        toDoItems.add(name);
    }

    /**
     * Edit a to do item
     * @param name - The name of the to do item
     */
    public void editItem(String name, String editName) {
        System.out.println("---=== ToDoController.editItem called ===---");
        System.out.println("Name: " + name);

        // Find the index of the item to edit
        int indexToEdit = -1;
        for (int i = 0; i < toDoItems.size(); i++) {
            Object item = toDoItems.get(i);
            if (item instanceof String && ((String) item).equals(name)) {
                indexToEdit = i;
                break;
            }
        }

        // Check if the item was found
        if (indexToEdit >= 0) {
            // Edit the item at the found index
            toDoItems.set(indexToEdit, editName);
            System.out.println("Item edited successfully.");
        } else {
            System.out.println("Item not found for editing.");
        }
    }

    /**
     * Remove a to do item
     * @param name - The name of the to do item
     */
    public void removeItem(String name) {
        System.out.println("---=== ToDoController.removeItem called ===---");
        System.out.println("Removing: " + name);

        // Find the index of the item to remove
        int indexToRemove = -1;
        for (int i = 0; i < toDoItems.size(); i++) {
            Object item = toDoItems.get(i);
            if (item instanceof String && ((String) item).equals(name)) {
                indexToRemove = i;
                break;
            }
        }

        // Check if the item was found
        if (indexToRemove >= 0) {
            toDoItems.remove(indexToRemove);
            System.out.println("Item removed successfully.");
        } else {
            System.out.println("Item not found for removal.");
        }
    }
}
