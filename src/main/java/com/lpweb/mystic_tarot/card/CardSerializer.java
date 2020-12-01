package com.lpweb.mystic_tarot.card;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
            e.printStackTrace();
            System.out.println("Card could not be saved.");
        }
    }
}
