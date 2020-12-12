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
     * Directory the cards are saved in.
     */
    public static final String cardSavePath = "./data/cards/serialized/";

    /**
     * Directory the cards images are saved in.
     */
    public static final String cardImageDirectory = "./data/cards/images/";

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
     * The path to the image of the card.
     */
    protected String  imagePath;

    /**
     * The image of the card.
     */
    protected transient File image;

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
    public Card(Integer number, String name, File image) {
        this.number      = number;
        this.name        = name;
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
    public Card(Integer number, String name, String imagePath) {
        this(number, name, new File(imagePath));
        this.imagePath = imagePath;
    }

    /**
     * Constructs a new card from an existing card.
     * @param card the card copied to the new card.
     */
    public Card(Card card) {
        this.number      = card.number;
        this.name        = card.name;
        this.imagePath   = card.imagePath;
        this.image       = card.image;
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

    public String getImagePath() {
        return this.imagePath;
    }

    public File getImage() {
        return this.image;
    }

    //--------------------------------------------------------------------------
    // Public methods
    //--------------------------------------------------------------------------

    /**
     * Copies another card data into the card.
     * @param card
     */
    public void refreshFrom(Card card) {
        number      = card.number;
        name        = card.name;
        imagePath   = card.imagePath;
        image       = card.image;
    }

    //--------------------------------------------------------------------------
    // Protected methods
    //--------------------------------------------------------------------------

    /**
     * Change the card image from a filename.
     * @param filename the filename of the new image.
     */
    protected void setImageFromFilename(String filename) {
        File file = new File(filename);
        image = file;
        imagePath = file.getAbsolutePath();
    }

    //--------------------------------------------------------------------------
    // Public methods
    //--------------------------------------------------------------------------

    @Override
    public String toString() {
        return
         this.getClass().getSimpleName() + " " + this.number + " - " + this.name;
    }
}
