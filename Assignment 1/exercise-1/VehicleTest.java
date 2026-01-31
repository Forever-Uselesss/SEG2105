
public class VehicleTest {

    public static void main(String[] args) {

        // --- Create and configure multiple good objects ---
        Bike[] bikes = {
            new Bike(), new Bike(), new Bike()
        };
        bikes[0].setEmptyWeight(12.0);
        bikes[0].setCruiseSpeed(20.0);

        bikes[1].setEmptyWeight(15.0);
        bikes[1].setCruiseSpeed(25.0);

        bikes[2].setEmptyWeight(18.0);
        bikes[2].setCruiseSpeed(30.0);

        Car[] cars = {
            new Car(), new Car()
        };
        cars[0].setEmptyWeight(1300.0);
        cars[0].setCruiseSpeed(110.0);

        cars[1].setEmptyWeight(1400.0);
        cars[1].setCruiseSpeed(150.0);

        Plane[] planes = {
            new Plane(), new Plane()
        };
        planes[0].setEmptyWeight(55000.0);
        planes[0].setCruiseSpeed(760.0);

        planes[1].setEmptyWeight(60000.0);
        planes[1].setCruiseSpeed(850.0);

        Helicopter[] helicopters = {
            new Helicopter(), new Helicopter()
        };
        helicopters[0].setEmptyWeight(4500.0);
        helicopters[0].setCruiseSpeed(210.0);

        helicopters[1].setEmptyWeight(3000.0);
        helicopters[1].setCruiseSpeed(220.0);

        // --- Error cases ---
        System.out.println("\n--- Testing error cases ---");

        try {
            Bike badBike = new Bike();
            badBike.setEmptyWeight(-1);
        } catch (IllegalArgumentException e) {
            System.err.println("Case 1: " + e);
        }

        try {
            Car badCar = new Car();
            badCar.setCruiseSpeed(badCar.getMaxSpeed() + 1);
        } catch (IllegalArgumentException e) {
            System.err.println("Case 2: " + e);
        }

        try {
            Plane badPlane = new Plane();
            badPlane.setEmptyWeight(100000); // > max
        } catch (IllegalArgumentException e) {
            System.err.println("Case 3: " + e);
        }

        try {
            Helicopter badHeli = new Helicopter();
            badHeli.setCruiseSpeed(-5); // invalid
        } catch (IllegalArgumentException e) {
            System.err.println("Case 4: " + e);
        }

        // --- Test count ---
        System.out.println("\n--- Testing count() ---");
        System.out.println("Number of Bike instances: " + Bike.count());
        System.out.println("Number of Car instances: " + Car.count());
        System.out.println("Number of Plane instances: " + Plane.count());
        System.out.println("Number of Helicopter instances: " + Helicopter.count());

        // --- Test dump (just the first object of each type for brevity) ---
        System.out.println("\n--- Testing dump() ---");
        bikes[0].dump();
        System.out.println();
        cars[0].dump();
        System.out.println();
        planes[0].dump();
        System.out.println();
        helicopters[0].dump();

        // --- Test operate (again just first object of each type) ---
        System.out.println("\n--- Testing operate() ---");
        bikes[0].operate();
        System.out.println();
        cars[0].operate();
        System.out.println();
        planes[0].operate();
        System.out.println();
        helicopters[0].operate();
    }
}
