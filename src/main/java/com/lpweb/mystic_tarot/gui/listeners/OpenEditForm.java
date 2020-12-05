package com.lpweb.mystic_tarot.gui.listeners;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.lpweb.mystic_tarot.card.Card;
import com.lpweb.mystic_tarot.gui.frames.CreateOrEditCard;

/**
 * Listener for opening a form to edit a card.
 */
public class OpenEditForm implements ActionListener {
    /**
     * Card to edit.
     */
    private Card card;

    /**
     * Create a new listener.
     * @param card
     */
    public OpenEditForm(Card card) {
        this.card = card;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CreateOrEditCard form = new CreateOrEditCard(card);
        form.setVisible(true);
    }
}
