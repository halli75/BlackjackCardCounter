// Player.java
package blackjack;

import java.util.*;

public class Player {
    protected List<Card> hand = new ArrayList<>();

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public int handValue() {
        int total = 0;
        int aces = 0;
        for (Card c : hand) {
            total += c.getValue();
            if (c.getRank().equals("A")) aces++;
        }
        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }
        return total;
    }

    public void clearHand() {
        hand.clear();
    }
}