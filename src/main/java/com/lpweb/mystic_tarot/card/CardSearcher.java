package com.lpweb.mystic_tarot.card;

import java.util.ArrayList;
import java.util.Scanner;

import com.lpweb.mystic_tarot.MysticTarot;

/**
 * The CardSearcher class provides methods to search cards in a list of cards.
 */
public class CardSearcher {
    /**
     * List of cards the CardSearcher will search into.
     */
    private ArrayList<Card> cards;

    /**
     * Public constructor.
     * @param cards the cards the CardSearcher will search into.
     */
    public CardSearcher(ArrayList<Card> cards) {
        this.cards = cards;
    };

    //--------------------------------------------------------------------------
    // Public methods
    //--------------------------------------------------------------------------

    /**
     * Displays a console interface asking the user a card number to search.
     * @return the found card or null if no card matches the given number.
     */
    public Card searchByNumber() {
        System.out.println("---- Card Searcher (Number) ----");

        Scanner input = MysticTarot.getScanner();
        while (true) {
            System.out.print("Card number: ");
            try {
                Integer cardNumber = Integer.parseInt(input.nextLine());
                return this.get(cardNumber);
            } catch (NumberFormatException e) {
                System.err.println("Number must be an integer.");
            }
        }
    }

    /**
     * Displays a console interface asking the user a card name to search.
     * @return the found card or null if no card matches the given name.
     */
    public Card searchByName() {
        System.out.println("---- Card Searcher (Name) ----");
        Scanner input = MysticTarot.getScanner();

        System.out.print("Card number: ");
        return this.get(input.nextLine());
    }

    /**
     * Displays a console interface asking the user a string to look for in
     * cards description.
     * @return the list cards with description containing the given string.
     */
    public ArrayList<Card> searchByMatchingDescription() {
        System.out.println("---- Card Searcher (Matching Description) ----");
        Scanner input = MysticTarot.getScanner();

        System.out.print("Card number: ");
        return this.getAllWhereDescriptionContains(input.nextLine());
    }

    /**
     * Get a card with a given number.
     * @param number the number of the card to get.
     * @return the found card or null if no card matches the given number.
     */
    public Card get(Integer number) {
        for (Card card : this.cards) {
            if (card.number.equals(number)) {
                return card;
            }
        }

        return null;
    }

    /**
     * Get a card with a given name.
     * @param name the name of the card to get.
     * @return the found card or null if no card matches the given name.
     */
    public Card get(String name) {
        for (Card card : this.cards) {
            if (card.name.equals(name)) {
                return card;
            }
        }

        return null;
    }

    /**
     * Get all cards with a description containing a given string.
     * @param search the string to search in the cards description
     * @return the cards found
     */
    public ArrayList<Card> getAllWhereDescriptionContains(String search) {
        ArrayList<Card> results = new ArrayList<>();
        for (Card card : this.cards) {
            if (card.description.contains(search)) {
                results.add(card);
            }
        }

        return results;
    }
}
