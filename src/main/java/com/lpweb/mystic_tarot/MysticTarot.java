package com.lpweb.mystic_tarot;

import java.util.Scanner;

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
        MainFrame gui = new MainFrame();
        gui.setVisible(true);
    }

    public static Scanner getScanner() {
        return scanner;
    }
}
