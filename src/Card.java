import java.util.Objects;

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
        return Integer.compare(value.getValue(), c.value.getValue());
    }

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        Card card = (Card) o;
        // field comparison
        return Objects.equals(value, card.value)
                && Objects.equals(suit, card.suit);
    }
}

