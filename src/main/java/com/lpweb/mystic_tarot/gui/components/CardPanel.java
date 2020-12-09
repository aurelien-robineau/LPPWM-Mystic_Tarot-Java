package com.lpweb.mystic_tarot.gui.components;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.lpweb.mystic_tarot.card.Card;
import com.lpweb.mystic_tarot.gui.listeners.DeleteCard;
import com.lpweb.mystic_tarot.gui.listeners.OpenEditForm;

/**
 * A panel to display a card.
 * The panel includes the card number, name and description.
 * It also provides two buttons to edit or delete the card.
 */
public class CardPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    /**
     * Card represented on the panel.
     */
    private Card card;

    /**
     * Creates a new CardPanel from a Card.
     * @param card the card to be represented on the panel.
     */
    public CardPanel(Card card) {
        super();
        this.card = card;

        construct();
    }

    //--------------------------------------------------------------------------
    // Public methods
    //--------------------------------------------------------------------------

    /**
     * Card getter.
     * @return the panel card.
     */
    public Card getCard() {
        return card;
    }

    /**
     * Changes the card panel card and refreshes the GUI.
     * @param card
     */
    public void setCard(Card card) {
        this.card = card;
        refresh();
    }

    //--------------------------------------------------------------------------
    // Private methods
    //--------------------------------------------------------------------------

    /**
     * Constructs the card panel.
     */
    private void construct() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel titlePanel       = new JPanel();
        JPanel descriptionPanel = new JPanel();
        JPanel imagePanel       = new JPanel();

        add(titlePanel);
        add(descriptionPanel);
        add(imagePanel);

        JLabel titleLabel       = new JLabel("Carte n°" + card.getNumber() + ": " + card.getName());
        JLabel descriptionLabel = new JLabel(card.getDescription());
        Image  image            = new Image(card.getImage());

        titlePanel.add(titleLabel);
        descriptionPanel.add(descriptionLabel);
        imagePanel.add(image);

        JPanel controlsPanel = new JPanel();
        
        add(controlsPanel);

        Button editCard = new Button("Edit");
        Button deleteCard = new Button("Delete");

        controlsPanel.add(editCard);
        controlsPanel.add(deleteCard);

        editCard.addActionListener(new OpenEditForm(card));
        deleteCard.addActionListener(new DeleteCard(card));
    }

    /**
     * Removes all components from the panel and reconstructs it.
     */
    private void refresh() {
        for (Component component : getComponents()) {
            remove(component);
        }

        construct();
    }
}
