package Bridge;

public class BridgeDriver {
    public static void main(String[] args) {
        NotificationChannel email = new EmailChannel();
        NotificationChannel sms   = new SmsChannel();
        NotificationChannel push  = new PushChannel();

        new OnlinePaymentNotification(email, "alice@example.com", 129.99, "Credit Card").notifyCustomer();
        new OnlinePaymentNotification(sms, "+1-555-0100", 49.99,  "PayPal").notifyCustomer();
        new CashOnDeliveryPayment(email, "bob@example.com", 19.99).notifyCustomer();
        new CashOnDeliveryPayment(sms, "+1-555-0101", 89.00).notifyCustomer();

        new BitcoinPayment(email,"carol@example.com", 250.00, "tx_abcd1234").notifyCustomer();
        new BitcoinPayment(sms, "+1-555-0102", 75.25, "tx_efgh5678").notifyCustomer();

        new OnlinePaymentNotification(push, "dave_device_id", 15.49, "Credit Card").notifyCustomer();
        new CashOnDeliveryPayment(push, "erin_device_id", 42.00).notifyCustomer();
    }
}