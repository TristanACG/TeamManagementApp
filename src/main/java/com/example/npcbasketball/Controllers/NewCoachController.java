package com.example.npcbasketball.Controllers;

import com.example.npcbasketball.Models.Coach;
import com.example.npcbasketball.Models.SharedDataModel;
import com.example.npcbasketball.NPCBasketballMain;
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

public class NewCoachController {
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfAge;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfCoachPos;
    @FXML
    private TextField tfYOE;
    @FXML
    private TextField tfSalary;
    @FXML
    public VBox vbxTextFields;
    @FXML
    private TextField tfFileName;
    @FXML
    private ImageView ivAge;
    @FXML
    private ImageView ivEmail;
    @FXML
    private ImageView ivCoachPos;
    @FXML
    public Label lblResult;

    // Handler for the "Save Data" button click event
    @FXML
    protected void onSaveDataClick() {
        int age = 0;
        int salary = 0;
        int yoe;

        try {
            // Validate email using regular expressions
            String email = tfEmail.getText();
            String regXEmail = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern patternEmail = Pattern.compile(regXEmail);
            Matcher matcherEmail = patternEmail.matcher(email);
            if (!matcherEmail.matches()) {
                throw new InvalidEmailExceptionC("Email is Invalid");
            }

            // Validate age as a positive integer
            age = Integer.parseInt(tfAge.getText());
            if (age < 0) throw new NegativeAgeExceptionC("Age can't be Negative");

            // Validate salary as a positive integer
            salary = Integer.parseInt(tfSalary.getText());
            if (salary < 0) throw new InvalidSalaryException("Salary can't be less than 0");

            // Get years of experience
            yoe = Integer.parseInt(tfYOE.getText());

            // Create a Coach object with input data and add it to the shared data model
            Coach coach = new Coach(tfName.getText(), age, email, tfCoachPos.getText(), yoe, salary);
            SharedDataModel.getInstance().getData().add(coach);

        } catch (InvalidEmailExceptionC e) {
            // Display an error message and highlight the email field in red
            lblResult.setText(e.getMessage());
            tfEmail.getStyleClass().add("red-border");
        } catch (NegativeAgeExceptionC e) {
            // Display an error message and highlight the age field in red
            lblResult.setText(e.getMessage());
            tfAge.getStyleClass().add("red-border");
        } catch (InvalidSalaryException e) {
            // Display an error message and highlight the salary field in red
            lblResult.setText(e.getMessage());
            tfSalary.getStyleClass().add("red-border");
        }
    }

    // Event handler for clicking the age field
    public void onAgeClicked(MouseEvent mouseEvent) {
        // Clear any previous styling and error messages for the age field
        tfAge.getStyleClass().clear();
        tfAge.getStyleClass().addAll("text-field", "text-input");
        lblResult.setText("");
    }

    // Event handler for clicking the salary field
    public void onNumberClicked(MouseEvent mouseEvent) {
        // Clear any previous styling and error messages for the salary field
        tfSalary.getStyleClass().clear();
        tfSalary.getStyleClass().addAll("text-field", "text-input");
        lblResult.setText("");
    }

    // Event handler for clicking the email field
    public void onEmailClicked(MouseEvent mouseEvent) {
        // Clear any previous styling and error messages for the email field
        tfEmail.getStyleClass().clear();
        tfEmail.getStyleClass().addAll("text-field", "text-input");
        lblResult.setText("");
    }

    // Event handler for clicking the "Archive Data" button
    public void onArchiveDataClick(ActionEvent actionEvent) throws IOException {
        try {
            // Load the "ArchivePage.fxml" GUI when the Archive Data button is clicked
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

// Exception class for handling negative age input
class NegativeAgeExceptionC extends Exception {
    public NegativeAgeExceptionC(String message) {
        super(message);
    }
}

// Exception class for handling invalid salary input
class InvalidSalaryException extends Exception {
    public InvalidSalaryException(String message) {
        super(message);
    }
}

// Exception class for handling invalid email input
class InvalidEmailExceptionC extends Exception {
    public InvalidEmailExceptionC(String message) {
        super(message);
    }
}
