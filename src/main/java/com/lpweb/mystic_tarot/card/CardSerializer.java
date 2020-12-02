package com.lpweb.mystic_tarot.card;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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

    private String cardFilename;

    /**
     * Public contrustor.
     * @param card the card to be managed by the CardSerializer.
     */
    public CardSerializer(Card card) {
        this.card = card;
        this.cardFilename = cardSavePath + this.card.number + ".serial";
    }

    //--------------------------------------------------------------------------
    // Public methods
    //--------------------------------------------------------------------------

    /**
     * Saves the card to a file.
     * The file name is <number_of_the_card>.serial.
     */
    public void save() {
        try {
            FileOutputStream fos = new FileOutputStream(this.cardFilename);
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
            System.err.println("Card could not be saved.");
        }
    }

    /**
     * Deletes the serialization file of the card.
     */
    public void delete() {
        File cardFile = new File(this.cardFilename);
        if (!cardFile.delete()) {
            System.err.println("Card file could not be deleted.");
        }
    }

    //--------------------------------------------------------------------------
    // Public static methods
    //--------------------------------------------------------------------------

    /**
     * Loads all cards saved in files.
     * @return the loaded cards
     */
    public static ArrayList<Card> loadSavedCards() {
        File directory = new File(cardSavePath);
        File[] files = directory.listFiles();
        ArrayList<Card> loadedCards = new ArrayList<>();

        // The provided directory really is a directory
        if (files != null) {
            // For each file of the directory
            for (File file : files) {
                // Try to load file if it is a card serialization file
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
