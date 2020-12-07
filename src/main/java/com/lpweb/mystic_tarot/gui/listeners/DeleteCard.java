package com.lpweb.mystic_tarot.gui.listeners;

import com.lpweb.mystic_tarot.card.Card;
import com.lpweb.mystic_tarot.card.CardManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteCard implements ActionListener {
    private Card card;

    public DeleteCard(Card card) {
        this.card = card;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CardManager.getInstance().deleteCard(card);
    }
}
