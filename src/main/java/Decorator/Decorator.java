package Decorator;

import java.util.*;

// ===== Component =====
interface FoodItem {
    String description();
    double cost();
}

// ===== Concrete Components =====
class Burger implements FoodItem {
    @Override public String description() { return "Burger"; }
    @Override public double cost() { return 5.00; }
}
class Fries implements FoodItem {
    @Override public String description() { return "Fries"; }
    @Override public double cost() { return 2.50; }
}
class HotDog implements FoodItem {
    @Override public String description() { return "Hot Dog"; }
    @Override public double cost() { return 3.50; }
}

// ===== Decorator =====
abstract class ToppingDecorator implements FoodItem {
    protected final FoodItem base;
    protected ToppingDecorator(FoodItem base) { this.base = base; }
}

// ===== Concrete Decorators =====
class Ketchup extends ToppingDecorator {
    public Ketchup(FoodItem base) { super(base); }
    @Override public String description() { return base.description() + " + Ketchup"; }
    @Override public double cost() { return base.cost() + 0.20; }
}
class Cheese extends ToppingDecorator {
    public Cheese(FoodItem base) { super(base); }
    @Override public String description() { return base.description() + " + Cheese"; }
    @Override public double cost() { return base.cost() + 0.50; }
}
class Onions extends ToppingDecorator {
    public Onions(FoodItem base) { super(base); }
    @Override public String description() { return base.description() + " + Onions"; }
    @Override public double cost() { return base.cost() + 0.30; }
}

// ===== Order Aggregation =====
class Order {
    private final List<FoodItem> items = new ArrayList<>();
    public void add(FoodItem item) { items.add(item); }
    public double subtotal() { return items.stream().mapToDouble(FoodItem::cost).sum(); }
    public void printItems() {
        for (FoodItem i : items) System.out.printf(" - %-30s $%.2f%n", i.description(), i.cost());
    }
}

// ===== Loyalty Discount (post-calculation) =====
enum LoyaltyTier {
    NONE(0.00), SILVER(0.05), GOLD(0.10), PLATINUM(0.15);
    final double discountRate;
    LoyaltyTier(double r) { this.discountRate = r; }
}
class LoyaltyStatus {
    private final LoyaltyTier tier;
    public LoyaltyStatus(LoyaltyTier tier) { this.tier = tier; }
    public double applyDiscount(double amount) { return amount * (1.0 - tier.discountRate); }
    public LoyaltyTier tier() { return tier; }
}


