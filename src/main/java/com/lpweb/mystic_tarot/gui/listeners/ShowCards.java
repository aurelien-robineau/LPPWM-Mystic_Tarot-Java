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

/**
 * Listener for opening searching cards and display them.
 */
public class ShowCards implements ActionListener {
    /**
     * Where to display cards.
     */
    private JComponent target;

    /**
     * Input for the user research.
     */
    private JTextField searchInput;

    /**
     * List of CardPanel to display.
     */
    private ArrayList<CardPanel> cards = new ArrayList<>();

    /**
     * Create a new listener.
     * @param target the component where to display cards.
     * @param searchInput the input for the user research.
     */
    public ShowCards(JComponent target, JTextField searchInput) {
        this.target      = target;
        this.searchInput = searchInput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        removeOldCards();
        refreshCards();

        for (CardPanel cardBox : cards) {
            target.add(cardBox, BorderLayout.CENTER);
        }

        render();
    }

    /**
     * Remove previous cards from the GUI and reset the card panel list.
     */
    private void removeOldCards() {
        // Remove cards from target
        for (CardPanel card : cards) {
            target.remove(card);
        }

        // Reset card list
        cards = new ArrayList<>();
    }

    /**
     * Render the new cards.
     */
    private void render() {
        target.revalidate();
        target.repaint();
    }

    /**
     * Fill the card panel list wich fresh data get from the user research.
     */
    private void refreshCards() {
        cards = new ArrayList<>();

        ArrayList<Card> newCards = searchInput.getText().equals("") ?
            CardManager.getInstance().getCards() :
            CardManager.getInstance().getByAllMeans(searchInput.getText());

        for (Card card : newCards) {
            cards.add(new CardPanel(card));
        }
    }
}
