package pc1_playing_cards.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Deck 
{
    private List<Card> cards = new ArrayList<Card>();

    public Deck() 
    {
            
    }

    public int size()
    {
        return cards.size();
    }

    public boolean contains(Card card)
    {
        return cards.contains(card);
    }

    public void insertCard(Card card)
    {
        cards.add(card);
    }

    public Card drawTopMostCard()
    {
        Card topMost = cards.get(0);
        cards.remove(0);
        return topMost;
    }

    public void shuffle()
    {
        Collections.shuffle(cards);
    }
}