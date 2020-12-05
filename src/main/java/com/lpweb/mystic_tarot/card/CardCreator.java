package com.lpweb.mystic_tarot.card;

import com.lpweb.mystic_tarot.UserInput;

/**
 * The CardCreator class provides a console interface to create a new card.
 */
public class CardCreator {
    protected CardCreator() {};

    /**
     * Asks the user values for a new card and save the card.
     */
    public void createCard() {
        UserInput input = new UserInput();

        System.out.println("---- Card Creator ----");
        Integer number      = input.getInteger("Number");
        String  name        = input.getString("Name");
        String  description = input.getString("Description");
        String  imagePath   = input.getString("Image path");

        Card newCard = new Card(number, name, description, imagePath);
        this.save(newCard);
    }

    /**
     * Save a card.
     * Saving includes adding the card to the card manager and saving it as a
     * file.
     * @param card the card to save.
     */
    public void save(Card card) {
        CardManager cardManager = CardManager.getInstance();
        CardSerializer serializer = new CardSerializer(card);

        cardManager.getCards().add(card);

        // serializer.saveCardBinary();
        serializer.saveCardJSON();
    }
}
