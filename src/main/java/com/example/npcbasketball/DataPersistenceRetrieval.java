package com.example.npcbasketball;
import java.util.ArrayList;

// An interface representing data persistence and retrieval operations.
public interface DataPersistenceRetrieval
{
    // Method to persist a list of objects to a file with the given filename.
    void persist(ArrayList<Object> list, String fileName);

    // Method to retrieve a list of objects from a file with the given filename.
    ArrayList<Object> retrieve(String fileName);
}


