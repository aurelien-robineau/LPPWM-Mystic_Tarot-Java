package com.lpweb.mystic_tarot.card;

/**
 * Card of the tarot game.
 */
public class Card 
{
    private Integer number;
    private String description;

    public Card(Integer number, String description) {
        this.number = number;
        this.description = description;
    }

    public Integer getNumber() {
        return this.number;
    };

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return
         this.getClass().getSimpleName() + " number " + this.number + ": " + this.description;
    }
}
