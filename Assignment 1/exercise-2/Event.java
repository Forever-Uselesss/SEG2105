public class Event {
    private String name;
    private int priority;

    Event() {
        name = "";
        priority = 0;
    }

    Event(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Event {name='" + getName() + "', priority=" + getPriority() + "}";
    }

}
