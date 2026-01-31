public class Alerter implements EventHandler {
    public void handle(Event event) {
        if (event.getPriority() > 3) {
            System.out.println("[ALERT] CRITICAL: " + event.getName());
        } else {
            System.out.println("[ALERT] " + event.getName());
        }
    }
}
