package com.lpweb.mystic_tarot.card;

import com.lpweb.mystic_tarot.UserInput;

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
        UserInput input = new UserInput();

        System.out.println("---- Card Creator ----");
        Integer number      = input.getInteger("Number");
        String  name        = input.getString("Name");
        String  description = input.getString("Description");
        String  imagePath   = input.getString("Image path");

        return new Card(number, name, description, imagePath);
    }
}
