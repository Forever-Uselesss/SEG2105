public class Logger implements EventHandler {
    public void handle(Event event) {
        System.out.println("[LOG] " + event);
    }
}
