package com.lpweb.mystic_tarot.card;

import java.util.ArrayList;
import java.util.Scanner;

import com.lpweb.mystic_tarot.MysticTarot;

/**
 * Console interface to manage Cards.
 */
public class CardManager {
    /**
     * CardManager instance.
     * CardManager is a singleton.
     */
    private static final CardManager instance = new CardManager();

    /**
     * All game cards.
     */
    private ArrayList<Card> cards;

    /**
     * Is the manager open ?
     */
    private Boolean isOpen = false;

    /**
     * Private constructor.
     * Loads saved cards on init.
     */
    private CardManager() {
        this.cards = CardSerializer.loadSavedCards();
    };

    /**
     * Opens the manager.
     * Asks the user what action he wants to do.
     */
    public void open() {
        this.isOpen = true;

        System.out.println("---- Card Manager ----");
        Scanner input = MysticTarot.getScanner();

        while (this.isOpen) {
            // Print all available actions
            System.out.println("* 1: Create new card");
            System.out.println("* 2: Edit card");
            System.out.println("* 3: Delete card");
            System.out.println("* 4: Search card by number");
            System.out.println("* 5: Search card by name");
            System.out.println("* 6: Search card matching description");
            System.out.println("* 7: Display cards");
            System.out.println("* 8: Leave");
    
            // Ask action while invalid choice
            while (true) {
                System.out.print("Action: ");

                try {
                    String action = input.nextLine();
    
                    if (action.equals("1")) {
                        this.createCard();
                        break;
                    }
                    else if (action.equals("2")) {
                        this.editCard();
                        break;
                    }
                    else if (action.equals("3")) {
                        this.deleteCard();
                        break;
                    }
                    else if (action.equals("4")) {
                        this.searchCardByNumber();
                        break;
                    }
                    else if (action.equals("5")) {
                        this.searchCardByName();
                        break;
                    }
                    else if (action.equals("6")) {
                        this.searchCardMatchingDescription();
                        break;
                    }
                    else if (action.equals("7")) {
                        this.displayCards();
                        break;
                    }
                    else if (action.equals("8")) {
                        this.close();
                        break;
                    }
                    else {
                        System.err.println("Invalid action.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Action must be an integer.");
                }
            }
        }
    }

    //--------------------------------------------------------------------------
    // Getters
    //--------------------------------------------------------------------------

    public static CardManager getInstance() {
        return instance;
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    //--------------------------------------------------------------------------
    // Public methods
    //--------------------------------------------------------------------------

    /**
     * Closes the manager.
     */
    public void close() {
        this.isOpen = false;
    }

    /**
     * Gets a card with a given number.
     * @param  number number of the card to get.
     * @return card mathing the number.
     * @throws Exception If the given number matches no card.
     */
    public Card getCardByNumber(Integer number) throws Exception {
        CardSearcher searcher = new CardSearcher(this.cards);
        Card card = searcher.get(number);

        if (card.equals(null)) {
            throw new Exception("Card with number " + number + " cannot be found.");
        }

        return card;
    }

    /**
     * Gets a card with a given name.
     * @param  name name of the card to get.
     * @return card mathing the name.
     * @throws Exception If the given name matches no card.
     */
    public Card getCardByName(String name) throws Exception {
        CardSearcher searcher = new CardSearcher(this.cards);
        Card card = searcher.get(name);

        if (card.equals(null)) {
            throw new Exception("Card with name " + name + " cannot be found.");
        }

        return card;
    }

    /**
     * Gets all cards with description containing a given string.
     * @param research string to look for in the descriptions.
     * @return cards mathing the research string
     * @throws Exception If the given research string matches no card.
     */
    public ArrayList<Card> getAllByMatchingDescription(String research) throws Exception {
        CardSearcher searcher = new CardSearcher(this.cards);
        ArrayList<Card> cards = searcher.getAllWhereDescriptionContains(research);

        if (cards.size() == 0) {
            throw new Exception("No card matching given description.");
        }

        return cards;
    }

    /**
     * Removes a card with a given number.
     * @param cardNumber number of the card to get.
     * @throws Exception If the given number matches no card.
     */
    public void removeCardByNumber(Integer cardNumber) throws Exception {
        this.cards.remove(this.getCardByNumber(cardNumber));
    }

    /**
     * Prints out all the manager cards.
     */
    public void displayCards() {
        for (Card card: this.cards) {
            System.out.println(card);
        }
    }

    //--------------------------------------------------------------------------
    // Private methods
    //--------------------------------------------------------------------------

    /**
     * Opens a new CardCreator and add the new card to CardManager cards.
     */
    private void createCard() {
        CardCreator creator = new CardCreator();
        this.cards.add(creator.createCard());
    }

    /**
     * Opens a new CardEditor and add the new card to CardManager cards.
     */
    private void editCard() {
        CardEditor editor = new CardEditor();
        editor.editCard();
    }

    /**
     * Opens new a CardDeletor.
     */
    private void deleteCard() {
        CardDeletor deletor = new CardDeletor();
        deletor.deleteCard();
    }

    /**
     * Searches a card with a given number and prints it out.
     */
    private void searchCardByNumber() {
        CardSearcher searcher = new CardSearcher(this.cards);
        Card card = searcher.searchByNumber();
        System.out.println(!card.equals(null) ? card : "No card found.");
    }

    /**
     * Searches a card with a given name and prints it out.
     */
    private void searchCardByName() {
        CardSearcher searcher = new CardSearcher(this.cards);
        Card card = searcher.searchByName();
        System.out.println(!card.equals(null) ? card : "No card found.");
    }

    /**
     * Searches cards with description containing a given string and prints them
     * out.
     */
    private void searchCardMatchingDescription() {
        CardSearcher searcher = new CardSearcher(this.cards);
        ArrayList<Card> cards = searcher.searchByMatchingDescription();

        if (cards.size() != 0) {
            for (Card card: searcher.searchByMatchingDescription()) {
                System.out.println(card);
            };
        }
        else {
            System.out.println("No card found.");
        }
    }
}
