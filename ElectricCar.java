package oop;

public class ElectricCar extends Car {
    private double batteryLevel = 100.0;
    private int chargingTime = 4;

    public ElectricCar(String model, String color, int year, double mileage, boolean engineOn, String license) {
        super(model, color, year, mileage, 0.0, engineOn, license); 
    }

    @Override
    public void refuel() {
        System.out.println("Charging the electric car's battery to 100%");
        
    }

    @Override
    public String toString() {
        return "Electric" + super.toString().replace("Car[", "") + ", battery=100%]";
    }

}
