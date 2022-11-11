
public class Card implements Comparable<Card> {
    private final CardValue value;
    private final CardSuit suit;

    public Card(CardValue value, CardSuit suit) {
        this.value = value;
        this.suit = suit;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public CardValue getValue() {
        return value;
    }

    @Override
    public int compareTo(Card c) {
        if (value.getValue() > c.value.getValue()) {
            return 1;
        } else if (value.getValue() < c.value.getValue()) {
            return -1;
        } else {
            return 0;
        }
    }
}

