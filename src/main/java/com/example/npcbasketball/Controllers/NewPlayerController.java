package com.example.npcbasketball.Controllers;

import com.example.npcbasketball.Models.SharedDataModel;
import com.example.npcbasketball.NPCBasketballMain;
import com.example.npcbasketball.Models.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewPlayerController {
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfAge;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPosition;
    @FXML
    private TextField tfHeight;
    @FXML
    private TextField tfNumber;
    @FXML
    public VBox vbxTextFields;
    @FXML
    private TextField tfFileName;
    @FXML
    private ImageView ivAge;
    @FXML
    private ImageView ivEmail;
    @FXML
    private ImageView ivPosition;
    @FXML
    public Label lblResult;

    @FXML
    protected void onSaveDataClick() {
        // Initialize variables to store input values
        int age = 0;
        int jNumber = 0;
        int height;

        try {
            // Validate email using regular expression
            String email = tfEmail.getText();
            String regXEmail = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern patternEmail = Pattern.compile(regXEmail);
            Matcher matcherEmail = patternEmail.matcher(email);
            if (!matcherEmail.matches()) {
                throw new InvalidEmailException("Email is Invalid");
            }

            // Validate age to ensure it's not negative
            age = Integer.parseInt(tfAge.getText());
            if (age < 0) throw new NegativeAgeException("Age can't be Negative");

            // Validate Jersey number to ensure it's within a valid range
            jNumber = Integer.parseInt(tfNumber.getText());
            if (jNumber < 0 || jNumber > 60) throw new InvalidJerseyNumberException("Jersey Number can't be less than 0 or Greater than 60");

            height = Integer.parseInt(tfHeight.getText());

            // Create a new Player object and add it to the shared data model
            Player player = new Player(tfName.getText(), age, email, tfPosition.getText(), height, jNumber);
            SharedDataModel.getInstance().getData().add(player);

        } catch (InvalidEmailException e) {
            lblResult.setText(e.getMessage());
            tfEmail.getStyleClass().add("red-border");
        } catch (NegativeAgeException e) {
            lblResult.setText(e.getMessage());
            tfAge.getStyleClass().add("red-border");
        } catch (InvalidJerseyNumberException e) {
            lblResult.setText(e.getMessage());
            tfNumber.getStyleClass().add("red-border");
        }
    }

    // Event handler for the "Age" field click
    public void onAgeClicked(MouseEvent mouseEvent) {
        // Clear any previous styling and error messages for the "Age" field
        tfAge.getStyleClass().clear();
        tfAge.getStyleClass().addAll("text-field", "text-input");
        lblResult.setText("");
    }

    // Event handler for the "Number" field click
    public void onNumberClicked(MouseEvent mouseEvent) {
        // Clear any previous styling and error messages for the "Number" field
        tfNumber.getStyleClass().clear();
        tfNumber.getStyleClass().addAll("text-field", "text-input");
        lblResult.setText("");
    }

    // Event handler for the "Email" field click
    public void onEmailClicked(MouseEvent mouseEvent) {
        // Clear any previous styling and error messages for the "Email" field
        tfEmail.getStyleClass().clear();
        tfEmail.getStyleClass().addAll("text-field", "text-input");
        lblResult.setText("");
    }

    // Event handler for clicking the "Archive Data" button
    public void onArchiveDataClick(ActionEvent actionEvent) throws IOException {
        try {
            // Load the ArchivePage.fxml and open a new window for archiving data
            FXMLLoader fxmlLoader = new FXMLLoader(NPCBasketballMain.class.getResource("ArchivePage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 430, 288);
            Stage stage = new Stage();
            stage.setTitle("Archive Data");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// Exception class for negative age validation
class NegativeAgeException extends Exception {
    public NegativeAgeException(String message) {
        super(message);
    }
}

// Exception class for invalid jersey number validation
class InvalidJerseyNumberException extends Exception {
    public InvalidJerseyNumberException(String message) {
        super(message);
    }
}

// Exception class for invalid email validation
class InvalidEmailException extends Exception {
    public InvalidEmailException(String message) {
        super(message);
    }
}
