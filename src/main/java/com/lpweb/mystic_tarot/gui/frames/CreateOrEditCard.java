package com.lpweb.mystic_tarot.gui.frames;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.lpweb.mystic_tarot.card.Card;
import com.lpweb.mystic_tarot.gui.components.Button;
import com.lpweb.mystic_tarot.gui.components.ImageFilePicker;
import com.lpweb.mystic_tarot.gui.components.TextInput;
import com.lpweb.mystic_tarot.gui.listeners.SaveCard;

/**
 * Window for creating or editing a card.
 */
public class CreateOrEditCard extends JFrame {
    private static final long serialVersionUID = 1L;

    /**
     * Name of the window.
     */
    private static final String NAME = "New Card | Mystic tarot";

    /**
     * Window's default dimensions.
     */
    private static final Dimension DEFAULT_DIMENSION = new Dimension(350, 450);

    /**
     * Create of new window for creating a card.
     */
    public CreateOrEditCard() {
        super();
        createFrame(null);
    }

    /**
     * Create of new window for editing a card.
     * @param card the card to edit.
     */
    public CreateOrEditCard(Card card) {
        super();
        createFrame(card);
    }

    /**
     * Generate a form for editing a card.
     * @param card the card to edit, null for creating a new one.
     */
    private void createFrame(Card card) {
        setTitle(NAME);
        setSize(DEFAULT_DIMENSION);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel);

        JPanel numberPanel = new JPanel();
        JPanel namePanel   = new JPanel();
        JPanel imagePanel  = new JPanel();

        TextInput       numberInput = new TextInput(card == null ? "" : card.getNumber().toString());
        TextInput       nameInput   = new TextInput(card == null ? "" : card.getName());
        ImageFilePicker imageInput  = new ImageFilePicker(null);

        if (card != null) imageInput.setSelectedFile(card.getImage());

        JLabel numberLabel = new JLabel("Number");
        JLabel nameLabel   = new JLabel("Name");
        JLabel imageLabel  = new JLabel("Image");

        numberPanel.add(numberLabel);
        numberPanel.add(numberInput);

        namePanel.add(nameLabel);
        namePanel.add(nameInput);

        imagePanel.add(imageLabel);
        imagePanel.add(imageInput);

        panel.add(numberPanel);
        panel.add(namePanel);
        panel.add(imagePanel);

        JPanel controlsPanel = new JPanel();

        Button createCardButton = new Button(card == null ? "Create" : "Save");

        controlsPanel.add(createCardButton);

        panel.add(controlsPanel);

        if (card == null) {
            createCardButton.addActionListener(new SaveCard(
                numberInput,
                nameInput,
                imageInput,
                this
            ));
        }
        else {
            createCardButton.addActionListener(new SaveCard(
                numberInput,
                nameInput,
                imageInput,
                this,
                card
            ));
        }
    }
}
