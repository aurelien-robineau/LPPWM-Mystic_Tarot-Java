package com.lpweb.mystic_tarot.card;

import java.util.ArrayList;

import com.lpweb.mystic_tarot.UserInput;

/**
 * The CardSearcher class provides methods to search cards in a list of cards.
 */
public class CardSearcher implements CardAction {
    /**
     * List of cards the CardSearcher will search into.
     */
    private ArrayList<Card> cards;

    /**
     * Protected constructor.
     * @param cards the cards the CardSearcher will search into.
     */
    protected CardSearcher(ArrayList<Card> cards) {
        this.cards = cards;
    };

    //--------------------------------------------------------------------------
    // Public methods
    //--------------------------------------------------------------------------

    @Override
    public void openInConsole() {
        UserInput input = new UserInput();

        System.out.println("---- Card Searcher ----");
        System.out.println("* 1: Search card by number");
        System.out.println("* 2: Search card by name");
        
        while (true) {
            String action = input.getString("Search mode");
            if (action.equals("1")) {
                Card card = searchByNumber();
                System.out.println(card != null ? card : "No card found.");
                break;
            }
            else if (action.equals("2")) {
                Card card = searchByName();
                System.out.println(card != null ? card : "No card found.");
                break;
            }
            else {
                System.err.println("Invalid action.");
            }
        }
    }

    /**
     * Displays a console interface asking the user a card number to search.
     * @return the found card or null if no card matches the given number.
     */
    public Card searchByNumber() {
        UserInput input = new UserInput();

        System.out.println("---- Card Searcher (Number) ----");
        return get(input.getInteger("Card number"));
    }

    /**
     * Displays a console interface asking the user a card name to search.
     * @return the found card or null if no card matches the given name.
     */
    public Card searchByName() {
        UserInput input = new UserInput();
        
        System.out.println("---- Card Searcher (Name) ----");
        return get(input.getString("Card name"));
    }

    /**
     * Get a card with a given number.
     * @param number the number of the card to get.
     * @return the found card or null if no card matches the given number.
     */
    public Card get(Integer number) {
        for (Card card : cards) {
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
        name = name.toLowerCase();

        for (Card card : cards) {
            if (card.name.toLowerCase().equals(name)) {
                return card;
            }
        }

        return null;
    }

    /**
     * Get all cards with a number or name containing a given string.
     * @param search the string to search in the cards number, name and description.
     * @return the cards found.
     */
    public ArrayList<Card> getByAllMeans(String search) {
        search = search.toLowerCase();

        ArrayList<Card> results = new ArrayList<>();
        for (Card card : cards) {
            if (card.number.toString().toLowerCase().contains(search)
                || card.name.toLowerCase().contains(search)
                && !results.contains(card))
            {
                results.add(card);
            }
        }

        return results;
    }
}
