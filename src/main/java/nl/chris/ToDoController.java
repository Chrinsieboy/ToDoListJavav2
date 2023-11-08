package nl.chris;

import java.util.ArrayList;
import java.util.Objects;

public class ToDoController {
    /**
     * The controller is the middleman between the view and the model.
     * It handles the logic and updates the view.
     */

    // Instance variables
    // private ToDoWindow toDoWindow;
    // private Database database = new Database();
    private ArrayList<ToDoItem> items = new ArrayList<>();

    /**
     * Constructor
     */
    public ToDoController() {
        System.out.println("ToDoController constructor called");
        createDummyItems();
        getItems();

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        addItem("Item 4", true, 4);
        getItems();

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        editItem("Item 5", true, 1);
        getItems();

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        removeItem(4);
        getItems();
    }

    /**
     * Dummy data
     */
    public void createDummyItems() {
        items.add(new ToDoItem() {{
            addItem("Item 1", false, 1);
        }});
        items.add(new ToDoItem() {{
            addItem("Item 2", false, 2);
        }});
        items.add(new ToDoItem() {{
            addItem("Item 3", true, 3);
        }});
    }

    /**
     * Get todo items
     * @return - Returns an ArrayList of to do items
     */
    public ArrayList<ToDoItem> getItems() {
        System.out.println("---=== ToDoController.getToDoItems called ===---");
        for (ToDoItem item : items) {
            System.out.println(item.getName() + " - " + item.getIsDone() + " - " + item.getId());
        }
        return items;

        // TODO: Get items from database
        // return database.getToDoItems();
    }

    /**
     * Add a todo item
     * @param name - The name of the to do item
     * @param isDone - The isDone of the to do item
     */
    public void addItem(String name, Boolean isDone, Integer id) {
        System.out.println("---=== ToDoController.addItem called ===---");
        System.out.println("Name: " + name);

        items.add(new ToDoItem() {{
            addItem(name, isDone, id);
        }});
    }

    /**
     * Edit a todo item
     * @param editName - The new name of the to do item
     * @param editIsDone - The new isDone of the to do item
     */
    public void editItem(String editName, Boolean editIsDone, Integer id) {
        System.out.println("---=== ToDoController.editItem called ===---");

        // Find item
        for (ToDoItem item : items) {
            if (Objects.equals(item.getId(), id)) {
                item.addItem(editName, editIsDone, id);
                System.out.println("Name: " + item.getName());
                System.out.println("isDone: " + item.getIsDone());
                return;
            }
        }
    }

    /**
     * Remove a to do item
     * @param id - The id of the to do item
     */
    public void removeItem(Integer id) {
        System.out.println("---=== ToDoController.removeItem called ===---");

        // Find item
        for (ToDoItem item : items) {
            if (Objects.equals(item.getId(), id)) {
                items.remove(item);
                System.out.println("removed item with id: " + id);
                return;
            }
        }
    }
}
