package com.lpweb.mystic_tarot.card;

import java.util.Scanner;

public class CardDeletor {
    public CardDeletor() {};

    public void deleteCard() {
        System.out.println("---- Card Deletor ----");
        CardManager cardManager = CardManager.getInstance();
        
        if (cardManager.getCards().size() == 0) {
            System.out.println("No card to delete.");
            return;
        }

        cardManager.displayCards();

        Scanner input = new Scanner(System.in);
        Integer cardNumber;
        while (true) {
            System.out.print("Card number: ");
            try {
                cardNumber = Integer.parseInt(input.nextLine());
                cardManager.removeCardByNumber(cardNumber);
                break;
            } catch (NumberFormatException e) {
                System.err.println("Number must be an integer.");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
