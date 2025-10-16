package Decorator;

public enum LoyaltyTier {
    NONE(0.00), SILVER(0.05), GOLD(0.10), PLATINUM(0.15);
    public final double discountRate;
    LoyaltyTier(double r) { this.discountRate = r; }
}
