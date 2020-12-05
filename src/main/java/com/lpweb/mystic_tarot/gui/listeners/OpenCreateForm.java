package com.lpweb.mystic_tarot.gui.listeners;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.lpweb.mystic_tarot.gui.frames.CreateOrEditCard;

public class OpenCreateForm implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        CreateOrEditCard form = new CreateOrEditCard();
        form.setVisible(true);
    }
}
