package Bridge;

public abstract class PaymentNotification {
    protected final NotificationChannel channel;
    protected final String customer;
    protected final double amount;

    protected PaymentNotification(NotificationChannel channel, String customer, double amount) {
        this.channel = channel;
        this.customer = customer;
        this.amount  = amount;
    }
    public abstract void notifyCustomer();
}
