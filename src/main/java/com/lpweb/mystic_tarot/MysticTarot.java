package com.lpweb.mystic_tarot;

import com.lpweb.mystic_tarot.card.Card;
import com.lpweb.mystic_tarot.card.CardCreator;


/**
 * Mystic tarot game.
 */
public class MysticTarot 
{
    public static void main( String[] args )
    {
        CardCreator cardCreator = new CardCreator();
        Card card = cardCreator.createCard();
        System.out.println(card);
    }
}
