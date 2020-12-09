package com.lpweb.mystic_tarot.card;

import java.io.File;
import java.io.Serializable;

/**
 * Card of the mystic tarot.
 * Cards are used to predict the futur.
 */
public class Card implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * The number of the card.
     * The number must be unique.
     */
    protected Integer number;

    /**
     * The name of the card.
     * The name must be unique.
     */
    protected String  name;

    /**
     * The description of the card.
     */
    protected String  description;

    /**
     * The path to the image of the card.
     */
    protected String  imagePath;

    /**
     * The image of the card.
     */
    protected File image;

    //--------------------------------------------------------------------------
    // Constructors
    //--------------------------------------------------------------------------

    /**
     * Constructs a new Card.
     * @param number the unique number of the card
     * @param name the unique name of the card
     * @param description the description of the card
     * @param image the image of the card
     */
    public Card(Integer number, String name, String description, File image) {
        this.number      = number;
        this.name        = name;
        this.description = description;
        this.imagePath   = image.getAbsolutePath();
        this.image       = image;
    }

    /**
     * Constructs a new Card.
     * @param number the unique number of the card
     * @param name the unique name of the card
     * @param description the description of the card
     * @param imagePath the path to the image of the card
     */
    public Card(Integer number, String name, String description, String imagePath) {
        this(number, name, description, new File(imagePath));
    }

    /**
     * Constructs a new card from an existing card.
     * @param card the card copied to the new card.
     */
    public Card(Card card) {
        this.number      = card.number;
        this.name        = card.name;
        this.description = card.description;
        this.imagePath   = card.imagePath;
    }

    //--------------------------------------------------------------------------
    // Getters
    //--------------------------------------------------------------------------

    public Integer getNumber() {
        return this.number;
    };

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImagePath() {
        return this.description;
    }

    public File getImage() {
        return this.image;
    }

    /**
     * Copies another card data into the card.
     * @param card
     */
    public void refreshFrom(Card card) {
        number      = card.number;
        name        = card.name;
        description = card.description;
        imagePath   = card.imagePath;
    }

    //--------------------------------------------------------------------------
    // Public methods
    //--------------------------------------------------------------------------

    @Override
    public String toString() {
        return
         this.getClass().getSimpleName() + " number " + this.number + ": " + this.name + " -> " + this.description;
    }
}
