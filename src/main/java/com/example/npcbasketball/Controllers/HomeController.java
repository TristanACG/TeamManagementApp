package com.example.npcbasketball.Controllers;

import com.example.npcbasketball.TeamSummary;
import com.example.npcbasketball.NPCBasketballMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HomeController {
    @FXML
    private TeamSummary teamSummaryDisplay;

    @FXML
    protected void onNewPlayerButtonClick() {
        try {
            // Load the "NewPlayer.fxml" GUI when the New Player button is clicked
            FXMLLoader fxmlLoader = new FXMLLoader(NPCBasketballMain.class.getResource("NewPlayer.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 325, 272);
            scene.getStylesheets().add(NPCBasketballMain.class.getResource("styles.css").toExternalForm());

            Stage stage = new Stage();
            stage.setTitle("New Player Entry");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onNewCoachButtonClick() {
        try {
            // Load the "NewCoach.fxml" GUI when the New Coach button is clicked
            FXMLLoader fxmlLoader = new FXMLLoader(NPCBasketballMain.class.getResource("NewCoach.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 325, 272);
            Stage stage = new Stage();
            stage.setTitle("New Coach Entry");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onNewTransactionButtonClick() {
        try {
            // Load the "TransactionPage.fxml" GUI when the New Transaction button is clicked
            FXMLLoader fxmlLoader = new FXMLLoader(NPCBasketballMain.class.getResource("TransactionPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 340, 240);
            Stage stage = new Stage();
            stage.setTitle("New Transaction");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDetailsPage(ActionEvent actionEvent) {
        try {
            // Load the "DetailsPage.fxml" GUI when the Details button is clicked
            FXMLLoader fxmlLoader = new FXMLLoader(NPCBasketballMain.class.getResource("DetailsPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 585, 200);
            Stage stage = new Stage();
            stage.setTitle("Team Details");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDisplay(MouseEvent mouseEvent) {
        // Update the team summary display when triggered by a mouse event
        teamSummaryDisplay.update();
    }
}
