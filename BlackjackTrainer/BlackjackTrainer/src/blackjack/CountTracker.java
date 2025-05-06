// CountTracker.java
package blackjack;

public class CountTracker {
    private int runningCount = 0;

    public void updateCount(Card card) {
        runningCount += card.getHiLoValue();
    }

    public int getRunningCount() {
        return runningCount;
    }

    public void reset() {
        runningCount = 0;
    }
}
