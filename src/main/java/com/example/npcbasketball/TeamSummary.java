package com.example.npcbasketball;

import com.example.npcbasketball.Models.SharedDataModel;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

// UI component displays the count of players in the team
public class TeamSummary extends StackPane
{
    private int count;  // Variable to store the count of players.
    Label label;        // Label to display the player count.

    // Constructor for initializing the TeamSummary component.
    public TeamSummary()
    {
        label = new Label(); // Create a label for displaying the player count.
        count = SharedDataModel.getInstance().getData().size(); // Initialize count with the initial number of players.
        label.setText("Players: " + count); // Set the label's text to display the player count.
        getChildren().add(label); // Add the label to the StackPane.
    }

    // Method to update the player count displayed in the TeamSummary component.
    public void update()
    {
        count = SharedDataModel.getInstance().getData().size(); // Update the count by querying the data model.
        label.setText("Players: " + count); // Update the label's text to display the updated player count.
    }
}
