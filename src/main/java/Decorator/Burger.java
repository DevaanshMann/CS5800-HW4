package Decorator;

public class Burger implements FoodItem {
    @Override public String description() { return "Burger"; }
    @Override public double cost() { return 5.00; }
}
