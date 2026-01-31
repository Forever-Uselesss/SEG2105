public class EventManagerTest {
    public static void main(String[] args) {
        // Create two events
        Event lowPriority = new Event("Database Backup", 1);
        Event highPriority = new Event("Server Down", 5);
        // Create handlers
        EventHandler logger = new Logger();
        EventHandler alerter = new Alerter();
        EventHandler notifier = new Notifier();
        // Store events and handlers in arrays
        Event[] events = { lowPriority, highPriority };
        EventHandler[] handlers = { logger, alerter, notifier };
        // Process all events with all handlers
        for (Event e : events) {
            System.out.println("--- Processing Event: " + e + " ---");
            for (EventHandler h : handlers) {
                h.handle(e);
            }
            System.out.println();
        }
    }
}