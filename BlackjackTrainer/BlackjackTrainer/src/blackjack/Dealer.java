// Dealer.java
package blackjack;

public class Dealer extends Player {
    public boolean shouldHit() {
        return handValue() < 17;
    }
}