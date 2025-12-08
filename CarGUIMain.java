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

    public CarGUIMain() {
        setTitle("Car Management System - Dashboard");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 245, 245));

        //Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Create New Car"));
        inputPanel.setBackground(Color.WHITE);

        inputPanel.add(new JLabel("Model:"));           
        modelField = new JTextField(); 
        inputPanel.add(modelField);
        
        inputPanel.add(new JLabel("Color:"));           
        colorField = new JTextField(); 
        inputPanel.add(colorField);
        
        inputPanel.add(new JLabel("Year:"));            
        yearField = new JTextField("2025"); 
        inputPanel.add(yearField);
        
        inputPanel.add(new JLabel("Mileage (km):"));    
        mileageField = new JTextField("0"); 
        inputPanel.add(mileageField);
        
        inputPanel.add(new JLabel("Fuel/Battery (%):"));
        fuelField = new JTextField("80"); 
        inputPanel.add(fuelField);
        
        inputPanel.add(new JLabel("Car Type:"));        
        carTypeCombo = new JComboBox<>(new String[]{"Normal", "Electric", "Sports"});
        inputPanel.add(carTypeCombo);
        
        inputPanel.add(new JLabel("Trip Distance:"));   
        distanceField = new JTextField(); 
        inputPanel.add(distanceField);
        
        inputPanel.add(new JLabel("Destination:"));     
        destinationField = new JTextField(); 
        inputPanel.add(destinationField);

        add(inputPanel, BorderLayout.NORTH);

        //Buttons
        JPanel actionPanel = new JPanel(new GridLayout(4, 2, 15, 15));
        actionPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
        actionPanel.setBackground(Color.WHITE);

        turboCheck = new JCheckBox("Turbo Mode (Sports only)");
        engineCheck = new JCheckBox("Engine ON"); engineCheck.setSelected(true);
        actionPanel.add(turboCheck);
        actionPanel.add(engineCheck);

        JButton createBtn = createButton("Create Car", new Color(46, 204, 113), e -> createCar());
        JButton tripBtn = createButton("Start Trip", new Color(52, 152, 219), e -> startTrip());
        JButton parkBtn = createButton("Park in Garage", new Color(155, 89, 182), e -> parkCar());
        JButton serviceBtn = createButton("Service Car (Refuel)", new Color(231, 76, 60), e -> serviceCarWithRefuelOutput());
        JButton garageBtn = createButton("Show Garage", new Color(241, 196, 15), e -> showGarage());
        JButton clearBtn = createButton("Clear Output", new Color(149, 165, 166), e -> outputArea.setText(""));

        actionPanel.add(createBtn); actionPanel.add(tripBtn);
        actionPanel.add(parkBtn); actionPanel.add(serviceBtn);
        actionPanel.add(garageBtn); actionPanel.add(clearBtn);

        add(actionPanel, BorderLayout.CENTER);

        //Output Area
        outputArea = new JTextArea(12, 60);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(30, 30, 30));
        outputArea.setForeground(Color.WHITE);
        outputArea.append("=== CAR MANAGEMENT SYSTEM ===\n");
        outputArea.append("Login successful! Welcome to the dashboard.\n\n");
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JButton createButton(String text, Color bg, ActionListener listener) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.addActionListener(listener);
        return btn;
    }

    private void createCar() {
        try {
            String model = modelField.getText().trim();
            String color = colorField.getText().trim();
            int year = Integer.parseInt(yearField.getText());
            double mileage = Double.parseDouble(mileageField.getText());
            double fuel = Double.parseDouble(fuelField.getText());
            boolean engineOn = engineCheck.isSelected();
            String license = "MSU" + (int)(Math.random()*8999 + 1000);

            String type = (String) carTypeCombo.getSelectedItem();

            if (type.equals("Electric")) {
                currentCar = new ElectricCar(model, color, year, mileage, engineOn, license);
            } else if (type.equals("Sports")) {
                currentCar = new SportsCar(model, color, year, mileage, fuel, engineOn, license);
                if (turboCheck.isSelected()) ((SportsCar) currentCar).activateTurbo();
            } else {
                currentCar = new Car(model, color, year, mileage, fuel, engineOn, license);
            }

            outputArea.append("CAR CREATED SUCCESSFULLY!\n");
            outputArea.append(currentCar + "\n");
            outputArea.append("────────────────────────────────────\n\n");
        } catch (Exception ex) {
            outputArea.append("ERROR: Invalid input! Check your values.\n\n");
        }
    }

    private void startTrip() {
        if (currentCar == null) { outputArea.append("No car created yet!\n\n"); return; }
        try {
            double dist = Double.parseDouble(distanceField.getText());
            String dest = destinationField.getText().trim();
            if (dest.isEmpty()) dest = "Unknown Destination";
            Trip trip = new Trip(dist, dest);
            trip.startTrip(currentCar);
            outputArea.append("TRIP COMPLETED: " + dest + " (" + dist + " km)\n");
            outputArea.append("Updated status: " + currentCar + "\n");
            outputArea.append("────────────────────────────────────\n\n");
        } catch (Exception ex) {
            outputArea.append("Invalid trip data for trip!\n\n");
        }
    }

    private void parkCar() {
        if (currentCar == null) { outputArea.append("No car to park!\n\n"); return; }
        if (garage.slot1 == null) {
            garage.addCarToSlot1(currentCar);
        } else {
            garage.addCarToSlot2(currentCar);
        }
        outputArea.append("Car parked successfully!\n");
        showGarage();
    }

    
    private void serviceCarWithRefuelOutput() {
        if (currentCar == null) {
            outputArea.append("No car to service!\n\n");
            return;
        }


        // polymorism can be seen here
        if (currentCar instanceof ElectricCar) {
            outputArea.append("CHARGING THE ELECTRIC CAR BATTERY TO 100%\n");
        } else if (currentCar instanceof SportsCar) {
            outputArea.append("REFUELING WITH HIGH-OCTANE RACING FUEL\n");
        } else {
            outputArea.append("REFUELING WITH REGULAR PETROL\n");
        }

        mechanic.serviceCar(currentCar);  
        currentCar.setFuelPercentage(100.0); 

        outputArea.append("SERVICE COMPLETED! Fuel/Battery = 100%\n");
        outputArea.append("Updated car: " + currentCar + "\n");
        outputArea.append("────────────────────────────────────\n\n");
    }

    private void showGarage() {
        outputArea.append("GARAGE STATUS\n");
        garage.displayCars(outputArea);  
        outputArea.append("────────────────────────────────────\n\n");
    }

    
}

