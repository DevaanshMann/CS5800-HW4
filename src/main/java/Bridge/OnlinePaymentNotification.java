package Bridge;

public class OnlinePaymentNotification extends PaymentNotification {
    private final String method; // "Credit Card", "PayPal", etc.

    public OnlinePaymentNotification(NotificationChannel c, String customer, double amount, String method) {
        super(c, customer, amount);
        this.method = method;
    }

    @Override public void notifyCustomer() {
        channel.send(customer, String.format("Online payment of $%.2f received via %s. Thank you!", amount, method));
    }
}
