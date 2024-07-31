package com.example.npcbasketball.Controllers;

import com.example.npcbasketball.Models.Player;
import com.example.npcbasketball.Models.SharedDataModel;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class DetailsPageController {
    @FXML
    public VBox focusedPlayer;
    @FXML
    public Label lblAverage;
    @FXML
    private ComboBox<String> playerComboBox;
    @FXML
    private Label playerNameLabel;
    @FXML
    private Label playerNumberLabel;
    @FXML
    private Label playerPositionLabel;
    @FXML
    TextField tfFilter;

    private ObservableList<String> players = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Initialize the controller
        // Populate the playerComboBox with player names and sort them alphabetically
        for (Object obj : SharedDataModel.getInstance().getData()) {
            Player player = (Player) obj;
            players.add(player.getName());
        }
        Collections.sort(players);
        playerComboBox.setItems(players);
    }

    @FXML
    private void handlePlayerSelection() {
        // Handle the selection of a player from the ComboBox
        String playerName = playerComboBox.getSelectionModel().getSelectedItem();

        // Find and display the details of the selected player
        for (Object obj : SharedDataModel.getInstance().getData()) {
            Player player = (Player) obj;
            if (player.getName().equals(playerName)) {
                playerNameLabel.setText(player.getName());
                playerNumberLabel.setText('#' + String.valueOf(player.getNumber()));
                playerPositionLabel.setText(player.getPosition());
            }
        }
    }

    public void updateFilter(KeyEvent keyEvent) {
        // Update the playerComboBox with filtered player names
        String filter = tfFilter.getText();
        playerComboBox.getItems().clear();
        players.clear();
        for (Object obj : SharedDataModel.getInstance().getData()) {
            Player player = (Player) obj;
            if (player.getName().startsWith(filter)) players.add(player.getName());
        }
        Collections.sort(players);
        playerComboBox.setItems(players);
        playerComboBox.setPromptText("... Filtered ...");
    }
}
