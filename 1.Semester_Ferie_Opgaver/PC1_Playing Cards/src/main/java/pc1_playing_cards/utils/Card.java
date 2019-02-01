package pc1_playing_cards.utils;

public class Card 
{
    private Faction faction;
    private Rank rank;

    public Card(Faction faction, Rank rank) 
    {
        this.rank = rank;
        this.faction = faction;
    }

    public Faction getFaction() 
    {
        return faction;
    }

    public Rank getRank() 
    {
        return rank;
    }

    public enum Faction
    {
        HEARTS(0),
        DIAMONDS(1),
        CLOVERS(2),
        SPADES(3);

        private final int id;

        Faction(int id) 
        { 
            this.id = id;
        }

        public int getValue() 
        { 
            return id; 
        }

        public static Faction getFaction(int id)
        {
            for (Faction faction : values()) {
                if (faction.id == id) {
                    return faction;
                }
            }
            return null;
        }
    }

    public enum Rank
    {
        ACE(0),
        TWO(1),
        THREE(2),
        FOUR(3),
        FIVE(4),
        SIX(5),
        SEVEN(6),
        EIGHT(7),
        NINE(8),
        TEN(9),
        JACK(10),
        QUEEN(11),
        KING(12);

        private final int id;

        Rank(int id) 
        { 
            this.id = id;
        }
        public int getValue()
        { 
            return id;
        }

        public static Rank getRank(int id)
        {
            for (Rank rank : values()) {
                if (rank.id == id) {
                    return rank;
                }
            }
            return null;
        }
    }

    @Override
    public String toString()
    {
        String cardName = null;

        switch (rank) 
        {
            case ACE:
            cardName = "Ace";
            break;
            case TWO:
            cardName = "Two";
            break;
            case THREE:
            cardName = "Three";
            break;
            case FOUR:
            cardName = "Four";
            break;
            case FIVE:
            cardName = "Five";
            break;
            case SIX:
            cardName = "Six";
            break;
            case SEVEN:
            cardName = "Seven";
            break;
            case EIGHT:
            cardName = "Eight";
            break;
            case NINE:
            cardName = "Nine";
            break;
            case TEN:
            cardName = "Ten";
            break;
            case JACK:
            cardName = "Jack";
            break;
            case QUEEN:
            cardName = "Queen";
            break;
            case KING:
            cardName = "King";
            break;

            default:
                break;
        }

        cardName += " Of ";

        switch (faction) {
            case HEARTS:
                cardName += "Hearts";
                break;
            case DIAMONDS:
                cardName += "Diamonds";
                break;
            case CLOVERS:
                cardName += "Clovers";
                break;
            case SPADES:
                cardName += "Spades";
                break;
        
            default:
                break;
        }
        return cardName;
    }
}