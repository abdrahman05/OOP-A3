package oop;

public class SportsCar extends Car {
    private int topSpeed = 300;
    private boolean turboMode = false;

    public SportsCar(String model, String color, int year, double mileage, double fuelPercentage, 
                     boolean engineOn, String license) {
        super(model, color, year, mileage, fuelPercentage, engineOn, license);
    }

    public void activateTurbo() {
        turboMode = true;
        System.out.println("TURBO MODE ACTIVATED!");
    }

    @Override
    public void refuel() {
        System.out.println("Refueling with high-octane racing fuel");
        setFuelPercentage(100);
    }

    @Override
    public String toString() {
        return "Sports" + super.toString().replace("Car[", "") + 
               String.format(", topSpeed=%dkm/h, turbo=%s]", topSpeed, turboMode ? "ON" : "OFF");
    }
}