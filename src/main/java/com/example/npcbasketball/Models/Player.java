package com.example.npcbasketball.Models;

import java.io.Serializable;

public class Player extends BasketballPerson implements Serializable {
    // Player-specific properties
    private String position;
    private double height;
    private int number; // Jersey Number

    // Constructor to initialize Player object
    public Player(String name, int age, String email, String position, int height, int number) {
        // Call constructor of superclass (BasketballPerson)
        super(name, age, email);

        // Initialize Player-specific properties
        this.position = position;
        this.height = height;
        this.number = number;
    }

    // Getter and Setter methods for position
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    // Getter and Setter methods for height
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    // Getter and Setter methods for number (Jersey Number)
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    // Override toString method to provide a formatted string representation of Player
    @Override
    public String toString() {
        return getName() + ": " + getAge() + ": " + getEmail() + ": " + getPosition() + ": " + getHeight() + ": " + getNumber();
    }
}
