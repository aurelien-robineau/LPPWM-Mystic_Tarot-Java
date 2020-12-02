package com.lpweb.mystic_tarot.card;

import com.lpweb.mystic_tarot.UserInput;

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
        UserInput input = new UserInput();
        CardManager cardManager = CardManager.getInstance();

        System.out.println("---- Card Editor ----");

        if (cardManager.getCards().size() == 0) {
            System.out.println("No card to edit.");
            return null;
        }
        
        Integer cardNumber = input.getCardNumber("Card number");
        Card card = cardManager.getCardByNumber(cardNumber);

        card.number      = input.getInteger("New number");
        card.name        = input.getString("New name");
        card.description = input.getString("New description");
        card.imagePath   = input.getString("New image path");

        return card;
    }
}
