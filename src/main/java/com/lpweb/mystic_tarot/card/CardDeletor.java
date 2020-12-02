package com.lpweb.mystic_tarot.card;

import com.lpweb.mystic_tarot.UserInput;

/**
 * The CardDeletor class provides a console interface to delete a card.
 */
public class CardDeletor {
    protected CardDeletor() {};

    /**
     * Asks the card number to delete and delete the matching card.
     * @return the deleted card or null if no card to delete.
     */
    public Card deleteCard() {
        UserInput input = new UserInput();
        CardManager cardManager = CardManager.getInstance();

        System.out.println("---- Card Deletor ----");
        
        // No card
        if (cardManager.getCards().size() == 0) {
            System.out.println("No card to delete.");
            return null;
        }

        // Display all cards
        cardManager.displayCards();

        // Ask card number while invalid
        Integer cardNumber = input.getCardNumber("Card number");
        try {
            return cardManager.removeCardByNumber(cardNumber);
        }
        catch (Exception e) {
            return null;
        }
    }
}
