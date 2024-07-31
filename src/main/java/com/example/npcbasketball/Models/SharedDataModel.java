package com.example.npcbasketball.Models;

import java.util.ArrayList;

public class SharedDataModel {
    // Singleton instance of SharedDataModel
    private static SharedDataModel instance = new SharedDataModel();

    // List to store shared data (e.g., players, coaches, etc.)
    private ArrayList<Object> data = new ArrayList<Object>();

    // List to store transaction data
    private ArrayList<Object> transactions = new ArrayList<Object>();

    // Indicates whether a background thread has completed its task
    private boolean isThreadCompleted;

    // Percentage completed of a background task
    private int percentCompleted;

    // Private constructor to ensure Singleton pattern
    private SharedDataModel() {

    }


    // Get the Singleton instance of SharedDataModel
    public static SharedDataModel getInstance() {
        return instance;
    }

    // Getter and Setter methods for data
    public ArrayList<Object> getData() {
        return data;
    }

    public void setData(ArrayList<Object> data) {
        this.data = data;
    }

    // Getter and Setter methods for isThreadCompleted
    public boolean isThreadCompleted() {
        return isThreadCompleted;
    }

    public void setThreadCompleted(boolean threadCompleted) {
        isThreadCompleted = threadCompleted;
    }

    // Getter and Setter methods for percentCompleted
    public int getPercentCompleted() {
        return percentCompleted;
    }

    public void setPercentCompleted(int percentCompleted) {
        this.percentCompleted = percentCompleted;
    }

    // Getter and Setter methods for transactions
    public ArrayList<Object> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Object> transactions) {
        this.transactions = transactions;
    }
}
