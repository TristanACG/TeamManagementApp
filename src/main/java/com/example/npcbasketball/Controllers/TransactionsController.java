package com.example.npcbasketball.Controllers;

import com.example.npcbasketball.Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.Objects;

public class TransactionsController {
    // FXML elements
    @FXML
    private ComboBox transactionComboBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField numberField;
    @FXML
    private TextField positionField;
    @FXML
    private TextField playerNameField;

    // Handle the submission of a transaction
    public void submitTransaction(ActionEvent actionEvent) {
        // Get selected transaction type, date, and input values
        TransactionType transactionType = TransactionType.valueOf(transactionComboBox.getValue().toString());
        LocalDate date = datePicker.getValue();
        int number = 0;
        String position = positionField.getText();
        String playerName = playerNameField.getText();

        // Perform the appropriate action based on the selected transaction type
        switch (transactionType) {
            case JERSEY:
                for (Object obj : SharedDataModel.getInstance().getData()) {
                    Player player = (Player) obj;
                    if (Objects.equals(player.getName(), playerName)) {
                        number = Integer.parseInt(numberField.getText());
                        player.setNumber(number);
                        player.setPosition(player.getPosition());
                    }
                }
                break;
            case POSITION:
                for (Object obj : SharedDataModel.getInstance().getData()) {
                    Player player = (Player) obj;
                    if (Objects.equals(player.getName(), playerName)) {
                        player.setPosition(position);
                        player.setNumber(player.getNumber());
                        number = player.getNumber();
                    }
                }
                break;
        }

        // Add the transaction to the SharedDataModel
        SharedDataModel.getInstance().getTransactions().add(new Transaction(transactionType, number, date, position, playerName));
    }

    // Configure input fields based on the selected transaction type
    public void configureButtons(ActionEvent actionEvent) {
        if (transactionComboBox.getValue().equals("JERSEY")) {
            positionField.setDisable(true);
            numberField.setDisable(false);
        } else if (transactionComboBox.getValue().equals("POSITION")) {
            positionField.setDisable(false);
            numberField.setDisable(true);
        } else {
            positionField.setDisable(false);
            numberField.setDisable(false);
        }
    }
}
