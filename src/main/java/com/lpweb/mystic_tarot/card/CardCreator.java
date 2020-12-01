package com.lpweb.mystic_tarot.card;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class CardCreator {
    private static final String cardSavePath = "./data/cards/";

    public CardCreator() {};

    public Card createCard() {
        System.out.println("---- Card Creator ----");
        Scanner input = new Scanner(System.in);

        Integer number;
        while (true) {
            System.out.print("Number: ");
            try {
                number = Integer.parseInt(input.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.err.println("Number must be an integer.");
            }
        }

        System.out.print("Name: ");
        String name = input.nextLine();

        System.out.print("Description: ");
        String description = input.nextLine();

        System.out.print("Image path: ");
        String imagePath = input.nextLine();

        Card card = new Card(number, name, description, imagePath);
        this.saveCard(card);

        return card;
    }

    private void saveCard(Card card) {
        try {
            FileOutputStream fos = new FileOutputStream(cardSavePath + card.number + ".serial");
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
