package com.lpweb.mystic_tarot.card;

import java.util.ArrayList;
import java.util.Scanner;

public class CardManager {
    private static final CardManager instance = new CardManager();

    private ArrayList<Card> cards;

    private Boolean isOpen = false;

    private CardManager() {
        this.cards = CardSerializer.loadSavedCards();
    };

    public void open() {
        this.isOpen = true;

        System.out.println("---- Card Manager ----");
        Scanner input = new Scanner(System.in);

        while (this.isOpen) {
            System.out.println("* 1: Create new card");
            System.out.println("* 2: Edit card");
            System.out.println("* 3: Delete card");
            System.out.println("* 4: Search card by number");
            System.out.println("* 5: Search card by name");
            System.out.println("* 6: Search card matching description");
            System.out.println("* 7: Display cards");
            System.out.println("* 8: Leave");
    
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

    public static CardManager getInstance() {
        return instance;
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    public void close() {
        this.isOpen = false;
    }

    public Card getCardByNumber(Integer cardNumber) throws Exception {
        CardSearcher searcher = new CardSearcher(this.cards);
        Card card = searcher.get(cardNumber);

        if (card.equals(null)) {
            throw new Exception("Card with number " + cardNumber + " cannot be found.");
        }

        return card;
    }

    public Card getCardByName(String name) throws Exception {
        CardSearcher searcher = new CardSearcher(this.cards);
        Card card = searcher.get(name);

        if (card.equals(null)) {
            throw new Exception("Card with name " + name + " cannot be found.");
        }

        return card;
    }

    public ArrayList<Card> getAllByMatchingDescription(String research) throws Exception {
        CardSearcher searcher = new CardSearcher(this.cards);
        ArrayList<Card> cards = searcher.getAllWhereDescriptionContains(research);

        if (cards.size() == 0) {
            throw new Exception("No card matching given description.");
        }

        return cards;
    }

    public void removeCardByNumber(Integer cardNumber) throws Exception {
        this.cards.remove(this.getCardByNumber(cardNumber));
    }

    public void displayCards() {
        for (Card card: this.cards) {
            System.out.println(card);
        }
    }

    private void createCard() {
        CardCreator creator = new CardCreator();
        this.cards.add(creator.createCard());
    }

    private void editCard() {
        CardEditor editor = new CardEditor();
        editor.editCard();
    }

    private void deleteCard() {
        CardDeletor deletor = new CardDeletor();
        deletor.deleteCard();
    }

    private void searchCardByNumber() {
        CardSearcher searcher = new CardSearcher(this.cards);
        Card card = searcher.searchByNumber();
        System.out.println(!card.equals(null) ? card : "No card found.");
    }

    private void searchCardByName() {
        CardSearcher searcher = new CardSearcher(this.cards);
        Card card = searcher.searchByName();
        System.out.println(!card.equals(null) ? card : "No card found.");
    }

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
