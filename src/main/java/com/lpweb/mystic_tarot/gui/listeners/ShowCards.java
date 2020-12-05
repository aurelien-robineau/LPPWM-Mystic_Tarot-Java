package com.lpweb.mystic_tarot.gui.listeners;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.JComponent;
import javax.swing.JTextField;

import com.lpweb.mystic_tarot.card.Card;
import com.lpweb.mystic_tarot.card.CardManager;
import com.lpweb.mystic_tarot.gui.components.CardPanel;

public class ShowCards implements ActionListener {
    private JComponent target;
    private JTextField searchInput;
    private ArrayList<CardPanel> cards = new ArrayList<>();

    public ShowCards(JComponent target, JTextField searchInput) {
        this.target      = target;
        this.searchInput = searchInput;
    }

    public void actionPerformed(ActionEvent e) {
        removeOldCards();
        refreshCards();

        for (CardPanel cardBox : cards) {
            target.add(cardBox, BorderLayout.CENTER);
        }

        render();
    }

    private void removeOldCards() {
        // Remove cards from target
        for (CardPanel card : cards) {
            target.remove(card);
        }

        // Reset card list
        cards = new ArrayList<>();
    }

    private void render() {
        target.revalidate();
        target.repaint();
    }

    private void refreshCards() {
        cards = new ArrayList<>();

        ArrayList<Card> newCards = searchInput.getText().equals("") ?
            CardManager.getInstance().getCards() :
            CardManager.getInstance().getAllByMatchingDescription(searchInput.getText());

        for (Card card : newCards) {
            cards.add(new CardPanel(card));
        }
    }
}
