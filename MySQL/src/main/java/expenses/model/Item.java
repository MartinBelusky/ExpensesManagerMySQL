package expenses.model;

import java.sql.Date;
import java.util.Objects;

public class Item {
    private int id;
    private String name;
    private double price;
    private Date date;
    private Category category;
    private String note;

    public Item(String name, double price, Date date, Category category, String note) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.category = category;
        this.note = note;
    }

    public Item(int id, String name, double price, Date date, Category category, String note) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
        this.category = category;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        if (note == null || note.equals("")) {
            return "id=" + id +", name= " + name + ", price= " + price + " €, date= " + date + ", category= " + category;
        }
        return "id=" + id +", name= " + name + ", price= " + price + " €, date= " + date + ", category= " + category + ", note= " + note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        if (note.equals("")) {
            return id == item.id && Double.compare(item.price, price) == 0 && Objects.equals(name, item.name) && Objects.equals(date, item.date) && category == item.category;
        }
        return id == item.id && Double.compare(item.price, price) == 0 && Objects.equals(name, item.name) && Objects.equals(date, item.date) && category == item.category && Objects.equals(note, item.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, date, category, note);
    }
}
