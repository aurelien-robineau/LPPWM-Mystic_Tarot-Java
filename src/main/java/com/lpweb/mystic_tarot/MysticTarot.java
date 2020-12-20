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
     * Avalaibles user interfaces.
     */
    private enum Interface { CONSOLE, GRAPHICAL };

    /**
     * Interface to use when launching the app.
     */
    private static final Interface INTERFACE = Interface.GRAPHICAL;

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
        switch (INTERFACE) {
            case CONSOLE:
                CardManager.getInstance().open();
                break;
            case GRAPHICAL:
                MainFrame gui = new MainFrame();
                gui.setVisible(true);
                break;
            default:
                System.err.println("No interface chosen.");
        }
    }

    /**
     * Scanner getter.
     * @return the application scanner.
     */
    public static Scanner getScanner() {
        return scanner;
    }
}
