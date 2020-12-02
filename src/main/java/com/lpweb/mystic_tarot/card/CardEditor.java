package com.lpweb.mystic_tarot.card;

import java.util.Scanner;

import com.lpweb.mystic_tarot.MysticTarot;

public class CardEditor {
    public CardEditor() {};

    public void editCard() {
        System.out.println("---- Card Editor ----");
        Scanner input = MysticTarot.getScanner();
        CardManager cardManager = CardManager.getInstance();

        if (cardManager.getCards().size() == 0) {
            System.out.println("No card to edit.");
            return;
        }
        
        Card card;
        while (true) {
            System.out.print("Card number: ");
            try {
                Integer cardNumber = Integer.parseInt(input.nextLine());
                card = cardManager.getCardByNumber(cardNumber);
                break;
            } catch (NumberFormatException e) {
                System.err.println("Number must be an integer.");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        while (true) {
            System.out.print("New number: ");
            try {
                card.number = Integer.parseInt(input.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.err.println("Number must be an integer.");
            }
        }

        System.out.print("new name: ");
        card.name = input.nextLine();

        System.out.print("New description: ");
        card.description = input.nextLine();

        System.out.print("New image path: ");
        card.imagePath = input.nextLine();

        CardSerializer serializer = new CardSerializer(card);
        serializer.save();
    }
}
