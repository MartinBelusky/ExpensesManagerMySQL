package expenses;

import expenses.model.Category;
import expenses.model.Item;
import expenses.service.ItemService;
import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ItemService itemService = new ItemService();
        itemService.init();
        /*
        Item item = new Item("Apple", 2.0, Date.valueOf("2023-08-17"), Category.FRUIT, "discount 10 %");
        itemService.create(item);
        System.out.println(itemService.gelAllItems());
        itemService.update(6, item);
        */
        itemService.gelAllItems().forEach(System.out::println);
        System.out.println();

        System.out.println("Item no. 6 is: " + itemService.readById(6) + ".");
        System.out.println();

        System.out.println("Sum of all items is: " + itemService.sumAllItems() + " €.");
        System.out.println();

        itemService.sumAllItemsByCategory().entrySet().forEach(System.out::println);
        System.out.println();

        System.out.println("Sum in category MEAT: " + itemService.sumInCategory(Category.MEAT) + " €.");
        System.out.println();

        System.out.println("Number of items: " + itemService.countItems() + ".");
    }
}
