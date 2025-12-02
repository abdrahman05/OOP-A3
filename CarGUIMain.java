package oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarGUIMain extends JFrame {
    private JTextField modelField, colorField, yearField, mileageField, fuelField;
    private JTextField distanceField, destinationField;
    private JComboBox<String> carTypeCombo;
    private JCheckBox turboCheck, engineCheck;
    private JTextArea outputArea;
    private Garage garage = new Garage();
    private Mechanic mechanic = new Mechanic("John Doe");
    private Car currentCar = null;

    // Constructor - the GUI
    public CarGUIMain() {
        setTitle("Car Management System - Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 248, 255));

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Create New Car"));
        inputPanel.setBackground(Color.WHITE);

        inputPanel.add(new JLabel("Model:"));
        modelField = new JTextField("Tesla Model 3");
        inputPanel.add(modelField);

        inputPanel.add(new JLabel("Color:"));
        colorField = new JTextField("Red");
        inputPanel.add(colorField);

        inputPanel.add(new JLabel("Year:"));
        yearField = new JTextField("2025");
        inputPanel.add(yearField);

        inputPanel.add(new JLabel("Mileage:"));
        mileageField = new JTextField("0");
        inputPanel.add(mileageField);

        inputPanel.add(new JLabel("Fuel/Battery %:"));
        fuelField = new JTextField("100");
        inputPanel.add(fuelField);

        inputPanel.add(new JLabel("Car Type:"));
        carTypeCombo = new JComboBox<>(new String[]{"Normal", "Electric", "Sports"});
        inputPanel.add(carTypeCombo);

        inputPanel.add(new JLabel("Trip Distance (km):"));
        distanceField = new JTextField();
        inputPanel.add(distanceField);

        inputPanel.add(new JLabel("Destination:"));
        destinationField = new JTextField("City Center");
        inputPanel.add(destinationField);

        add(inputPanel, BorderLayout.NORTH);

        // Action Buttons 
        JPanel actionPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        actionPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
        actionPanel.setBackground(Color.WHITE);

        turboCheck = new JCheckBox("Turbo Mode (Sports Car only)");
        actionPanel.add(turboCheck);

        engineCheck = new JCheckBox("Engine ON");
        engineCheck.setSelected(true);
        actionPanel.add(engineCheck);

        JButton createBtn = new JButton("Create Car");
        createBtn.setBackground(new Color(46, 204, 113));
        createBtn.setForeground(Color.WHITE);
        createBtn.addActionListener(new CreateCarListener());
        actionPanel.add(createBtn);

        JButton tripBtn = new JButton("Start Trip");
        tripBtn.setBackground(new Color(52, 152, 219));
        tripBtn.setForeground(Color.WHITE);
        tripBtn.addActionListener(new StartTripListener());
        actionPanel.add(tripBtn);

        JButton parkBtn = new JButton("Park in Garage");
        parkBtn.setBackground(new Color(155, 89, 182));
        parkBtn.setForeground(Color.WHITE);
        parkBtn.addActionListener(e -> parkCar());
        actionPanel.add(parkBtn);

        JButton serviceBtn = new JButton("Service Car");
        serviceBtn.setBackground(new Color(231, 76, 60));
        serviceBtn.setForeground(Color.WHITE);
        serviceBtn.addActionListener(e -> serviceCar());
        actionPanel.add(serviceBtn);

        JButton garageBtn = new JButton("Show Garage");
        garageBtn.setBackground(new Color(241, 196, 15));
        garageBtn.setForeground(Color.BLACK);
        garageBtn.addActionListener(e -> showGarage());
        actionPanel.add(garageBtn);

        JButton clearBtn = new JButton("Clear Output");
        clearBtn.addActionListener(e -> outputArea.setText(""));
        actionPanel.add(clearBtn);

        add(actionPanel, BorderLayout.CENTER);

        // Output Area 
        outputArea = new JTextArea(10, 50);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        outputArea.append("=== CAR MANAGEMENT SYSTEM ===\n");
        outputArea.append("Login successful. Welcome!\n\n");
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        setVisible(true);
    }

    // Listener Classes
    private class CreateCarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String model = modelField.getText().trim();
                String color = colorField.getText().trim();
                int year = Integer.parseInt(yearField.getText());
                double mileage = Double.parseDouble(mileageField.getText());
                double fuel = Double.parseDouble(fuelField.getText());
                boolean engineOn = engineCheck.isSelected();
                String license = "MSU" + (int)(Math.random()*9000+1000);

                String type = (String) carTypeCombo.getSelectedItem();

                if (type.equals("Electric")) {
                    currentCar = new ElectricCar(model, color, year, mileage, engineOn, license);
                } else if (type.equals("Sports")) {
                    currentCar = new SportsCar(model, color, year, mileage, fuel, engineOn, license);
                    if (turboCheck.isSelected()) {
                        ((SportsCar) currentCar).activateTurbo();
                    }
                } else {
                    currentCar = new Car(model, color, year, mileage, fuel, engineOn, license);
                }

                outputArea.append("CAR CREATED SUCCESSFULLY:\n");
                outputArea.append(currentCar.toString() + "\n\n");
            } catch (Exception ex) {
                outputArea.append("Error: Please check your input values.\n\n");
            }
        }
    }

    private class StartTripListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentCar == null) {
                outputArea.append("Please create a car first!\n\n");
                return;
            }
            try {
                double distance = Double.parseDouble(distanceField.getText());
                String dest = destinationField.getText();
                Trip trip = new Trip(distance, dest);
                trip.startTrip(currentCar);
                outputArea.append("Trip completed! Updated car status:\n" + currentCar + "\n\n");
            } catch (Exception ex) {
                outputArea.append("Invalid trip details.\n\n");
            }
        }
    }

    private void parkCar() {
        if (currentCar == null) {
            outputArea.append("ERROR: No car created yet!\n\n");
            return;
        }
        if (garage != null) {
            if (garage.slot1 == null) { 
                garage.addCarToSlot1(currentCar);
            } else {
                garage.addCarToSlot2(currentCar);
            }
        }
        outputArea.append("Car successfully parked!\n");
        showGarage();
    }

    private void serviceCar() {
        if (currentCar == null) {
            outputArea.append("No car to service!\n\n");
            return;
        }
        mechanic.serviceCar(currentCar);
        outputArea.append("Service completed!\n" + currentCar + "\n\n");
    }

    private void showGarage() {
        outputArea.append("=== GARAGE STATUS ===\n");
        garage.displayCars();
        outputArea.append("\n");
    }

  

}
