package com.lpweb.mystic_tarot.gui;

import java.util.ArrayList;

import javax.swing.JComponent;

import com.lpweb.mystic_tarot.card.Card;
import com.lpweb.mystic_tarot.gui.components.CardPanel;

/**
 * The GuiManager class is a singleton for managing the GUI.
 * It provides methods to manage cards. Adding, removing or editing cards will
 * only change the GUI and not actually the data. Use the {@code CardManager}
 * singleton to manage the data.
 */
public class GuiManager {
    /**
     * GuiManager instance.
     */
    private static GuiManager instance = new GuiManager();

    /**
     * All application card panels.
     */
    private ArrayList<CardPanel> cardPanels = new ArrayList<>();

    /**
     * Container for the card panels.
     */
    private JComponent cardContainer;

    //--------------------------------------------------------------------------
    // Constructors
    //--------------------------------------------------------------------------

    /**
     * Constructs a new GuiManager.
     * This constructor is private as GuiManager is a singleton.
     */
    private GuiManager() {};

    //--------------------------------------------------------------------------
    // Public methods
    //--------------------------------------------------------------------------

    /**
     * Adds a card panel to the card panel container.
     * @param card the card to add.
     * @param position position of the card panel in the container.
     */
    public void addCard(Card card, String position) {
        CardPanel panel = new CardPanel(card);

        cardPanels.add(panel);

        cardContainer.add(panel, position);
        cardContainer.revalidate();
        cardContainer.repaint();
    }

    /**
     * Refreshes the old card panel with data from a new card.
     * @param oldCard old card for wich editing the panel.
     * @param newCard new card to refresh panel with.
     */
    public void refreshCardPanel(Card oldCard, Card newCard) {
        CardPanel cardPanel = getCardPanel(oldCard);

        if (cardPanel != null) {
            cardPanel.setCard(newCard);
            cardPanel.revalidate();
            cardPanel.repaint();
        }
    }

    /**
     * Deletes a card panel.
     * @param card card for wich the panel must be deleted.
     */
    public void deleteCard(Card card) {
        CardPanel cardPanel = getCardPanel(card);

        cardPanels.remove(cardPanel);
                
        cardContainer.remove(cardPanel);
        cardContainer.revalidate();
        cardContainer.repaint();
    }

    /**
     * Deletes all cards panel.
     */
    public void deleteAllCards() {
        for (CardPanel panel: cardPanels) {
            cardContainer.remove(panel);
        }

        cardContainer.revalidate();
        cardContainer.repaint();

        cardPanels = new ArrayList<>();
    }

    /**
     * Get a card panel from a card.
     * @param card card for wich we must find the panel.
     * @return the card panel, null if not found.
     */
    public CardPanel getCardPanel(Card card) {
        for (CardPanel cardPanel : cardPanels) {
            if (cardPanel.getCard().equals(card)) {
                return cardPanel;
            }
        }

        return null;
    }
    
    /**
     * Change the card panel container.
     * This method should be call a least once otherwise the container is never
     * initialized.
     * @param container
     */
    public void setCardContainer(JComponent container) {
        cardContainer = container;
    }

    //--------------------------------------------------------------------------
    // Public static methods
    //--------------------------------------------------------------------------

    /**
     * Get the GuiManager instance.
     * @return the GuiManager instance.
     */
    public static GuiManager getInstance() {
        return instance;
    }
}
