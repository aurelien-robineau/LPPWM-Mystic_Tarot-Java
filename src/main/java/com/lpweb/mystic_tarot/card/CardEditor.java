package com.lpweb.mystic_tarot.card;

import java.util.Scanner;

import com.lpweb.mystic_tarot.MysticTarot;

/**
 * The CardEditor class provides a console interface to edit an existing card.
 */
public class CardEditor {
    protected CardEditor() {};

    /**
     * Asks the number of the card to edit, then the new card values.
     * @return the edited card or null if no card to edit.
     */
    public Card editCard() {
        System.out.println("---- Card Editor ----");
        Scanner input = MysticTarot.getScanner();
        CardManager cardManager = CardManager.getInstance();

        // No card
        if (cardManager.getCards().size() == 0) {
            System.out.println("No card to edit.");
            return null;
        }
        
        Card card;
        // Ask card number while invalid
        while (true) {
            System.out.print("Card number: ");
            // Number must be an integer and exist
            try {
                Integer cardNumber = Integer.parseInt(input.nextLine());
                card = cardManager.getCardByNumber(cardNumber);
                break;
            } catch (NumberFormatException e) {
                System.err.println("Number must be an integer.");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        // Ask new card number while invalid
        while (true) {
            System.out.print("New number: ");
            // New number must be an integer
            try {
                card.number = Integer.parseInt(input.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.err.println("Number must be an integer.");
            }
        }

        System.out.print("new name: ");
        card.name = input.nextLine();

        System.out.print("New description: ");
        card.description = input.nextLine();

        System.out.print("New image path: ");
        card.imagePath = input.nextLine();

        return card;
    }
}
