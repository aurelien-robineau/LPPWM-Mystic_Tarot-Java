package com.lpweb.mystic_tarot.gui.listeners;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.lpweb.mystic_tarot.card.Card;
import com.lpweb.mystic_tarot.card.CardManager;
import com.lpweb.mystic_tarot.gui.GuiManager;
import com.lpweb.mystic_tarot.gui.components.ImageFilePicker;
import com.lpweb.mystic_tarot.gui.components.Validable;
import com.lpweb.mystic_tarot.gui.components.TextInput;

/**
 * Listener for saving an existing card.
 */
public class SaveCard implements ActionListener {
    /**
     * Input to read for the new card number.
     */
    private TextInput numberInput;

    /**
     * Input to read for the new card name.
     */
    private TextInput nameInput;

    /**
     * Input to read for the new card description.
     */
    private TextInput descriptionInput;

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

    /**
     * Save a card and add it the the GUI.
     * @param numberInput the input to read for the new card number.
     * @param nameInput the input to read for the new card name.
     * @param descriptionInput the input to read for the new card description.
     * @param imageInput the file picker for the new image.
     * @param parentFrame the frame from wich the event has been trigerred.
     * @param oldCard the card to replace by the new one. Set to {@code null} for creating a new one.
     */
    public SaveCard(
        TextInput        numberInput,
        TextInput        nameInput,
        TextInput        descriptionInput,
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

    /**
     * Create and save a new card and add it the the GUI.
     * This constructor allows to create a new card whitout specifying the
     * {@code oldCard} to be {@code null}.
     * @param numberInput the input to read for the new card number.
     * @param nameInput the input to read for the new card name.
     * @param descriptionInput the input to read for the new card description.
     * @param imageInput the file picker for the new image.
     * @param parentFrame the frame from wich the event has been trigerred.
     */
    public SaveCard(
        TextInput        numberInput,
        TextInput        nameInput,
        TextInput        descriptionInput,
        ImageFilePicker  imageInput,
        JFrame           parentFrame
    ) {
        this(numberInput, nameInput, descriptionInput, imageInput, parentFrame, null);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        ArrayList<Validable> invalidInputs = getInvalidInputs();

        // If there are invalid inputs, display errors and cancel saving.
        if (invalidInputs.size() != 0) {
            for (Validable input : invalidInputs) {
                input.setError();
            }
            return;
        }

        // Create new card from inputs.
        Card card = new Card(
            Integer.parseInt(numberInput.getText()),
            nameInput.getText(),
            descriptionInput.getText(),
            imageInput.getSelectedFile()
        );

        try {
            // New card if oldCard is null, else replace card
            if (oldCard == null) {
                CardManager.getInstance().saveNewCard(card);
                GuiManager.getInstance().addCard(card, BorderLayout.CENTER);
            }
            else {
                CardManager.getInstance().saveExistingCard(oldCard, card);
                GuiManager.getInstance().refreshCardPanel(oldCard, card);
            }

            parentFrame.dispose();

        } catch (Exception e) {
            numberInput.setError();
        }
    }

    /**
     * Gets a list of the invalid card inputs.
     * @return the list of the invalid card inputs.
     */
    private ArrayList<Validable> getInvalidInputs() {
        ArrayList<Validable> invalidInputs = new ArrayList<>();

        if (numberInput.getText().equals(""))
            invalidInputs.add(numberInput);
        if (nameInput.getText().equals(""))
            invalidInputs.add(nameInput);
        if (descriptionInput.getText().equals(""))
            invalidInputs.add(descriptionInput);
        if (imageInput.getSelectedFile() == null)
            invalidInputs.add(imageInput);

        return invalidInputs;
    }
}
