package com.lpweb.mystic_tarot.gui.components;

import java.awt.Dimension;

import javax.swing.JTextField;

/**
 * Custom JTextField.
 * @see JTextField
 */
public class Input extends JTextField {
    private static final long serialVersionUID = 1L;

    /**
     * Create a new empy Input.
     * @see JTextField#JTextField()
     */
    public Input() {
        super();
        setPreferredSize(new Dimension(300, 40));
    }

    /**
     * Create a new Input prefield with a text.
     * @param text the text to insert into the input.
     * @see JTextField#JTextField(String)
     */
    public Input(String text) {
        super(text);
        setPreferredSize(new Dimension(300, 40));
    }
}
