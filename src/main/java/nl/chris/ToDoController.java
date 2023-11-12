package nl.chris;

import nl.chris.views.ToDoWindow;

import java.util.ArrayList;
import java.util.Objects;

public class ToDoController {
    /**
     * The controller is the middleman between the view and the model.
     * It handles the logic and updates the view.
     */

    private Database database = new Database();
    private ArrayList<ToDoItem> items = new ArrayList<>();

    /**
     * Constructor
     */
    public ToDoController() {
//        createDummyItems();

        new ToDoWindow(getItems("none"), this);
    }

    /**
     * Dummy data
     */
    public void createDummyItems() {
        addItem("Item 1", 1);
        addItem("Item 2", 2);
        addItem("Item 3", 3);
        addItem("Item 4", 4);
        addItem("Item 5", 5);
        addItem("Item 6", 6);
        addItem("Item 7", 7);
        addItem("Item 8", 8);
    }

    /**
     * Get todo items
     * @return - Returns an ArrayList of to do items
     */
    public ArrayList<ToDoItem> getItems(String orderBy) {
        switch (orderBy) {
            case "name" -> {
                items = database.sortToDoItems("name");
            }
            case "status" -> {
                items = database.sortToDoItems("status");
            }
            case "none" -> {
                items = database.getToDoItems();
            }
        }

        return items;
    }

    /**
     * Add a todo item
     * @param name - The name of the to do item
     */
    public ToDoItem addItem(String name, Integer id) {
        ToDoItem item = new ToDoItem(name, false, id);

        database.addToDoItem(item);

        items.add(item);
        return item;
    }

    /**
     * Edit a todo item
     * @param editName - The new name of the to do item
     * @param editIsDone - The new isDone of the to do item
     */
    public void editItem(String editName, Boolean editIsDone, Integer id) {

        // Find item
        for (ToDoItem item : items) {
            if (Objects.equals(item.getId(), id)) {
                System.out.println("There was an item edited");
                return;
            }
        }
    }

    /**
     * Remove a to do item
     * @param id - The id of the to do item
     */
    public void removeItem(Integer id) {
        // Find item
        for (ToDoItem item : items) {
            if (Objects.equals(item.getId(), id)) {
                items.remove(item);
                database.removeToDoItem(item);
                System.out.println("removed item with id: " + id);
                return;
            }
        }
    }
}
