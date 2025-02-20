package com.lpweb.mystic_tarot.card;

import com.lpweb.mystic_tarot.FileCopier;
import com.lpweb.mystic_tarot.UserInput;

/**
 * The CardEditor class provides methods for saving existing cards.
 * It alose provides a console interface.
 */
public class CardEditor implements CardAction {
    protected CardEditor() {};

    @Override
    public void openInConsole() {
        UserInput input = new UserInput();
        CardManager cardManager = CardManager.getInstance();

        System.out.println("---- Card Editor ----");

        if (cardManager.getCards().size() == 0) {
            System.out.println("No card to edit.");
            return;
        }
        
        cardManager.displayCards();

        Integer cardNumber = input.getCardNumber("Card number");
        Card    card       = cardManager.getCardByNumber(cardNumber);

        Integer number    = input.getNewCardNumber("New number", card.number);
        String  name      = input.getString("New name");
        String  imagePath = input.getString("New image path");

        Card newCard = new Card(number, name, imagePath);

        try {
            save(card, newCard);
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

        // Copy the image to the project if it changed
        if (!card.imagePath.equals(newCard.imagePath)) {
            card.image.delete();
            FileCopier copier = new FileCopier(newCard.image);
            newCard.imagePath = Card.cardImageDirectory + newCard.image.getName();
            newCard.image     = copier.copyTo(newCard.imagePath);
        }

        // Update card with new values
        card.refreshFrom(newCard);
        CardManager.getInstance().sortCards();

        CardSerializer serializer = new CardSerializer(card);
        serializer.saveCard();

        CardSerializer.cleanFiles();
    }
}
