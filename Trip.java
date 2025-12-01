package oop;

public class Trip {
    private double distance;
    private String destination;

    public Trip(double distance, String destination) {
        this.distance = distance;
        this.destination = destination;
    }

    public void startTrip(Car car) {
        if (car.calculateRemainingDistance() < distance) {
            System.out.println("Not enough fuel/battery for trip.");
            return;
        }
        car.setMileage(car.getMileage() + distance);
        car.setFuelPercentage(car.getFuelPercentage() - (distance / 100)); // Simple consumption
        System.out.println("Trip to " + destination + " completed. New mileage: " + car.getMileage());
    }
}
