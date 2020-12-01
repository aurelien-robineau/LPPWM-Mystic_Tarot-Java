package com.lpweb.mystic_tarot.card;

import java.util.ArrayList;
import java.util.Scanner;

public class CardManager {
    private static final CardManager instance = new CardManager();

    private ArrayList<Card> cards = new ArrayList<>();

    private Boolean isOpen = false;

    private CardManager() {};

    public void open() {
        this.isOpen = true;

        System.out.println("---- Card Manager ----");
        Scanner input = new Scanner(System.in);

        while (this.isOpen) {
            System.out.println("* 1: Create new card");
            System.out.println("* 2: Delete card");
            System.out.println("* 3: Display cards");
            System.out.println("* 4: Leave");
    
            while (true) {
                System.out.print("Action: ");
                try {
                    String action = input.nextLine();
    
                    if (action.equals("1")) {
                        this.createCard();
                        break;
                    }
                    else if (action.equals("2")) {
                        this.deleteCard();
                        break;
                    }
                    else if (action.equals("3")) {
                        this.displayCards();
                        break;
                    }
                    else if (action.equals("4")) {
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

    public void removeCardByNumber(Integer cardNumber) throws Exception {
        Integer numberOfCards = this.cards.size();

        for (Card card : this.cards) {
            if (card.getNumber().equals(cardNumber)) {
                this.cards.remove(card);
                break;
            }
        }

        if (numberOfCards.equals(this.cards.size())) {
            throw new Exception("Card with number " + cardNumber + " cannot be found.");
        }
    }

    private void createCard() {
        CardCreator creator = new CardCreator();
        this.cards.add(creator.createCard());
    }

    private void deleteCard() {
        CardDeletor deletor = new CardDeletor();
        deletor.deleteCard();
    }

    public void displayCards() {
        for (Card card: this.cards) {
            System.out.println(card);
        }
    }
}
