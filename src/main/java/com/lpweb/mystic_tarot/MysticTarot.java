package com.lpweb.mystic_tarot;

import com.lpweb.mystic_tarot.card.CardManager;


/**
 * Mystic tarot game.
 */
public class MysticTarot 
{
    public static void main( String[] args )
    {
        CardManager cardManager = CardManager.getInstance();
        cardManager.open();
    }
}
