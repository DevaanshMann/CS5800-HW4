package Bridge;

public class SmsChannel implements NotificationChannel {
    @Override public void send(String to, String message) {
        System.out.println("[SMS] to " + to + " :: " + message);
    }
}
