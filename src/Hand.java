import java.util.*;
import java.util.stream.Collectors;

public class Hand {
    private final List<Card> cards;

    public List<CardValue> getTieCompareOrder() {
        return tieCompareOrder;
    }

    private final List<CardValue> tieCompareOrder;

    public Rank getRank() {
        return rank;
    }

    private final Rank rank;

    public Hand(List<Card> cards) {
        if (cards.size() != 5) {
            throw new IllegalArgumentException("Es müssen 5 Karten auf einer Hand sein.");
        }
        this.cards = cards;
        this.rank = getHandRank();
        this.tieCompareOrder = getHandTieCompareOrder(cards);
    }

    private List<CardValue> getHandTieCompareOrder(List<Card> cards) {
        Map<CardValue, List<Card>> cardsGrouped = cards.stream().collect(Collectors.groupingBy(Card::getValue));
        List<List<Card>> cardsGroupedList = new ArrayList<>(cardsGrouped.values());
        cardsGroupedList.sort(new ListComparator<>());
        List<CardValue> cardValueList = cardsGroupedList.stream().map(g -> g.get(0).getValue()).collect(Collectors.toList());
        Collections.reverse(cardValueList);
        return cardValueList;
    }


    private Rank getHandRank() {
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
     * @return Wahr, wenn die Hand m n-letten enthält, sonst falsch
     */
    private boolean containsMNlets(int m, int n) {
        Map<CardValue, List<Card>> cardsGrouped = cards.stream().collect(Collectors.groupingBy(Card::getValue));
        return cardsGrouped.values().stream().filter(g -> g.size() >= n).count() >= m;
    }

    private boolean isStraight() {
        List<Integer> cardNumericalValues = cards.stream().map(c -> c.getValue().getValue()).sorted().collect(Collectors.toList());

        for (int i = 0; i < cardNumericalValues.size() - 1; i++) {
            int value = cardNumericalValues.get(i);
            int nextValue = cardNumericalValues.get(i + 1);

            if (!(value + 1 == nextValue)){
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

