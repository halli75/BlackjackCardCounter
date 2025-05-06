// Card.java
package blackjack;

public class Card {
    public enum Suit { HEARTS, DIAMONDS, CLUBS, SPADES }

    private final Suit suit;
    private final String rank;
    private final int value;

    public Card(Suit suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }

    public int getHiLoValue() {
        if (value >= 2 && value <= 6) return 1;
        if (value >= 7 && value <= 9) return 0;
        return -1;
    }

    @Override
    public String toString() {
        return rank + " of " + suit.name();
    }
}
