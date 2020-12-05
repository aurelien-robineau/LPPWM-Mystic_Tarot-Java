package com.lpweb.mystic_tarot.gui.listeners;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.lpweb.mystic_tarot.card.Card;
import com.lpweb.mystic_tarot.gui.frames.CreateOrEditCard;

public class OpenEditForm implements ActionListener {
    private Card card;

    public OpenEditForm(Card card) {
        this.card = card;
    }

    public void actionPerformed(ActionEvent e) {
        CreateOrEditCard form = new CreateOrEditCard(card);
        form.setVisible(true);
    }
}
