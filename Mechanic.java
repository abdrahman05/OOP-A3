package oop;

public class Mechanic {
    private String name;

    public Mechanic(String name) {
        this.name = name;
    }

    public void serviceCar(Car car) {
        System.out.println(name + " is servicing the car.");
        car.refuel(); // Polymorphism here
        System.out.println("Service complete.");
    }
}