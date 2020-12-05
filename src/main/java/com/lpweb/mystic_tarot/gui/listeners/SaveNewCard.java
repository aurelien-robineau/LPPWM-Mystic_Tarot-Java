package com.lpweb.mystic_tarot.gui.listeners;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;

import com.lpweb.mystic_tarot.card.Card;
import com.lpweb.mystic_tarot.card.CardManager;
import com.lpweb.mystic_tarot.gui.components.Input;

public class SaveNewCard implements ActionListener {
    private Input  numberInput;
    private Input  nameInput;
    private Input  descriptionInput;
    private Input  imageInput;
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
    public void actionPerformed(ActionEvent e) {
        Card card = new Card(
            Integer.parseInt(numberInput.getText()),
            nameInput.getText(),
            descriptionInput.getText(),
            imageInput.getText()
        );

        CardManager.getInstance().saveNewCard(card);

        parentFrame.dispose();
    }
}