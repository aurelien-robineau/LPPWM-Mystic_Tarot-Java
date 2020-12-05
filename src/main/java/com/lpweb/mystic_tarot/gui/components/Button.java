package com.lpweb.mystic_tarot.gui.components;

import java.awt.Dimension;

import javax.swing.*;

public class Button extends JButton {
    private static final long serialVersionUID = 1L;

    public Button(String text) {
        super(text);
        setPreferredSize(new Dimension(100, 40));
    }
}
