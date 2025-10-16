package Decorator;

public class HotDog implements FoodItem {
    @Override public String description() { return "Hot Dog"; }
    @Override public double cost() { return 3.50; }
}
