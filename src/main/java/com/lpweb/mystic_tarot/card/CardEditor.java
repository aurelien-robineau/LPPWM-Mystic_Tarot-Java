package com.lpweb.mystic_tarot.card;

import java.util.Scanner;

import com.lpweb.mystic_tarot.MysticTarot;
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

        // No card
        if (cardManager.getCards().size() == 0) {
            System.out.println("No card to edit.");
            return null;
        }
        
        Card card;

        // Ask card number while invalid
        Integer cardNumber = input.getCardNumber("Card number");
        try {
            card = cardManager.removeCardByNumber(cardNumber);
        }
        catch (Exception e) {
            return null;
        }

        card.number      = input.getInteger("New number");
        card.name        = input.getString("New name");
        card.description = input.getString("New description");
        card.imagePath   = input.getString("New image path");

        return card;
    }
}
