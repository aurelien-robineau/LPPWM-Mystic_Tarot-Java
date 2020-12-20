package com.lpweb.mystic_tarot.card;

import com.lpweb.mystic_tarot.FileCopier;
import com.lpweb.mystic_tarot.UserInput;

/**
 * The CardCreator class provides methods to save a new card.
 * It also provides a console interface.
 */
public class CardCreator implements CardAction {
    protected CardCreator() {};

    @Override
    public void openInConsole() {
        UserInput input = new UserInput();

        System.out.println("---- Card Creator ----");
        Integer number    = input.getNewCardNumber("Number");
        String  name      = input.getString("Name");
        String  imagePath = input.getString("Image path");

        Card newCard = new Card(number, name, imagePath);

        try {
            save(newCard);
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
        cardManager.sortCards();

        // Copy the image to the project
        FileCopier copier = new FileCopier(card.image);
        card.imagePath = Card.cardImageDirectory + card.image.getName();
        card.image     = copier.copyTo(card.imagePath);

        serializer.saveCard();
    }
}
