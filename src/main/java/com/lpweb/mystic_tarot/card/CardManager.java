package com.lpweb.mystic_tarot.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import com.lpweb.mystic_tarot.MysticTarot;

/**
 * The CardManager class is a singleton for managing cards. All actions on cards
 * must go through the CardManager and not directly by other classes.
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
     * Singleton constructor.
     * Loads saved cards on init.
     */
    private CardManager() {
        // Both json and binary serialization available
        // cards = CardSerializer.loadBinarySavedCards();
        cards = CardSerializer.loadJSONSavedCards();
        sortCards();
    };

    /**
     * Opens the manager.
     * Asks the user what action he wants to do.
     */
    public void open() {
        isOpen = true;

        System.out.println("---- Card Manager ----");
        Scanner input = MysticTarot.getScanner();

        while (isOpen) {
            // Print all available actions
            System.out.println("* 1: Create new card");
            System.out.println("* 2: Edit card");
            System.out.println("* 3: Delete card");
            System.out.println("* 4: Search card");
            System.out.println("* 5: Display cards");
            System.out.println("* 6: Leave");
    
            // Ask action while invalid choice
            while (true) {
                System.out.print("Action: ");

                String action = input.nextLine();
    
                if (action.equals("1")) {
                    new CardCreator().openInConsole();
                    break;
                }
                else if (action.equals("2")) {
                    new CardEditor().openInConsole();
                    break;
                }
                else if (action.equals("3")) {
                    new CardDeletor().openInConsole();
                    break;
                }
                else if (action.equals("4")) {
                    new CardSearcher(cards).openInConsole();
                    break;
                }
                else if (action.equals("5")) {
                    displayCards();
                    break;
                }
                else if (action.equals("6")) {
                    close();
                    break;
                }
                else {
                    System.err.println("Invalid action.");
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
        return cards;
    }

    //--------------------------------------------------------------------------
    // Public methods
    //--------------------------------------------------------------------------

    /**
     * Closes the manager.
     */
    public void close() {
        isOpen = false;
    }

    /**
     * Check if a card numbers exists.
     * @param number the card number to check.
     * @return {@code true} if a card with this number exists.
     */
    public Boolean cardNumberExists(Integer number) {
        for (Card card : cards) {
            if (card.number.equals(number)) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * Gets a card with a given number.
     * @param  number number of the card to get.
     * @return card mathing the number.
     */
    public Card getCardByNumber(Integer number) {
        CardSearcher searcher = new CardSearcher(cards);
        return searcher.get(number);
    }

    /**
     * Gets a card with a given name.
     * @param  name name of the card to get.
     * @return card mathing the name.
     */
    public Card getCardByName(String name) {
        CardSearcher searcher = new CardSearcher(cards);
        return searcher.get(name);
    }

    /**
     * Get all cards with a number, name or description containing a given string.
     * @param search the string to search in the cards number, name and description.
     * @return the cards found.
     */
    public ArrayList<Card> getByAllMeans(String research) {
        CardSearcher searcher = new CardSearcher(cards);
        return searcher.getByAllMeans(research);
    }

    /**
     * Removes a card with a given number.
     * @param cardNumber number of the card to get.
     * @return the removed card.
     */
    public Card removeCardByNumber(Integer cardNumber) {
        Card cardToRemove = getCardByNumber(cardNumber);
        cards.remove(cardToRemove);

        return cardToRemove;
    }

    /**
     * Prints out all the manager cards.
     */
    public void displayCards() {
        for (Card card: cards) {
            System.out.println(card);
        }
    }

    /**
     * Save a new card.
     * @param card the card to save.
     * @throws Exception if the card number already exists. 
     */
    public void saveNewCard(Card card) throws Exception {
        CardCreator saver = new CardCreator();
        saver.save(card);
    }

    /**
     * Save an existing card.
     * @param card the card to save.
     * @throws Exception if the card number already exists. 
     */
    public void saveExistingCard(Card oldCard, Card newCard) throws Exception {
        CardEditor saver = new CardEditor();
        saver.save(oldCard, newCard);
    }

    /**
     * Delete a card.
     * @param card the card to delete.
     */
    public void deleteCard(Card card) {
        CardDeletor deletor = new CardDeletor();
        deletor.delete(card);
    }

    /**
     * Sorts the cards.
     */
    public void sortCards() {
        Collections.sort(cards);
    }
}
