package com.example.npcbasketball;

import com.example.npcbasketball.Models.Player;
import com.example.npcbasketball.Models.SharedDataModel;
import com.example.npcbasketball.Models.Transaction;
import com.example.npcbasketball.Models.TransactionType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NPCBasketballMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Load the GUI from the "home-view.fxml" file and create a scene
        FXMLLoader fxmlLoader = new FXMLLoader(NPCBasketballMain.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("NPC Basketball"); // Set the stage title
        stage.setScene(scene); // Set the scene for the stage
        stage.show(); // Display the stage
    }

    public static void main(String[] args) {
        // Load player data, changes, and apply changes, then launch the application
        loadPlayers("Player_Data.csv");
        loadChanges("team_Changes.csv");
        applyChanges();
        launch(); // Start the JavaFX application
    }

    private static void loadPlayers(String filePath) {
        try {
            // Read player data from a CSV file
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // Create Player objects and add them to the shared data model
                Player player = new Player(data[0], Integer.parseInt(data[1]), data[2], data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5]));
                SharedDataModel.getInstance().getData().add(player);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadChanges(String filePath) {
        try {
            // Read transaction data from a CSV file
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // Create Transaction objects and add them to the shared data model
                Transaction transaction = new Transaction(TransactionType.valueOf(data[0]), Integer.parseInt(data[1]), LocalDate.parse(data[2], formatter), data[3], data[4]);
                SharedDataModel.getInstance().getTransactions().add(transaction);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void applyChanges() {
        // Apply all changes to the team
        for (Object object : SharedDataModel.getInstance().getTransactions()) {
            boolean transactionComplete = false;
            Transaction transaction = (Transaction) object;

            switch (transaction.getType()) {
                case JERSEY:
                    // Find the target name in the players list and change the jersey number
                    for (Object objA : SharedDataModel.getInstance().getData()) {
                        Player player = (Player) objA;
                        if (player.getName().equals(transaction.getPlayerName())) {
                            player.setNumber((int) transaction.getNumber());
                            break;
                        }
                    }
                    break;
                case POSITION:
                    // Find the target name in the players list and change the player's position
                    for (Object objA : SharedDataModel.getInstance().getData()) {
                        Player player = (Player) objA;
                        if (player.getName().equals(transaction.getPlayerName())) {
                            player.setPosition(transaction.getPlayerPosition());
                            break;
                        }
                    }
                    break;
            }
        }
    }
}
