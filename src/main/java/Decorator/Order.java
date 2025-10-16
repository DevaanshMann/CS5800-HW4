package Decorator;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<FoodItem> items = new ArrayList<>();
    public void add(FoodItem item) { items.add(item); }
    public double subtotal() { return items.stream().mapToDouble(FoodItem::cost).sum(); }
    public void printItems() {
        for (FoodItem i : items) System.out.printf(" - %-30s $%.2f%n", i.description(), i.cost());
    }
}
