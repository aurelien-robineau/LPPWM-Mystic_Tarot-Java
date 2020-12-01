package com.lpweb.mystic_tarot.card;

import java.util.ArrayList;
import java.util.Scanner;

public class CardSearcher {
    private ArrayList<Card> cards;

    public CardSearcher(ArrayList<Card> cards) {
        this.cards = cards;
    };

    public Card searchByNumber() {
        System.out.println("---- Card Searcher (Number) ----");

        Scanner input = new Scanner(System.in);
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

    public Card searchByName() {
        System.out.println("---- Card Searcher (Name) ----");

        Scanner input = new Scanner(System.in);
        return this.get(input.nextLine());
    }

    public ArrayList<Card> searchByMatchingDescription() {
        System.out.println("---- Card Searcher (Matching Description) ----");

        Scanner input = new Scanner(System.in);
        return this.getAllWhereDescriptionContains(input.nextLine());
    }

    public Card get(Integer number) {
        for (Card card : this.cards) {
            if (card.number.equals(number)) {
                return card;
            }
        }

        return null;
    }

    public Card get(String name) {
        for (Card card : this.cards) {
            if (card.name.equals(name)) {
                return card;
            }
        }

        return null;
    }

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
