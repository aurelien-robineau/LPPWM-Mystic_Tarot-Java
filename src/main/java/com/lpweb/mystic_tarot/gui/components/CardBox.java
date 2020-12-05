package com.lpweb.mystic_tarot.gui.components;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.lpweb.mystic_tarot.card.Card;
import com.lpweb.mystic_tarot.gui.listeners.OpenEditForm;

public class CardBox extends JPanel {
    private static final long serialVersionUID = 1L;

    public CardBox(Card card) {
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
