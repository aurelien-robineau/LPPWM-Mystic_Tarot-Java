package com.lpweb.mystic_tarot.gui.components;

import java.awt.Dimension;

import javax.swing.JTextField;

public class Input extends JTextField {
    private static final long serialVersionUID = 1L;

    public Input() {
        super();
        setPreferredSize(new Dimension(300, 40));
    }

    public Input(String text) {
        super(text);
        setPreferredSize(new Dimension(300, 40));
    }
}
