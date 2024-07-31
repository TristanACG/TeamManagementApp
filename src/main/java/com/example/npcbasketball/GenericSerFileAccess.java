package com.example.npcbasketball;

import java.io.*;
import java.util.ArrayList;

// Serialize/Deserialize ArrayList to/from File
public class GenericSerFileAccess {
    // Method to persist an ArrayList of objects to a serialized file
    public void persist(ArrayList<Object> objects, String filename) {
        try (
                FileOutputStream fileOut = new FileOutputStream(filename);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)
        ) {
            // Write the ArrayList of objects to the serialized file
            objectOut.writeObject(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve an ArrayList of objects from a serialized file
    public ArrayList<Object> retrieve(String filename) {
        ArrayList<Object> objects = new ArrayList<>();
        try (
                FileInputStream fileIn = new FileInputStream(filename);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn)
        ) {
            // Read and deserialize the ArrayList of objects from the file
            objects = (ArrayList<Object>) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return objects;
    }
}
