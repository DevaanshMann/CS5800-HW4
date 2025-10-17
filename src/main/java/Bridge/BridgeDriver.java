package Bridge;

public class BridgeDriver {
    public static void main(String[] args) {
        NotificationChannel email = new EmailChannel();
        NotificationChannel sms   = new SmsChannel();
        NotificationChannel push  = new PushChannel();

        new OnlinePaymentNotification(email, "alex@email.com", 129.99, "Credit Card").notifyCustomer();
        new OnlinePaymentNotification(sms, "+1-555-0100", 49.99,  "Zelle").notifyCustomer();

        new CashOnDeliveryPayment(email, "dave@email.com", 19.99).notifyCustomer();
        new CashOnDeliveryPayment(sms, "+1-818-0101", 89.00).notifyCustomer();

        new BitcoinPayment(email,"dm@email.com", 250.00, "tx_dm2312").notifyCustomer();
        new BitcoinPayment(sms, "+1-818-0102", 75.25, "tx_mm2707").notifyCustomer();

        new OnlinePaymentNotification(push, "dave_device_id", 15.49, "Credit Card").notifyCustomer();
        new CashOnDeliveryPayment(push, "erin_device_id", 42.00).notifyCustomer();
    }
}