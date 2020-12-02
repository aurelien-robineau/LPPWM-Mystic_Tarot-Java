package com.lpweb.mystic_tarot;

import java.util.Scanner;

import com.lpweb.mystic_tarot.card.CardManager;


/**
 * Mystic tarot game.
 */
public class MysticTarot 
{
    /**
     * Scanner for user input.
     * This allows to fix the issue mentionned here :
     * https://stackoverflow.com/questions/14142853/close-a-scanner-linked-to-system-in
     */
    private static final Scanner scanner = new Scanner(System.in);
    public static void main( String[] args )
    {
        CardManager cardManager = CardManager.getInstance();
        cardManager.open();

        scanner.close();
    }

    public static Scanner getScanner() {
        return scanner;
    }
}
