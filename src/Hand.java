import java.util.List;

public class Hand {
    private final List<Card> cards;

    public Hand(List<Card> cards) {
        if (cards.size() != 5) {
            throw new IllegalArgumentException("Es müssen 5 Karten auf einer Hand sein.");
        }
        this.cards = cards;
    }
}

