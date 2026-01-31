import java.time.Instant;

public class Helicopter extends AirVehicle {
    public static final double MAX_WEIGHT = 5000.0; // kg
    public static final double MAX_SPEED = 250.0; // km/h
    public static final double MIN_SPEED = (0.1) * MAX_SPEED; // km/h
    public final Instant registrationTime = Instant.now();
    private static int counter = 0;

    public Helicopter() {
        super();
        counter++;
    }

    @Override
    public double getMaxWeight() {
        return MAX_WEIGHT;
    }

    @Override
    public double getMaxSpeed() {
        return MAX_SPEED;
    }

    @Override
    public double getMinSpeed() {
        return calculateMinSpeed();
    }

    private double calculateMinSpeed() {
        return (MAX_SPEED * 0.1);
    }

    @Override
    public String getRegistrationTime() {
        return registrationTime.toString();
    }

    @Override
    public String getVehicleType() {
        return "Helicopter";
    }

    public static int count() {
        return counter;
    }
}
