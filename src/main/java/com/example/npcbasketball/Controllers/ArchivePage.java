package com.example.npcbasketball.Controllers;

import com.example.npcbasketball.CSVFileAccess;
import com.example.npcbasketball.DBDataAccess;
import com.example.npcbasketball.GenericSerFileAccess;
import com.example.npcbasketball.Models.SharedDataModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;

public class ArchivePage {
    // Enumeration to represent different file types
    enum FileType {
        CSV, SER, DB
    }

    private FileType fileType;

    @FXML
    private TextField tfFileName;
    @FXML
    private Button btnArchiveData;

    // Event handler for choosing the file type
    public void onChooseFileType(ActionEvent actionEvent) {
        RadioButton rb = (RadioButton) actionEvent.getSource();
        if (rb.getId().equals("rbCSV")) {
            fileType = FileType.CSV;
        } else if (rb.getId().equals("rbSERIAL")) {
            fileType = FileType.SER;
        } else if (rb.getId().equals("rbDB")) {
            fileType = FileType.DB;
        }
    }

    // Event handler for archiving data
    public void onArchiveData(ActionEvent actionEvent) throws ClassNotFoundException {
        // Get the list of basketball persons from the shared data model
        ArrayList<Object> basketballPersons = SharedDataModel.getInstance().getData();
        String fileName = tfFileName.getText();

        if (fileType == FileType.CSV) {
            // Archive data to a CSV file
            CSVFileAccess csvFile = new CSVFileAccess();
            csvFile.persist(basketballPersons, fileName);
        } else if (fileType == FileType.SER) {
            // Archive data using GenericSerFileAccess
            GenericSerFileAccess genFile = new GenericSerFileAccess();
            genFile.persist(basketballPersons, fileName);
        } else if (fileType == FileType.DB) {
            // Archive data to a database using a separate thread
            DBDataAccess db = new DBDataAccess();

            // Create a thread for database persistence
            Thread persistThread = new Thread(() -> {
                try {
                    db.persist(basketballPersons);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            persistThread.start();

            // Create a separate thread to check the status of the archive process
            Thread checkStatusThread = new Thread(() -> {
                while (!SharedDataModel.getInstance().isThreadCompleted()) {
                    // Update the button text to show progress percentage
                    Platform.runLater(() -> btnArchiveData.setText(Integer.toString(SharedDataModel.getInstance().getPercentCompleted())));

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (SharedDataModel.getInstance().isThreadCompleted()) {
                    // Update the button text to indicate completion
                    Platform.runLater(() -> btnArchiveData.setText("Archive Completed"));
                }
            });
            checkStatusThread.start();
        }
    }
}
