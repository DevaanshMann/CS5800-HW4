package Decorator;

public class Cheese extends ToppingDecorator {
    public Cheese(FoodItem base) { super(base); }
    @Override public String description() { return base.description() + " + Cheese"; }
    @Override public double cost() { return base.cost() + 0.50; }
}

