package com.lpweb.mystic_tarot.gui.listeners;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import com.lpweb.mystic_tarot.card.Card;
import com.lpweb.mystic_tarot.card.CardManager;
import com.lpweb.mystic_tarot.gui.GuiManager;

/**
 * Listener for opening searching cards and display them.
 */
public class ShowCards implements ActionListener {
    /**
     * Input for the user research.
     */
    private JTextField searchInput;

    /**
     * Create a new listener.
     * @param searchInput the input for the user research.
     */
    public ShowCards(JTextField searchInput) {
        this.searchInput = searchInput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        removeOldCards();
        refreshCards();
    }
    
    //--------------------------------------------------------------------------
    // Private methods
    //--------------------------------------------------------------------------

    /**
     * Remove previous cards from the GUI and reset the card panel list.
     */
    private void removeOldCards() {
        GuiManager.getInstance().deleteAllCards();
    }

    /**
     * Fill the card panel list wich fresh data get from the user research.
     */
    private void refreshCards() {
        ArrayList<Card> newCards = searchInput.getText().equals("") ?
            CardManager.getInstance().getCards() :
            CardManager.getInstance().getByAllMeans(searchInput.getText());

        for (Card card : newCards) {
            GuiManager.getInstance().addCard(card, BorderLayout.CENTER);
        }
    }
}
