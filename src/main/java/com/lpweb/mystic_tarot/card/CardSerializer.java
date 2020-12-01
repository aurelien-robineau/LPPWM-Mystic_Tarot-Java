package com.lpweb.mystic_tarot.card;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CardSerializer {
    private static final String cardSavePath = "./data/cards/";

    private Card card;

    public CardSerializer(Card card) {
        this.card = card;
    }

    public void save() {
        try {
            FileOutputStream fos = new FileOutputStream(cardSavePath + this.card.number + ".serial");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            try {
                oos.writeObject(card);
                oos.flush();
            } finally {
                try {
                    oos.close();
                } finally {
                    fos.close();
                }
            }
        } catch (IOException e) {
            System.out.println("Card could not be saved.");
        }
    }

    public static ArrayList<Card> loadSavedCards() {
        File dir = new File(cardSavePath);
        File[] directoryListing = dir.listFiles();
        ArrayList<Card> loadedCards = new ArrayList<>();

        if (directoryListing != null) {
            for (File file : directoryListing) {
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

    private static String getFileExtension(String filename) {
        String[] splittedFilename = filename.split("\\.");

        for (String string : splittedFilename) {
            System.out.println(string);
        }

        if (splittedFilename.length == 0) {
            return "";
        }

        return splittedFilename[splittedFilename.length - 1];
    }
}
