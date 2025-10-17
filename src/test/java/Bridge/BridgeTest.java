package Bridge;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class BridgeTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream capture;

    @BeforeEach
    void setUp() {
        capture = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capture));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void emailSend() {
        NotificationChannel ch = new EmailChannel();
        ch.send("user@example.com", "hello");
        String out = capture.toString();
        assertTrue(out.contains("[EMAIL] to user@example.com :: hello"));
    }

    @Test
    void smsSend() {
        NotificationChannel ch = new SmsChannel();
        ch.send("+1-555-0101", "otp:1234");
        String out = capture.toString();
        assertTrue(out.contains("[SMS] to +1-555-0101 :: otp:1234"));
    }

    @Test
    void pushSend() {
        NotificationChannel ch = new PushChannel();
        ch.send("device_abc", "ping");
        String out = capture.toString();
        assertTrue(out.contains("[PUSH] to device_abc :: ping"));
    }

    @Test
    void onlineNotification() {
        NotificationChannel email = new EmailChannel();
        PaymentNotification n = new OnlinePaymentNotification(email, "alice@example.com", 129.99, "Credit Card");
        n.notifyCustomer();
        String out = capture.toString();
        assertTrue(out.contains("[EMAIL] to alice@example.com"));
        assertTrue(out.contains("Online payment of $129.99 received via Credit Card. Thank you!"));
    }

    @Test
    void codNotification() {
        NotificationChannel sms = new SmsChannel();
        PaymentNotification n = new CashOnDeliveryPayment(sms, "+1-555-0101", 89.00);
        n.notifyCustomer();
        String out = capture.toString();
        assertTrue(out.contains("[SMS] to +1-555-0101"));
        assertTrue(out.contains("Cash on Delivery: Please have $89.00 ready upon arrival."));
    }

    @Test
    void bitcoinNotification() {
        NotificationChannel push = new PushChannel();
        PaymentNotification n = new BitcoinPayment(push, "dev_device", 15.49, "tx_abc");
        n.notifyCustomer();
        String out = capture.toString();
        assertTrue(out.contains("[PUSH] to dev_device"));
        assertTrue(out.contains("Bitcoin payment of $15.49 confirmed. TxId=tx_abc"));
    }
}
