package Decorator;

public class LoyaltyStatus {
    private final LoyaltyTier tier;
    public LoyaltyStatus(LoyaltyTier tier) { this.tier = tier; }
    public double applyDiscount(double amount) { return amount * (1.0 - tier.discountRate); }
    public LoyaltyTier tier() { return tier; }
}
