

package Bridge;

import java.util.*;

abstract class PaymentNotification {
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

interface NotificationChannel {
    void send(String to, String message);
}

class EmailChannel implements NotificationChannel {
    @Override
    public void send(String to, String message) {
        System.out.println("[EMAIL] to " + to + " :: " + message);
    }
}
class SmsChannel implements NotificationChannel {
    @Override
    public void send(String to, String message) {
        System.out.println("[SMS] to " + to + " :: " + message);
    }
}
class PushChannel implements NotificationChannel {
    @Override
    public void send(String to, String message) {
        System.out.println("[PUSH] to " + to + " :: " + message);
    }
}

class OnlinePaymentNotification extends PaymentNotification {
    private final String method;
    public OnlinePaymentNotification(NotificationChannel c, String customer, double amount, String method) {
        super(c, customer, amount);
        this.method = method;
    }
    @Override
    public void notifyCustomer() {
        channel.send(customer, String.format("Online payment of $%.2f received via %s. Thank you!", amount, method));
    }
}
class CashOnDeliveryPayment extends PaymentNotification {
    public CashOnDeliveryPayment(NotificationChannel c, String customer, double amount) {
        super(c, customer, amount);
    }
    @Override
    public void notifyCustomer() {
        channel.send(customer, String.format("Cash on Delivery: Please have $%.2f ready upon arrival.", amount));
    }
}
class BitcoinPayment extends PaymentNotification {
    private final String txId;
    public BitcoinPayment(NotificationChannel c, String customer, double amount, String txId) {
        super(c, customer, amount);
        this.txId = txId;
    }
    @Override
    public void notifyCustomer() {
        channel.send(customer, String.format("Bitcoin payment of $%.2f confirmed. TxId=%s", amount, txId));
    }
}


