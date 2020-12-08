package com.lpweb.mystic_tarot.gui.listeners;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.lpweb.mystic_tarot.card.Card;
import com.lpweb.mystic_tarot.card.CardManager;
import com.lpweb.mystic_tarot.gui.GuiManager;
import com.lpweb.mystic_tarot.gui.components.Input;

/**
 * Listener for saving a new card.
 */
public class SaveNewCard implements ActionListener {
    /**
     * Input to read for the card number.
     */
    private Input numberInput;

    /**
     * Input to read for the card name.
     */
    private Input nameInput;

    /**
     * Input to read for the card description.
     */
    private Input descriptionInput;

    /**
     * Input to read for the card imager path.
     */
    private Input imageInput;

    /**
     * Frame from wich the event has been trigerred.
     * This frame will be closed after saving the card.
     */
    private JFrame parentFrame;

    public SaveNewCard(
        Input  numberInput,
        Input  nameInput,
        Input  descriptionInput,
        Input  imageInput,
        JFrame parentFrame
    ) {
        this.numberInput      = numberInput;
        this.nameInput        = nameInput;
        this.descriptionInput = descriptionInput;
        this.imageInput       = imageInput;
        this.parentFrame      = parentFrame;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // Create the new card.
        Card card = new Card(
            Integer.parseInt(numberInput.getText()),
            nameInput.getText(),
            descriptionInput.getText(),
            imageInput.getText()
        );

        try {
            CardManager.getInstance().saveNewCard(card);
            GuiManager.getInstance().addCard(card, BorderLayout.CENTER);
            parentFrame.dispose();
        } catch (Exception e) {
            numberInput.invalidate();
        }
    }
}