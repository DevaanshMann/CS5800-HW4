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

//    @Disabled("Intentional red test (TDD): expected wrong channel tag to fail")
    @Test
    void email_send_failing() {
        NotificationChannel ch = new EmailChannel();
        ch.send("user@example.com", "hello");
        String out = capture.toString();
        // Wrong expectation on purpose
        assertTrue(out.contains("[SMS]"), "Failed: email output should not be labeled as SMS");
    }

    @Test
    void email_send_passing() {
        NotificationChannel ch = new EmailChannel();
        ch.send("user@example.com", "hello");
        String out = capture.toString();
        assertTrue(out.contains("[EMAIL] to user@example.com :: hello"));
    }

//    @Disabled("Intentional red test (TDD): wrong recipient string")
    @Test
    void sms_send_failing() {
        NotificationChannel ch = new SmsChannel();
        ch.send("+1-555-0101", "otp:1234");
        String out = capture.toString();
        // Wrong expectation on purpose
        assertTrue(out.contains("[SMS] to +1-555-9999 :: otp:1234"), " Failed: recipient is incorrect");
    }

    @Test
    void sms_send_passing() {
        NotificationChannel ch = new SmsChannel();
        ch.send("+1-555-0101", "otp:1234");
        String out = capture.toString();
        assertTrue(out.contains("[SMS] to +1-555-0101 :: otp:1234"));
    }

//    @Disabled("Intentional red test (TDD): wrong message body")
    @Test
    void push_send_failing() {
        NotificationChannel ch = new PushChannel();
        ch.send("device_abc", "ping");
        String out = capture.toString();
        // Wrong expectation on purpose
        assertTrue(out.contains("[PUSH] to device_abc :: pong"), " Failed: message is 'ping', not 'pong'");
    }

    @Test
    void push_send_passing() {
        NotificationChannel ch = new PushChannel();
        ch.send("device_abc", "ping");
        String out = capture.toString();
        assertTrue(out.contains("[PUSH] to device_abc :: ping"));
    }

//    @Disabled("Intentional red test (TDD): wrong amount formatting")
    @Test
    void online_notify_failing() {
        NotificationChannel email = new EmailChannel();
        PaymentNotification n = new OnlinePaymentNotification(email, "alice@example.com", 129.99, "Credit Card");
        n.notifyCustomer();
        String out = capture.toString();
        // Wrong expectation (integer dollars)
        assertTrue(out.contains("Online payment of $129 received via Credit Card"),
                " Failed: implementation prints 2 decimal places");
    }

    @Test
    void online_notify_passing() {
        NotificationChannel email = new EmailChannel();
        PaymentNotification n = new OnlinePaymentNotification(email, "alice@example.com", 129.99, "Credit Card");
        n.notifyCustomer();
        String out = capture.toString();
        assertTrue(out.contains("[EMAIL] to alice@example.com"));
        assertTrue(out.contains("Online payment of $129.99 received via Credit Card. Thank you!"));
    }

//    @Disabled("Intentional red test (TDD): wrong channel and phrasing")
    @Test
    void cod_notify_failing() {
        NotificationChannel sms = new SmsChannel();
        PaymentNotification n = new CashOnDeliveryPayment(sms, "+1-555-0101", 89.00);
        n.notifyCustomer();
        String out = capture.toString();
        // Wrong expectation on purpose
        assertTrue(out.contains("[EMAIL]"), " Failed: COD here uses SMS, not EMAIL");
        assertTrue(out.contains("have 89 dollars"), " Failed: message includes $89.00 with decimals");
    }

    @Test
    void cod_notify_passing() {
        NotificationChannel sms = new SmsChannel();
        PaymentNotification n = new CashOnDeliveryPayment(sms, "+1-555-0101", 89.00);
        n.notifyCustomer();
        String out = capture.toString();
        assertTrue(out.contains("[SMS] to +1-555-0101"));
        assertTrue(out.contains("Cash on Delivery: Please have $89.00 ready upon arrival."));
    }

//    @Disabled("Intentional red test (TDD): wrong tx id and channel")
    @Test
    void bitcoin_notify_failing() {
        NotificationChannel push = new PushChannel();
        PaymentNotification n = new BitcoinPayment(push, "dev_device", 15.49, "tx_abc");
        n.notifyCustomer();
        String out = capture.toString();
        // Wrong expectation on purpose
        assertTrue(out.contains("[SMS]"), " Failed: this is PUSH, not SMS");
        assertTrue(out.contains("TxId=tx_wrong"), " Failed: tx id should be tx_abc");
    }

    @Test
    void bitcoin_notify_passing() {
        NotificationChannel push = new PushChannel();
        PaymentNotification n = new BitcoinPayment(push, "dev_device", 15.49, "tx_abc");
        n.notifyCustomer();
        String out = capture.toString();
        assertTrue(out.contains("[PUSH] to dev_device"));
        assertTrue(out.contains("Bitcoin payment of $15.49 confirmed. TxId=tx_abc"));
    }
}
