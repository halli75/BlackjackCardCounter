// BasicStrategy.java
package blackjack;

public class BasicStrategy {
    public static boolean shouldHit(int playerTotal, int dealerUpCardValue, boolean isSoft, boolean isPair) {
        if (isPair) {
            return shouldHitPairs(playerTotal / 2, dealerUpCardValue);
        } else if (isSoft) {
            return shouldHitSoft(playerTotal, dealerUpCardValue);
        } else {
            return shouldHitHard(playerTotal, dealerUpCardValue);
        }
    }

    private static boolean shouldHitHard(int playerTotal, int dealer) {
        if (playerTotal <= 8) return true;
        if (playerTotal == 9) return !(dealer >= 3 && dealer <= 6);
        if (playerTotal == 10) return dealer >= 10;
        if (playerTotal == 11) return dealer == 11; // assume A = 11
        if (playerTotal == 12) return dealer < 4 || dealer > 6;
        if (playerTotal >= 13 && playerTotal <= 16) return dealer >= 7;
        return false;
    }

    private static boolean shouldHitSoft(int total, int dealer) {
        int softVal = total - 11;
        switch (softVal) {
            case 2: case 3: return dealer > 6;
            case 4: case 5: return dealer > 6;
            case 6:
                if (dealer >= 3 && dealer <= 6) return false;
                return true;
            case 7:
                if (dealer >= 2 && dealer <= 6) return false;
                return dealer >= 9;
            default:
                return false;
        }
    }

    private static boolean shouldHitPairs(int pair, int dealer) {
        return switch (pair) {
            case 2, 3 -> dealer > 7;
            case 4 -> true;
            case 5 -> dealer >= 10;
            case 6 -> dealer > 6;
            case 7 -> dealer > 7;
            case 8 -> false;
            case 9 -> dealer == 7 || dealer >= 10;
            case 10 -> false;
            case 11 -> false; // Aces
            default -> true;
        };
    }
}
