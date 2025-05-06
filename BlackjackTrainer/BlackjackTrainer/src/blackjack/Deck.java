// Deck.java
package blackjack;

import java.util.*;

public class Deck {
    private final List<Card> cards = new ArrayList<>();
    private final Random random = new Random();

    public Deck(int numDecks) {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        int[] values =  {  2 ,  3 ,  4 ,  5 ,  6 ,  7 ,  8 ,  9 ,  10 , 10 , 10 , 10 , 11};

        for (int d = 0; d < numDecks; d++) {
            for (Card.Suit suit : Card.Suit.values()) {
                for (int i = 0; i < ranks.length; i++) {
                    cards.add(new Card(suit, ranks[i], values[i]));
                }
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards, random);
    }

    public Card dealCard() {
        return cards.remove(0);
    }

    public int cardsRemaining() {
        return cards.size();
    }

    public double percentDealt() {
        return 1.0 - ((double) cards.size() / (52.0 * (cards.size() / 52)));
    }
}