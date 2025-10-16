package Bridge;

public class PushChannel implements NotificationChannel {
    @Override public void send(String to, String message) {
        System.out.println("[PUSH] to " + to + " :: " + message);
    }
}

