public abstract class Vehicle implements Printable {
    private static int identity = 0;
    private int Id;
    private double emptyWeight;
    private double cruiseSpeed;

    private double MAX_WEIGHT = getMaxWeight();
    private double MAX_SPEED = getMaxSpeed();
    private double MIN_SPEED = getMinSpeed();

    Vehicle() {
        emptyWeight = (30 / 100) * MAX_WEIGHT;
        cruiseSpeed = (30 / 100) * MAX_SPEED;
        Id = identity++;
    }

    Vehicle(double weight, double speed) {
        setEmptyWeight(weight);
        setCruiseSpeed(speed);
    }

    // Abstract methods to be implemented by subclasses
    public abstract double getMaxWeight();

    public abstract double getMaxSpeed();

    public abstract double getMinSpeed();

    public abstract String getRegistrationTime();

    public abstract String getVehicleType();

    // Status methods
    public void start() {
        System.out.println(getVehicleType() + " [" + Id + "] starting");
    }

    public void move() {
        System.out.println(getVehicleType() + " [" + Id + "] moving at " + cruiseSpeed + " km/h");
    }

    public void stop() {
        System.out.println(getVehicleType() + " [" + Id + "] stopped");
    }

    // operate method
    public void operate() {
        start();
        move();
        stop();
    }

    // Implements Printable.dump()
    @Override
    public void dump() {
        System.out.println("--- " + getVehicleType() + " ---");
        System.out.println("id            : " + Id);
        System.out.println("emptyWeight   : " + emptyWeight + " kg");
        System.out.println("cruiseSpeed   : " + cruiseSpeed + " km/h");
        System.out.println("MAX_WEIGHT    : " + MAX_WEIGHT + " kg");
        System.out.println("MAX_SPEED     : " + MAX_SPEED + " km/h");
        System.out.println("MIN_SPEED     : " + MIN_SPEED + " km/h");
        System.out.println("REG_TIME      : " + getRegistrationTime());
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public double getEmptyWeight() {
        return emptyWeight;
    }

    public void setEmptyWeight(double weight) {
        if (weight > 0 && weight <= MAX_WEIGHT)
            this.emptyWeight = weight;
        else if (weight < 0)
            throw new IllegalArgumentException("emptyWeight must be > 0");
        else if (weight > MAX_WEIGHT)
            throw new IllegalArgumentException("emptyWeight must be <= MAX_WEIGHT (" + MAX_WEIGHT + ")");

    }

    public double getCruiseSpeed() {
        return cruiseSpeed;
    }

    public void setCruiseSpeed(double speed) {
        if (speed >= MIN_SPEED && speed <= MAX_SPEED)
            this.cruiseSpeed = speed;
        else if (speed < MIN_SPEED)
            throw new IllegalArgumentException("cruiseSpeed must be >= MIN_SPEED (" + MIN_SPEED + ")");
        else if (speed > MAX_SPEED)
            throw new IllegalArgumentException("cruiseSpeed must be <= MAX_SPEED (" + MAX_SPEED + ")");
    }

}
