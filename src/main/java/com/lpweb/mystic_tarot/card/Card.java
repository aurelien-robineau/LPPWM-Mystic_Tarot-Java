package com.lpweb.mystic_tarot.card;

/**
 * Card of the tarot game.
 */
public class Card 
{
    private Integer number;
    private String name;
    private String description;

    public Card(Integer number, String name, String description) {
        this.number = number;
        this.name = name;
        this.description = description;
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

    @Override
    public String toString() {
        return
         this.getClass().getSimpleName() + " number " + this.number + ": " + this.name + " -> " + this.description;
    }
}
