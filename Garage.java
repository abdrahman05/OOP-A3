package oop;

public class Garage {
    Car slot1 = null;
    private Car slot2 = null;

    public void addCarToSlot1(Car car) {
        slot1 = car;
        System.out.println("Car parked in SLOT 1: " + car);
    }

    public void addCarToSlot2(Car car) {
        slot2 = car;
        System.out.println("Car parked in SLOT 2: " + car);
    }

    public void displayCars(JTextArea area) {
        area.append("  SLOT 1 → " + (slot1 != null ? slot1.toString() : "EMPTY") + "\n");
        area.append("  SLOT 2 → " + (slot2 != null ? slot2.toString() : "EMPTY") + "\n");
    }


}

