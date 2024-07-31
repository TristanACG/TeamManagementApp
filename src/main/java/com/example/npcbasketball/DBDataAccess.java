package com.example.npcbasketball;

import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import com.example.npcbasketball.Models.Player;
import com.example.npcbasketball.Models.SharedDataModel;
import javafx.application.Platform;


public class DBDataAccess
{
    // Boolean flag to track if the database access thread has completed
    public static volatile boolean threadCompleted = false;

    // JDBC URL, username, and password for the database connection
    String jdbcURL = "jdbc:mysql://localhost:/testdb";
    String username = "root";
    String password = ""; //

    // Method to persist data from an ArrayList of Player objects to MySQL database
    public void persist(ArrayList<Object> players) throws SQLException
    {
        if (players.get(0) instanceof Player) {
            ArrayList<Player> _players = new ArrayList<Player>();
            // Convert 'objects' to 'checking'
            for (Object obj : players) {
                if (obj instanceof Player) {
                    _players.add((Player) obj);
                }
            }

            // Define the SQL INSERT statement to insert player data into the 'basketball' table
            String insertSQL = "INSERT INTO basketball (name, number, email, height) VALUES (?, ?, ?, ?)";

            try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
                // Create a PreparedStatement to execute the SQL INSERT statement
                PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);

                Player player = null;
                for (Object obj : players) {
                    if (obj instanceof Player) {
                        player = (Player) obj;
                    }

                    // Set values for the parameters in the PreparedStatement
                    preparedStatement.setString(1, player.getName());
                    preparedStatement.setInt(2, player.getNumber());
                    preparedStatement.setString(3, player.getEmail());
                    preparedStatement.setDouble(4, player.getHeight());

                    // Execute the PreparedStatement to insert the player data into the database
                    preparedStatement.executeUpdate();
                } // End of for loop
            } catch (SQLException e) {
                // Handle any SQL-related exceptions that may occur
                throw new RuntimeException(e);
            }

            // Simulate a delay to show progress (you can replace this with actual database operations)
            for (int count = 0; count < 100; count++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // Update the progress percentage in the SharedDataModel
                SharedDataModel.getInstance().setPercentCompleted(count);
            }
            // Set the thread completion flag in the SharedDataModel
            SharedDataModel.getInstance().setThreadCompleted(true);
        }
    }

}



