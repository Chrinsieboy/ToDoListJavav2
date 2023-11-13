package nl.chris;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    // Initialize dotenv
    Dotenv dotenv = Dotenv.load();

    // get database url from the .env
    private String DATABASE_URL = dotenv.get("DB_URL");
    private String DATABASE_USERNAME = dotenv.get("DB_USER");
    private String DATABASE_PASSWORD = dotenv.get("DB_PASSWORD");

    /**
     * Get Connection
     */
    public Connection connectDatabase() {
        Connection con;
        try {
             con = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             return con;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Get all ToDoItem objects from the database
     * @return - The ToDoItem array
     */
    public ArrayList<ToDoItem> getToDoItems() {
        ArrayList<ToDoItem> list = new ArrayList<>();

        try {
            Connection con = connectDatabase();
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM todoitems");
            while (rs.next()) {
                ToDoItem toDoItem = new ToDoItem(rs.getString("name"), rs.getBoolean("isDone"), rs.getInt("id"));
                list.add(toDoItem);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    /**
     * Add a ToDoItem object to the database
     * @param toDoItem - The ToDoItem object to add
     */
    public void addToDoItem(ToDoItem toDoItem) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = connectDatabase();

            String name = toDoItem.getName();
            boolean isDone = toDoItem.getIsDone();

            Statement stmt = con.createStatement();
            PreparedStatement ps = con.prepareStatement("INSERT INTO todoitems (name, isDone) VALUES (?, ?)");
            ps.setString(1, name);
            ps.setBoolean(2, isDone);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Edit a ToDoItem object in the database
     * @param id - The id of the ToDoItem
     * @param name - The new name of the ToDoItem
     * @param isDone - The new status of the ToDoItem
     */
    public void editToDoItem(Integer id, String name, Boolean isDone) {
        try {
            Connection con = connectDatabase();

            PreparedStatement ps = con.prepareStatement("UPDATE todoitems SET name = ?, isDone = ? WHERE id = ?");
            ps.setString(1, name);
            ps.setBoolean(2, isDone);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Remove a ToDoItem object from the database
     * @param toDoItem - The ToDoItem object to remove
     */
    public void removeToDoItem(ToDoItem toDoItem) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = connectDatabase();

            PreparedStatement ps = con.prepareStatement("DELETE FROM todoitems WHERE id = ?");
            ps.setInt(1, toDoItem.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Sort ToDoItem objects by name or status
     */
    public ArrayList<ToDoItem> sortToDoItems(String sortBy) {
        ArrayList<ToDoItem> list = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            Statement stmt = con.createStatement();
            ResultSet rs;
            switch (sortBy) {
                case "name":
                    rs = stmt.executeQuery("SELECT * FROM todoitems ORDER BY name ASC");
                    break;
                case "status":
                    rs = stmt.executeQuery("SELECT * FROM todoitems ORDER BY isDone ASC");
                    break;
                default:
                    rs = stmt.executeQuery("SELECT * FROM todoitems");
                    break;
            }

            while (rs.next()) {
                ToDoItem toDoItem = new ToDoItem(rs.getString("name"), rs.getBoolean("isDone"), rs.getInt("id"));
                list.add(toDoItem);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
