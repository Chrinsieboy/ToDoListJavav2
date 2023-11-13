package nl.chris;

import nl.chris.views.ToDoWindow;

import java.util.ArrayList;
import java.util.Objects;

public class ToDoController {
    /**
     * The controller is the middleman between the view and the model.
     * It handles the logic and updates the view.
     */

    private final Database database = new Database();
    private ArrayList<ToDoItem> items = new ArrayList<>();

    /**
     * Constructor
     */
    public ToDoController() {
        new ToDoWindow(getItems("none"), this);
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
    public void editItem(String editName, Boolean editIsDone, ToDoItem item) {
        item.setItemName(editName);
        item.setIsDone(editIsDone);

        database.editToDoItem(item.getId(), editName, editIsDone);
    }

    /**
     * Remove a to do item
     * @param id - The id of the to do item
     */
    public void removeItem(Integer id) {
        for (ToDoItem item : items) {
            if (Objects.equals(item.getId(), id)) {
                items.remove(item);
                database.removeToDoItem(item);
                return;
            }
        }
    }
}
