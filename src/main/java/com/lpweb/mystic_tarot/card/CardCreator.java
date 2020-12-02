package com.lpweb.mystic_tarot.card;

import java.util.Scanner;

import com.lpweb.mystic_tarot.MysticTarot;

/**
 * The CardCreator class provides a console interface to create a new card.
 */
public class CardCreator {
    protected CardCreator() {};

    /**
     * Gets new card values from user input and create the new card.
     * @return the newly created Card.
     */
    public Card createCard() {
        System.out.println("---- Card Creator ----");
        Scanner input = MysticTarot.getScanner();

        Integer number;
        // Ask number while not valid
        while (true) {
            System.out.print("Number: ");
            // Number must be an integer
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

        return new Card(number, name, description, imagePath);
    }
}
