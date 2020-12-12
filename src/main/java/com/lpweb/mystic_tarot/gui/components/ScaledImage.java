package com.lpweb.mystic_tarot.gui.components;

import java.io.File;
import java.io.IOException;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

/**
 * The class scaledImage allows to diplay images with the wanted width while
 * adapting the height to the image scale.
 */
public class ScaledImage extends JPanel {
    private static final long serialVersionUID = 1L;

    /**
     * Original image file.
     */
    private BufferedImage originalImage;

    /**
     * Scaled AWT image.
     */
    private Image         scaledImage;

    /**
     * Width for the image.
     */
    private int width;

    /**
     * Creates a new scaled image.
     * @param file the original image file.
     * @param width width to use when displaying the image.
     */
    public ScaledImage(File file, int width) {
        this.width = width;

        try {
            originalImage = ImageIO.read(file);
            scaledImage   = originalImage.getScaledInstance(
                width,
                getScaledHeight(),
                BufferedImage.SCALE_SMOOTH
            );

            setPreferredSize(new Dimension(width, getScaledHeight()));
        } catch (IOException e) {
            // Hide image if an error occurs
            setVisible(false);
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(scaledImage, 0, 0, null);
    }

    /**
     * Get the height for to the wanted width that keeps the original scale.
     * @return the calculated height.
     */
    private int getScaledHeight() {
        float originalWidth  = (float) originalImage.getWidth();
        float originalHeight = (float) originalImage.getHeight();
        float newWidth       = (float) width;

        return (int) (originalHeight * newWidth / originalWidth);
    }
}