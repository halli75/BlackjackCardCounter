// GameController.java
package blackjack;

import java.util.*;

public class GameController {
    private final Deck deck;
    private final CountTracker countTracker;
    private final Dealer dealer;
    private final List<Player> players = new ArrayList<>();
    private int handsPlayed = 0;
    private int correctGuesses = 0;

    public GameController(int numDecks, int numPlayers) {
        this.deck = new Deck(numDecks);
        this.countTracker = new CountTracker();
        this.dealer = new Dealer();

        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player());
        }
    }

    public void dealInitialCards() {
        for (int i = 0; i < 2; i++) {
            for (Player p : players) {
                Card c = deck.dealCard();
                p.receiveCard(c);
                countTracker.updateCount(c);
            }
            Card d = deck.dealCard();
            dealer.receiveCard(d);
            countTracker.updateCount(d);
        }
    }

    public void playRound() {
        for (Player p : players) {
            while (BasicStrategy.shouldHit(p.handValue(), dealer.getHand().get(0).getValue(), isSoft(p), isPair(p))) {
                Card c = deck.dealCard();
                p.receiveCard(c);
                countTracker.updateCount(c);
            }
        }
        while (dealer.shouldHit()) {
            Card c = deck.dealCard();
            dealer.receiveCard(c);
            countTracker.updateCount(c);
        }
    }

    public void resetHands() {
        for (Player p : players) {
            p.clearHand();
        }
        dealer.clearHand();
    }

    public boolean isGameOver() {
        return deck.percentDealt() >= 0.85;
    }

    public void submitGuess(int userGuess) {
        handsPlayed++;
        if (userGuess == countTracker.getRunningCount()) {
            correctGuesses++;
        }
    }

    private boolean isSoft(Player p) {
        int value = p.handValue();
        for (Card c : p.getHand()) {
            if (c.getRank().equals("A") && value - c.getValue() <= 10) {
                return true;
            }
        }
        return false;
    }

    private boolean isPair(Player p) {
        List<Card> hand = p.getHand();
        return hand.size() == 2 && hand.get(0).getRank().equals(hand.get(1).getRank());
    }


    public double getScorePercentage() {
        return handsPlayed == 0 ? 0 : (100.0 * correctGuesses / handsPlayed);
    }

    public int getRunningCount() {
        return countTracker.getRunningCount();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
