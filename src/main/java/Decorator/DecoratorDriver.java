package Decorator;

public class DecoratorDriver {
    public static void main(String[] args) {

        FoodItem item1 = new Cheese(new Ketchup(new Onions(new Burger())));
        FoodItem item2 = new Onions(new Cheese(new HotDog()));
        FoodItem item3 = new Ketchup(new Fries());

        Order order = new Order();
        order.add(item1);
        order.add(item2);
        order.add(item3);

        System.out.println("          DECORATOR DEMO");
        order.printItems();
        double subtotal = order.subtotal();
        System.out.printf("Subtotal: $%.2f%n", subtotal);

        LoyaltyStatus status = new LoyaltyStatus(LoyaltyTier.PLATINUM);
        double totalAfterDiscount = status.applyDiscount(subtotal);
        System.out.printf("Loyalty Tier: %s (%.0f%% off)%n", status.tier(), status.tier().discountRate * 100);
        System.out.printf("Total after discount: $%.2f%n", totalAfterDiscount);
    }
}