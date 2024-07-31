package com.example.npcbasketball;

import com.example.npcbasketball.Models.Player;

import java.io.*;
import java.util.ArrayList;

// CSV File Access Class (to/from an ArrayList of Objects)
public class CSVFileAccess {
    // Method to persist data from an ArrayList of objects to a CSV file
    public void persist(ArrayList<Object> objects, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Loop through the objects and write them to the CSV file
            for (Object object : objects) {
                writer.write(object.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve data from a CSV file and convert it into an ArrayList of Player objects
    public ArrayList<Player> retrieve(String filename) {
        ArrayList<Player> players = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            // Read each line from the CSV file and convert it into a Player object
            while ((line = reader.readLine()) != null) {
                players.add(CSVtoPlayer(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return players;
    }

    // Method to convert a CSV line to a Player object
    Player CSVtoPlayer(String line) {
        String[] parts = line.split(",");
        if (parts.length == 7) {
            String name = parts[0].trim();
            int age = Integer.parseInt(parts[1].trim());
            String email = parts[2].trim();
            String position = parts[3].trim();
            int height = Integer.parseInt(parts[4].trim());
            int number = Integer.parseInt(parts[5].trim());
            return new Player(name, age, email, position, height, number);
        } else {
            return null;
        }
    }
}
