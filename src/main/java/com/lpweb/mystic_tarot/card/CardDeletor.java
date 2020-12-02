package com.lpweb.mystic_tarot.card;

import java.util.Scanner;

import com.lpweb.mystic_tarot.MysticTarot;

/**
 * The CardDeletor class provides a console interface to delete a card.
 */
public class CardDeletor {
    public CardDeletor() {};

    /**
     * Asks the card number to delete and delete the matching card.
     */
    public void deleteCard() {
        System.out.println("---- Card Deletor ----");
        CardManager cardManager = CardManager.getInstance();
        
        // No card
        if (cardManager.getCards().size() == 0) {
            System.out.println("No card to delete.");
            return;
        }

        // Display all cards
        cardManager.displayCards();

        Scanner input = MysticTarot.getScanner();
        Integer cardNumber;
        // Ask card number while invalid
        while (true) {
            System.out.print("Card number: ");
            // Number must be an integer and exist
            try {
                cardNumber = Integer.parseInt(input.nextLine());
                cardManager.removeCardByNumber(cardNumber);
                break;
            } catch (NumberFormatException e) {
                System.err.println("Number must be an integer.");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
