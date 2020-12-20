package com.lpweb.mystic_tarot.card;

import com.lpweb.mystic_tarot.UserInput;

/**
 * The CardDeletor provides methods for deleting cards.
 * It also provides a console interface.
 */
public class CardDeletor implements CardAction {
    protected CardDeletor() {};

    @Override
    public void openInConsole() {
        UserInput input = new UserInput();
        CardManager cardManager = CardManager.getInstance();

        System.out.println("---- Card Deletor ----");
        
        if (cardManager.getCards().size() == 0) {
            System.out.println("No card to delete.");
            return;
        }

        cardManager.displayCards();

        Integer number = input.getCardNumber("Card number");
        Card card = cardManager.getCardByNumber(number);
        delete(card);
    }

    /**
     * Delete a card.
     * Deleting includes removing the card from the card manager and deleting
     * the card file.
     * @param card the card to delete.
     */
    public void delete(Card card) {
        CardManager cardManager = CardManager.getInstance();
        CardSerializer serializer = new CardSerializer(card);

        cardManager.getCards().remove(card);

        serializer.deleteCard();

        card.image.delete();
    }
}
