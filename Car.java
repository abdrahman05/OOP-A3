package oop;

public class Car {
    private String model;
    private String color;
    private int year;
    private double mileage;
    private double fuelPercentage;
    private boolean engineOn;
    private String license;

    // Default constructor
    public Car() {
        this.model = "Camry";
        this.color = "Blue";
        this.year = 2025;
        this.mileage = 0.0;
        this.fuelPercentage = 25.56;
        this.engineOn = true;
        this.license = "ABC123";
    }

    // Parameterized constructor
    public Car(String model, String color, int year, double mileage, double fuelPercentage, boolean engineOn, String license) {
        this.model = model;
        this.color = color;
        this.year = year;
        this.mileage = mileage;
        this.fuelPercentage = fuelPercentage;
        this.engineOn = engineOn;
        this.license = license;
    }

    // Getters and Setters
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public double getMileage() { return mileage; }
    public void setMileage(double mileage) { this.mileage = mileage; }
    public double getFuelPercentage() { return fuelPercentage; }
    public void setFuelPercentage(double fuelPercentage) { this.fuelPercentage = fuelPercentage; }
    public boolean isEngineOn() { return engineOn; }
    public void setEngineOn(boolean engineOn) { this.engineOn = engineOn; }
    public String getLicense() { return license; }
    public void setLicense(String license) { this.license = license; }

    public double calculateRemainingDistance() {
        return fuelPercentage * 100; // Assuming 1% fuel = 1 km
    }

    public void toggleEngine() {
        engineOn = !engineOn;
    }

    public int getCarAge() {
        return 2025 - year; // Assuming current year 2025
    }

    public void refuel() {
        System.out.println("Refueling the car with petrol.");
        fuelPercentage = 100;
    }

    @Override
    public String toString() {
    	return String.format("Car[model=%s, color=%s, year=%d, mileage=%.1fkm, fuel=%.1f%%, engine=%s, license=%s]",
                model, color, year, mileage, fuelPercentage, engineOn ? "ON" : "OFF", license);
    }
}
