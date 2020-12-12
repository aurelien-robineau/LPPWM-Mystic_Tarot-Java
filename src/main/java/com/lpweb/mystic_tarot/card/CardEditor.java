package com.lpweb.mystic_tarot.card;

import com.lpweb.mystic_tarot.FileCopier;
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

        Integer number    = input.getNewCardNumber("New number", card.number);
        String  name      = input.getString("New name");
        String  imagePath = input.getString("New image path");

        Card newCard = new Card(number, name, imagePath);

        try {
            this.save(card, newCard);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Saves the edited card and clean card files directory.
     * @param card the card to save.
     * @param newCard the temporary card with the new data.
     * @throws Exception if the card number already exists.
     */
    public void save(Card card, Card newCard) throws Exception {
        Boolean numberExists  = CardManager.getInstance().cardNumberExists(card.number);
        Boolean numberChanged = !card.number.equals(newCard.number);

        if (numberChanged && numberExists) {
            throw new Exception("A card with this number already exists.");
        }

        String oldImagePath = new String(card.imagePath);
        card.refreshFrom(newCard);

        // Copy the image to the project if it changed
        if (!oldImagePath.equals(newCard.imagePath)) {
            FileCopier copier = new FileCopier(card.image);
            card.imagePath = Card.cardImageDirectory + card.image.getName();
            card.image     = copier.copyTo(card.imagePath);
        }

        CardSerializer serializer = new CardSerializer(card);
        // serializer.saveCardBinary();
        serializer.saveCardJSON();

        CardSerializer.cleanFiles();
    }
}
