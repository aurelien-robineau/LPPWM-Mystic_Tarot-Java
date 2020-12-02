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
        
        if (cardManager.getCards().size() == 0) {
            System.out.println("No card to delete.");
            return null;
        }

        cardManager.displayCards();

        Integer cardNumber = input.getCardNumber("Card number");
        
        return cardManager.removeCardByNumber(cardNumber);
    }
}
