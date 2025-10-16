package Decorator;

public class Ketchup extends ToppingDecorator {
    public Ketchup(FoodItem base) { super(base); }
    @Override public String description() { return base.description() + " + Ketchup"; }
    @Override public double cost() { return base.cost() + 0.20; }
}

