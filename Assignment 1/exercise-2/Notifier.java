public class Notifier implements EventHandler {
    public void handle(Event event) {
        System.out.println("[NOTIFY] user alerted about:  " + event.getName());
    }
    
}
