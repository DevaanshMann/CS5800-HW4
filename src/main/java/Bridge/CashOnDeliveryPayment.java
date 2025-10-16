package Bridge;

public class CashOnDeliveryPayment extends PaymentNotification {
    public CashOnDeliveryPayment(NotificationChannel c, String customer, double amount) {
        super(c, customer, amount);
    }

    @Override public void notifyCustomer() {
        channel.send(customer, String.format("Cash on Delivery: Please have $%.2f ready upon arrival.", amount));
    }
}

