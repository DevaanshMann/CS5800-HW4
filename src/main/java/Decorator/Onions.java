package Decorator;

public class Onions extends ToppingDecorator {
    public Onions(FoodItem base) { super(base); }
    @Override public String description() { return base.description() + " + Onions"; }
    @Override public double cost() { return base.cost() + 0.30; }
}
