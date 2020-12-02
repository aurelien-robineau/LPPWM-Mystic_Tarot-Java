package com.lpweb.mystic_tarot.card;

import java.util.Scanner;

import com.lpweb.mystic_tarot.MysticTarot;

public class CardCreator {
    public CardCreator() {};

    public Card createCard() {
        System.out.println("---- Card Creator ----");
        Scanner input = MysticTarot.getScanner();

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
        CardSerializer serializer = new CardSerializer(card);
        serializer.save();

        return card;
    }
}
