package expenses.service;

import expenses.model.Category;
import expenses.model.Item;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ItemService implements IitemService, IDatabaseService {
    private Connection conn;
    private ArrayList<Item> list;
    private HashMap<Category, Double> map;

    @Override
    public void init() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/expenses_db";
        String username = "root";
        String password = "password";
        conn = DriverManager.getConnection(url, username, password);
        System.out.println("Database connected!");
    }

    @Override
    public void create(Item item) throws SQLException {
        String query = "INSERT INTO item (name, price, category, date, note) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, item.getName());
        preparedStmt.setDouble(2, item.getPrice());
        preparedStmt.setString(3, item.getCategory().toString());
        preparedStmt.setDate(4, item.getDate());
        preparedStmt.setString(5, item.getNote());
        preparedStmt.execute();
        preparedStmt.close();
    }

    @Override
    public ArrayList<Item> gelAllItems() throws SQLException {
        list = new ArrayList<>();
        String query = "SELECT * FROM item";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        ResultSet rs = preparedStmt.executeQuery(query);
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            double price = rs.getDouble("price");
            Category category = getCategory(rs.getString("category"));
            Date date = rs.getDate("date");
            String note = rs.getString("note");
            Item item = new Item(id, name, price, date, category, note);
            list.add(item);
        }
        preparedStmt.close();
        rs.close();
        return list;
    }

    private static Category getCategory(String categoryAsString) {
        Category category;
        switch (categoryAsString) {
            case "meat" -> category = Category.MEAT;
            case "dairy" -> category = Category.DAIRY;
            case "fruit" -> category = Category.FRUIT;
            case "vegetables" -> category = Category.VEGETABLES;
            case "bread" -> category = Category.BREAD;
            default -> category = Category.UNKNOWN;
        }
        return category;
    }

    @Override
    public Item readById(int id) throws SQLException {
        String query = "SELECT * FROM item WHERE id = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, id);
        ResultSet rs = preparedStmt.executeQuery();
        String name = "";
        double price = 0.0;
        Category category = null;
        Date date = null;
        String note = "";

        if (rs.next()) {
            name = rs.getString("name");
            price = rs.getDouble("price");
            category = getCategory(rs.getString("category"));
            date = rs.getDate("date");
            note = rs.getString("note");
        }
        preparedStmt.close();
        rs.close();
        return new Item(id, name, price, date, category, note);
    }

    @Override
    public void update(int id, Item item) throws SQLException {
        String query = "UPDATE item SET name = ?, price = ?, category = ?, date = ?, note = ? WHERE id = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, item.getName());
        preparedStmt.setDouble(2, item.getPrice());
        preparedStmt.setString(3, item.getCategory().toString());
        preparedStmt.setDate(4, item.getDate());
        preparedStmt.setString(5, item.getNote());
        preparedStmt.setInt(6, id);
        preparedStmt.executeUpdate();
        preparedStmt.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM item WHERE id = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, id);
        preparedStmt.execute();
        preparedStmt.close();
    }

    @Override
    public double sumAllItems() throws SQLException {
        String query = "SELECT SUM(price) FROM item";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        ResultSet rs = preparedStmt.executeQuery();
        double result = 0;
        if (rs.next()) {
            result = rs.getDouble(1);
        }
        preparedStmt.close();
        rs.close();
        return result;
    }

    @Override
    public HashMap<Category, Double> sumAllItemsByCategory() throws SQLException {
        map = new HashMap<>();
        String query = "SELECT category, SUM(price) FROM ITEM GROUP BY category";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        ResultSet rs = preparedStmt.executeQuery();

        while (rs.next()) {
            map.put(getCategory(rs.getString("category")), rs.getDouble("SUM(price)"));
        }

        preparedStmt.close();
        rs.close();
        return map;
    }

    @Override
    public double sumInCategory(Category cat) throws SQLException {
        String query = "SELECT SUM(price) FROM item WHERE category = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, cat.toString());
        ResultSet rs = preparedStmt.executeQuery();
        double sum = 0;
        if (rs.next()) {
            sum = rs.getDouble(1);
        }

        preparedStmt.close();
        rs.close();
        return sum;
    }

    @Override
    public int countItems() throws SQLException {
        String query = "SELECT COUNT(id) FROM item";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        ResultSet rs = preparedStmt.executeQuery();
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }

        preparedStmt.close();
        rs.close();
        return count;
    }
}
