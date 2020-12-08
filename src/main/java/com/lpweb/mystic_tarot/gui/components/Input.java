package com.lpweb.mystic_tarot.gui.components;

import java.awt.Dimension;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

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

    public void setError() {
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.RED, Color.RED));
    }

    public void removeError() {
        setBorder(null);
    }
}
