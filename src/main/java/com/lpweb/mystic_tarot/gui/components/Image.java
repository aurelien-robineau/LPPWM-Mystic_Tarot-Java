package com.lpweb.mystic_tarot.gui.components;

import java.io.File;
import java.io.IOException;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Dimension;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

public class Image extends JPanel {
    private static final long serialVersionUID = 1L;

    BufferedImage image;

    public Image(File file) {
        try {
            image = ImageIO.read(file);
            setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        } catch (IOException e) {
            // Hide image if an error occurs
            setVisible(false);
        }
    }

    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}