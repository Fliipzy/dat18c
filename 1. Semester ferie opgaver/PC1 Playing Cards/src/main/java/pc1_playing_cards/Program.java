package pc1_playing_cards;

import java.util.Scanner;

import pc1_playing_cards.utils.Card;
import pc1_playing_cards.utils.Deck;
import pc1_playing_cards.utils.Card.Faction;
import pc1_playing_cards.utils.Card.Rank;

public final class Program 
{
    public static void main(String[] args) 
    {
        Deck cardDeck = new Deck();
        Scanner scanner = new Scanner(System.in);

        for (int f = 0; f < 4; f++) {
            for (int r = 0; r < 13; r++) {

                Card card = new Card(Faction.getFaction(f), Rank.getRank(r));
                cardDeck.insertCard(card);
            }
        }

        System.out.println(
            "Cards succesfully added to the deck!\n\n" +
            "How would you like to have your cards displayed?\n" +
            "1. In nice order (Ace to King)\n" +
            "2. Shuffled (Random order)\n");

        System.out.print("> ");
        String choice = scanner.next();

        while (true) {

            if (!choice.equals("1") && !choice.equals("2")) {
                System.out.println("1 or 2 ?\n");
                System.out.print("> ");
                choice = scanner.next();
                continue;
            }
            
            if (choice.equals("1")) {
                for (int i = 0; i < 52; i++) 
                {
                    System.out.println(cardDeck.drawTopMostCard().toString());
                    if ((i+1) % 13 == 0) 
                    {
                        System.out.println("\n" + cardDeck.size() + " cards left!\n");
                    }
                }
                break;
            }
            if (choice.equals("2")) {

                cardDeck.shuffle();
                for (int i = 0; i < 52; i++) 
                {
                    System.out.println(cardDeck.drawTopMostCard().toString());
                    if ((i+1) % 13 == 0) 
                    {
                        System.out.println("\n" + cardDeck.size() + " cards left!\n");
                    }
                }
                break;
            }
        }
        scanner.close();
    }
}
