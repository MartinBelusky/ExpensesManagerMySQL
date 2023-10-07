package expenses.service;

import expenses.model.Category;
import expenses.model.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface IitemService {
    void create(Item item) throws SQLException;
    ArrayList<Item> gelAllItems() throws SQLException;
    Item readById(int id) throws SQLException;
    void update(int id, Item item) throws SQLException;
    void delete(int id) throws SQLException;
    double sumAllItems() throws SQLException;
    HashMap<Category, Double> sumAllItemsByCategory() throws SQLException;
    double sumInCategory(Category cat) throws SQLException;
    int countItems() throws SQLException;
}
