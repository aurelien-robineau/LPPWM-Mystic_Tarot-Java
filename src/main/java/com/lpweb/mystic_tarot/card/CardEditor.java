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
    public void editCard() {
        UserInput input = new UserInput();
        CardManager cardManager = CardManager.getInstance();

        System.out.println("---- Card Editor ----");

        if (cardManager.getCards().size() == 0) {
            System.out.println("No card to edit.");
            return;
        }
        
        cardManager.displayCards();

        Integer cardNumber = input.getCardNumber("Card number");
        Card card = cardManager.getCardByNumber(cardNumber);

        Integer number      = input.getInteger("New number");
        String  name        = input.getString("New name");
        String  description = input.getString("New description");
        String  imagePath   = input.getString("New image path");

        Card newCard = new Card(number, name, description, imagePath);

        this.save(card, newCard);
    }

    /**
     * Saves the edited card and clean card files directory.
     * @param card the card to save.
     * @param newCard the temporary card with the new data.
     */
    public void save(Card card, Card newCard) {
        card.refreshFrom(newCard);

        CardSerializer serializer = new CardSerializer(card);

        // serializer.saveCardBinary();
        serializer.saveCardJSON();

        CardSerializer.cleanFiles();
    }
}
