package com.lpweb.mystic_tarot.gui.listeners;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;

import com.lpweb.mystic_tarot.card.Card;
import com.lpweb.mystic_tarot.card.CardManager;
import com.lpweb.mystic_tarot.gui.GuiManager;
import com.lpweb.mystic_tarot.gui.components.ImageFilePicker;
import com.lpweb.mystic_tarot.gui.components.Input;

/**
 * Listener for saving an existing card.
 */
public class SaveExistingCard implements ActionListener {
    /**
     * Input to read for the new card number.
     */
    private Input numberInput;

    /**
     * Input to read for the new card name.
     */
    private Input nameInput;

    /**
     * Input to read for the new card description.
     */
    private Input descriptionInput;

    /**
     * File picker for the new image.
     */
    private ImageFilePicker imageInput;

    /**
     * Frame from wich the event has been trigerred.
     * This frame will be closed after saving the card.
     */
    private JFrame parentFrame;

    /**
     * Old card to update.
     */
    private Card oldCard;

    public SaveExistingCard(
        Input            numberInput,
        Input            nameInput,
        Input            descriptionInput,
        ImageFilePicker  imageInput,
        JFrame           parentFrame,
        Card             oldCard
    ) {
        this.numberInput      = numberInput;
        this.nameInput        = nameInput;
        this.descriptionInput = descriptionInput;
        this.imageInput       = imageInput;
        this.parentFrame      = parentFrame;

        this.oldCard = oldCard;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // Create new card from inputs.
        Card card = new Card(
            Integer.parseInt(numberInput.getText()),
            nameInput.getText(),
            descriptionInput.getText(),
            imageInput.getSelectedFile()
        );

        try {
            CardManager.getInstance().saveExistingCard(oldCard, card);
            GuiManager.getInstance().refreshCardPanel(oldCard, card);
            parentFrame.dispose();
        } catch (Exception e) {
            numberInput.setError();
        }
    }
}
