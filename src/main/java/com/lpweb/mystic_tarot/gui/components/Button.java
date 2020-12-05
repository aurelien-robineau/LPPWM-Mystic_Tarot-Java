package com.lpweb.mystic_tarot.gui.components;

import java.awt.Dimension;

import javax.swing.*;

/**
 * Custom JButton.
 * @see JButton
 */
public class Button extends JButton {
    private static final long serialVersionUID = 1L;

    /**
     * Create a new Button with a text.
     * @param text the text to display on the button.
     * @see JButton#JButton(String)
     */
    public Button(String text) {
        super(text);
        setPreferredSize(new Dimension(100, 40));
    }
}
