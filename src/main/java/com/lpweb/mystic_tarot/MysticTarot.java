package com.lpweb.mystic_tarot;

import java.util.Scanner;

import com.lpweb.mystic_tarot.card.CardManager;
import com.lpweb.mystic_tarot.gui.frames.MainFrame;

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
    
    /**
     * Main function. Used to launch application.
     * @param args
     */
    public static void main( String[] args )
    {
        // To use the console instead of the GUI, use the CardManager open method.
        CardManager.getInstance().open();

        // MainFrame gui = new MainFrame();
        // gui.setVisible(true);
    }

    /**
     * Scanner getter.
     * @return the application scanner.
     */
    public static Scanner getScanner() {
        return scanner;
    }
}
