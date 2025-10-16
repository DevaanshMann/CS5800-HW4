package Decorator;

public abstract class ToppingDecorator implements FoodItem {
    protected final FoodItem base;
    protected ToppingDecorator(FoodItem base) { this.base = base; }
}
