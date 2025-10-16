package Bridge;

public class EmailChannel implements NotificationChannel {
    @Override public void send(String to, String message) {
        System.out.println("[EMAIL] to " + to + " :: " + message);
    }
}
