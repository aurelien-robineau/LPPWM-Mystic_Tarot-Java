package com.lpweb.mystic_tarot.gui.components;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.lpweb.mystic_tarot.card.Card;
import com.lpweb.mystic_tarot.gui.listeners.OpenEditForm;

/**
 * A panel to display a card.
 * The panel includes the card number, name and description.
 * It also provides two buttons to edit or delete the card.
 */
public class CardPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new CardPanel from a Card.
     * @param card the card to be represented on the panel.
     */
    public CardPanel(Card card) {
        super();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel titlePanel       = new JPanel();
        JPanel descriptionPanel = new JPanel();

        add(titlePanel);
        add(descriptionPanel);

        JLabel titleLabel       = new JLabel("Carte n°" + card.getNumber() + ": " + card.getName());
        JLabel descriptionLabel = new JLabel(card.getDescription());

        titlePanel.add(titleLabel);
        descriptionPanel.add(descriptionLabel);

        JPanel controlsPanel = new JPanel();
        
        add(controlsPanel);

        Button editCard = new Button("Edit");
        Button deleteCard = new Button("Delete");

        controlsPanel.add(editCard);
        controlsPanel.add(deleteCard);

        editCard.addActionListener(new OpenEditForm(card));
    }
}
