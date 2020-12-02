package com.lpweb.mystic_tarot.card;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * The CardSerializer class provides methods to manage Card class serialization,
 * such as saving and loading card objects.
 */
public class CardSerializer {
    /**
     * Directory the cards are saved in.
     */
    private static final String cardSavePath = "./data/cards/";

    /**
     * Card managed by the CardSerializer.
     */
    private Card card;

    /**
     * Filename of the binary card file.
     */
    private String cardFilenameBinary;

    /**
     * Filename of the JSON card file.
     */
    private String cardFilenameJSON;

    /**
     * Protected contrustor.
     * @param card the card to be managed by the CardSerializer.
     */
    protected CardSerializer(Card card) {
        this.card               = card;
        this.cardFilenameBinary = cardSavePath + this.card.number + ".serial";
        this.cardFilenameJSON   = cardSavePath + this.card.number + ".json";
    }

    //--------------------------------------------------------------------------
    // Public methods
    //--------------------------------------------------------------------------

    /**
     * Saves the card to a binary file.
     * The file name is <number_of_the_card>.serial.
     */
    public void saveCardBinary() {
        try {
            FileOutputStream fos = new FileOutputStream(this.cardFilenameBinary);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            try {
                oos.writeObject(this.card);
                oos.flush();
            } finally {
                try {
                    oos.close();
                } finally {
                    fos.close();
                }
            }
        } catch (IOException e) {
            System.err.println("Card could not be saved as a binary file.");
        }
    }

    /**
     * Deletes the serialization binary file of the card.
     */
    public void deleteCardBinary() {
        File cardFile = new File(this.cardFilenameBinary);
        if (!cardFile.delete()) {
            System.err.println("Binary card file could not be deleted.");
        }
    }

    /**
     * Saves the card to a JSON file.
     * The file name is <number_of_the_card>.json.
     */
    public void saveCardJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(this.cardFilenameJSON)) {
            gson.toJson(this.card, writer);
        } catch (Exception e) {
            System.err.println("Card could not be saved as JSON.");
        }
    }

    /**
     * Deletes the serialization JSON file of the card.
     */
    public void deleteCardJSON() {
        File cardFile = new File(this.cardFilenameJSON);
        if (!cardFile.delete()) {
            System.err.println("JSON card file could not be deleted.");
        }
    }

    //--------------------------------------------------------------------------
    // Public static methods
    //--------------------------------------------------------------------------

    /**
     * Loads all cards saved in binary files.
     * @return the loaded cards
     */
    public static ArrayList<Card> loadBinarySavedCards() {
        File directory = new File(cardSavePath);
        File[] files = directory.listFiles();
        ArrayList<Card> loadedCards = new ArrayList<>();

        // The provided directory really is a directory
        if (files != null) {
            // For each file of the directory
            for (File file : files) {
                // Try to load file if it is a card serialization binary file
                if (getFileExtension(file.toString()).equals("serial")) {
                    try {
                        FileInputStream fis = new FileInputStream(file);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        try {
                            loadedCards.add((Card) ois.readObject());
                        } finally {
                            try {
                                ois.close();
                            } finally {
                                fis.close();
                            }
                        }
                    } catch(Exception e) {
                        System.err.println("Could not load card.");
                    }
                }
            }
        }

        return loadedCards;
    }

    /**
     * Loads all cards saved in JSON files.
     * @return the loaded cards
     */
    public static ArrayList<Card> loadJSONSavedCards() {
        File directory = new File(cardSavePath);
        File[] files = directory.listFiles();
        ArrayList<Card> loadedCards = new ArrayList<>();
        Gson gson = new Gson();

        // The provided directory really is a directory
        if (files != null) {
            // For each file of the directory
            for (File file : files) {
                // Try to load file if it is a card serialization JSON file
                if (getFileExtension(file.toString()).equals("json")) {
                    try (Reader reader = new FileReader(file)) {
                        Card card = gson.fromJson(reader, Card.class);
                        loadedCards.add(card);   
                    } catch(Exception e) {
                        System.err.println("Could not load card.");
                    }
                }
            }
        }

        return loadedCards;
    }

    //--------------------------------------------------------------------------
    // Private static methods
    //--------------------------------------------------------------------------

    /**
     * Gets a file extension from a file name.
     * The method only returns the string after the last dot.
     * @param filename the name of the file
     * @return the file extension or an empty string if no extension found.
     */
    private static String getFileExtension(String filename) {
        String[] splittedFilename = filename.split("\\.");

        if (splittedFilename.length == 0) {
            return "";
        }

        return splittedFilename[splittedFilename.length - 1];
    }
}
