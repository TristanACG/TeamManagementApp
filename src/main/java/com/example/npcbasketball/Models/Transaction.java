package com.example.npcbasketball.Models;

import java.time.LocalDate;

public class Transaction {
    private TransactionType type;      // Type of transaction (e.g., JERSEY or POSITION)
    private int number;                // Number associated with the transaction (e.g., jersey number)
    private LocalDate date;            // Date when the transaction occurred
    private String playerPosition;     // Position of the player involved in the transaction
    private String playerName;         // Name of the player involved in the transaction

    // Constructor to initialize a Transaction object
    public Transaction(TransactionType type, int number, LocalDate date, String playerPosition, String playerName) {
        this.type = type;
        this.number = number;
        this.date = date;
        this.playerPosition = playerPosition;
        this.playerName = playerName;
    }

    // Getter and Setter methods for the properties
    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(String position) {
        this.playerPosition = position;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }
}
