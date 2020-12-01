package com.lpweb.mystic_tarot.card;

import java.util.Scanner;

public class CardCreator {
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

        System.out.print("Description: ");
        String description = input.nextLine();

        input.close();

        return new Card(number, description);
    }
}
