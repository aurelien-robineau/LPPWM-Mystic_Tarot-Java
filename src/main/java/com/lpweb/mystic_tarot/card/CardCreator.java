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
        Integer number      = input.getNewCardNumber("Number");
        String  name        = input.getString("Name");
        String  description = input.getString("Description");
        String  imagePath   = input.getString("Image path");

        Card newCard = new Card(number, name, description, imagePath);

        try {
            this.save(newCard);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Save a card.
     * Saving includes adding the card to the card manager and saving it as a
     * file.
     * @param card the card to save.
     * @throws Exception if the card number already exists.
     */
    public void save(Card card) throws Exception {
        Boolean numberExists  = CardManager.getInstance().cardNumberExists(card.number);

        if (numberExists) {
            throw new Exception("A card with this number already exists.");
        }

        CardManager cardManager = CardManager.getInstance();
        CardSerializer serializer = new CardSerializer(card);

        cardManager.getCards().add(card);

        // serializer.saveCardBinary();
        serializer.saveCardJSON();
    }
}
