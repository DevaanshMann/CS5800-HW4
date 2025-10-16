package Bridge;

public class BitcoinPayment extends PaymentNotification {
    private final String txId;

    public BitcoinPayment(NotificationChannel c, String customer, double amount, String txId) {
        super(c, customer, amount);
        this.txId = txId;
    }

    @Override public void notifyCustomer() {
        channel.send(customer, String.format("Bitcoin payment of $%.2f confirmed. TxId=%s", amount, txId));
    }
}
