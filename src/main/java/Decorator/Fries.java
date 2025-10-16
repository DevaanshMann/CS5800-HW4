package Decorator;

public class Fries implements FoodItem {
    @Override public String description() { return "Fries"; }
    @Override public double cost() { return 2.50; }
}
