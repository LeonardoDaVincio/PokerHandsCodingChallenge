import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Hand {
    private final List<Card> cards;

    public Rank getRank() {
        return rank;
    }

    private final Rank rank;

    public Hand(List<Card> cards) {
        if (cards.size() != 5) {
            throw new IllegalArgumentException("Es müssen 5 Karten auf einer Hand sein.");
        }
        this.cards = cards;
        rank = initializeRank();
    }

    private Rank initializeRank() {
        if (isStraightFlush()) return Rank.StraightFlush;
        if (containsMNlets(1, 4)) return Rank.FourOfAKind;
        if (isFullHouse()) return Rank.FullHouse;
        if (isFlush()) return Rank.Flush;
        if (isStraight()) return Rank.Straight;
        if (containsMNlets(1,3)) return Rank.ThreeOfAKind;
        if (containsMNlets(2,2)) return Rank.TwoPairs;
        if (containsMNlets(1,2)) return Rank.Pair;
        else return Rank.HighCard;
    }

    /**
     * Prüft, ob eine Hand eine bestimmte Anzahl (m) n-letten (Dubletten, Tripletten, ...) enthält.
     * @param m Anzahl an n-letten
     * @param n Anzahl an gleichen Karten
     * @return
     */
    private boolean containsMNlets(int m, int n) {
        Map<CardValue, List<Card>> cardsGrouped = cards.stream().collect(Collectors.groupingBy(c -> c.getValue()));
        return cardsGrouped.values().stream().filter(g -> g.size() >= n).collect(Collectors.toList()).size() >= m;
    }

    private boolean isStraight() {
        List<Integer> cardNumericalValues = cards.stream().map(c -> c.getValue().getValue()).collect(Collectors.toList());
        Collections.sort(cardNumericalValues);

        for (int i = 0; i < cardNumericalValues.size() - 1; i++) {
            if (!(cardNumericalValues.get(i) == cardNumericalValues.get(i + 1) + 1)){
                return false;
            }
        }
        return true;
    }

    private boolean isFlush() {
        return cards.stream().allMatch(c -> cards.get(0).getSuit() == c.getSuit());
    }

    private boolean isFullHouse() {
        return containsMNlets(2,2) && containsMNlets(1,3);
    }

    private boolean isStraightFlush() {
        return isStraight() && isFlush();
    }

}

