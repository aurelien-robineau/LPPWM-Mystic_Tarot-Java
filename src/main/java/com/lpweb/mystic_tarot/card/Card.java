package com.lpweb.mystic_tarot.card;

/**
 * Card of the tarot game.
 */
public class Card 
{
    protected Integer number;
    protected String name;
    protected String description;
    protected String imagePath;

    public Card(Integer number, String name, String description, String imagePath) {
        this.number = number;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
    }

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

    @Override
    public String toString() {
        return
         this.getClass().getSimpleName() + " number " + this.number + ": " + this.name + " -> " + this.description;
    }
}
