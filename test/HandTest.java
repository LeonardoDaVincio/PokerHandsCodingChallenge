import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    void Hand_constructor_Should_Fail_TooFewCards() {
        Card[] cards = {
                new Card(CardValue.Two, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Four, CardSuit.Club),
                new Card(CardValue.Six, CardSuit.Club)
        };
        assertThrows(IllegalArgumentException.class, () -> new Hand(Arrays.asList(cards)));
    }

    @Test
    void Hand_constructor_Should_Fail_TooMuchCards() {
        Card[] cards = {
                new Card(CardValue.Two, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Four, CardSuit.Club),
                new Card(CardValue.Six, CardSuit.Club),
                new Card(CardValue.Four, CardSuit.Club),
                new Card(CardValue.Six, CardSuit.Club)
        };
        assertThrows(IllegalArgumentException.class, () -> new Hand(Arrays.asList(cards)));
    }

    @Test
    void Hand_getTieCompareOrder_For_HighCard() {
        Card[] cards = {
                new Card(CardValue.Two, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Four, CardSuit.Club),
                new Card(CardValue.Six, CardSuit.Club),
                new Card(CardValue.Seven, CardSuit.Heart)
        };

        Hand hand = new Hand(Arrays.asList(cards));
        List<CardValue> expected = Arrays.asList(
                CardValue.Seven,
                CardValue.Six,
                CardValue.Four,
                CardValue.Three,
                CardValue.Two
        );

        List<CardValue> actual = hand.getTieCompareOrder();
        for (int i = 0; i < expected.size(); i++) {
            assert(expected.get(i).getValue() == actual.get(i).getValue());
        }
    }

    @Test
    void Hand_getTieCompareOrder_For_Pair() {
        Card[] cards = {
                new Card(CardValue.Two, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Four, CardSuit.Club),
                new Card(CardValue.Six, CardSuit.Club),
                new Card(CardValue.Two, CardSuit.Heart)
        };
        Hand hand = new Hand(Arrays.asList(cards));
        List<CardValue> expected = Arrays.asList(
                CardValue.Two,
                CardValue.Six,
                CardValue.Four,
                CardValue.Three
        );

        List<CardValue> actual = hand.getTieCompareOrder();
        for (int i = 0; i < expected.size(); i++) {
            assert(expected.get(i).getValue() == actual.get(i).getValue());
        }
    }

    @Test
    void Hand_getTieCompareOrder_For_TwoPairs() {
        Card[] cards = {
                new Card(CardValue.Two, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Four, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Heart),
                new Card(CardValue.Two, CardSuit.Heart)
        };
        Hand hand = new Hand(Arrays.asList(cards));
        List<CardValue> expected = Arrays.asList(
                CardValue.Three,
                CardValue.Two,
                CardValue.Four
        );

        List<CardValue> actual = hand.getTieCompareOrder();
        for (int i = 0; i < expected.size(); i++) {
            assert(expected.get(i).getValue() == actual.get(i).getValue());
        }
    }

    @Test
    void Hand_getTieCompareOrder_For_ThreeOfAKind() {
        Card[] cards = {
                new Card(CardValue.Two, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Four, CardSuit.Club),
                new Card(CardValue.Two, CardSuit.Diamond),
                new Card(CardValue.Two, CardSuit.Heart)
        };
        Hand hand = new Hand(Arrays.asList(cards));
        List<CardValue> expected = Arrays.asList(
                CardValue.Two,
                CardValue.Four,
                CardValue.Three
        );

        List<CardValue> actual = hand.getTieCompareOrder();
        for (int i = 0; i < expected.size(); i++) {
            assert(expected.get(i).getValue() == actual.get(i).getValue());
        }
    }

    @Test
    void Hand_getTieCompareOrder_For_Straight() {
        Card[] cards = {
                new Card(CardValue.Six, CardSuit.Heart),
                new Card(CardValue.Two, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Four, CardSuit.Club),
                new Card(CardValue.Five, CardSuit.Diamond)
        };
        Hand hand = new Hand(Arrays.asList(cards));
        List<CardValue> expected = Arrays.asList(
                CardValue.Six,
                CardValue.Five,
                CardValue.Four,
                CardValue.Three,
                CardValue.Two
        );

        List<CardValue> actual = hand.getTieCompareOrder();
        for (int i = 0; i < expected.size(); i++) {
            assert(expected.get(i).getValue() == actual.get(i).getValue());
        }
    }

    @Test
    void Hand_getTieCompareOrder_For_Flush() {
        Card[] cards = {
                new Card(CardValue.Six, CardSuit.Club),
                new Card(CardValue.Two, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Four, CardSuit.Club),
                new Card(CardValue.Ace, CardSuit.Club)
        };
        Hand hand = new Hand(Arrays.asList(cards));
        List<CardValue> expected = Arrays.asList(
                CardValue.Ace,
                CardValue.Six,
                CardValue.Four,
                CardValue.Three,
                CardValue.Two
        );

        List<CardValue> actual = hand.getTieCompareOrder();
        for (int i = 0; i < expected.size(); i++) {
            assert(expected.get(i).getValue() == actual.get(i).getValue());
        }
    }

    @Test
    void Hand_getTieCompareOrder_For_FullHouse() {
        Card[] cards = {
                new Card(CardValue.Six, CardSuit.Club),
                new Card(CardValue.Six, CardSuit.Diamond),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Diamond),
                new Card(CardValue.Three, CardSuit.Spade)
        };
        Hand hand = new Hand(Arrays.asList(cards));
        List<CardValue> expected = Arrays.asList(
                CardValue.Three,
                CardValue.Six
        );

        List<CardValue> actual = hand.getTieCompareOrder();
        for (int i = 0; i < expected.size(); i++) {
            assert(expected.get(i).getValue() == actual.get(i).getValue());
        }
    }

    @Test
    void Hand_getTieCompareOrder_For_FourOfAKind() {
        Card[] cards = {
                new Card(CardValue.Six, CardSuit.Club),
                new Card(CardValue.Six, CardSuit.Diamond),
                new Card(CardValue.Six, CardSuit.Spade),
                new Card(CardValue.Six, CardSuit.Heart),
                new Card(CardValue.Ace, CardSuit.Club)
        };
        Hand hand = new Hand(Arrays.asList(cards));
        List<CardValue> expected = Arrays.asList(
                CardValue.Six,
                CardValue.Ace
        );

        List<CardValue> actual = hand.getTieCompareOrder();
        for (int i = 0; i < expected.size(); i++) {
            assert(expected.get(i).getValue() == actual.get(i).getValue());
        }
    }

    @Test
    void Hand_getTieCompareOrder_For_StraightFlush() {
        Card[] cards = {
                new Card(CardValue.Six, CardSuit.Club),
                new Card(CardValue.Two, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Four, CardSuit.Club),
                new Card(CardValue.Five, CardSuit.Club)
        };
        Hand hand = new Hand(Arrays.asList(cards));
        List<CardValue> expected = Arrays.asList(
                CardValue.Six,
                CardValue.Five,
                CardValue.Four,
                CardValue.Three,
                CardValue.Two
        );

        List<CardValue> actual = hand.getTieCompareOrder();
        for (int i = 0; i < expected.size(); i++) {
            assert(expected.get(i).getValue() == actual.get(i).getValue());
        }
    }

    @Test
    void Hand_getRank_Should_HighCard() {
        Card[] cards = {
                new Card(CardValue.Two, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Four, CardSuit.Club),
                new Card(CardValue.Six, CardSuit.Club),
                new Card(CardValue.Seven, CardSuit.Heart)
        };

        Hand hand = new Hand(Arrays.asList(cards));
        assert(hand.getRank() == Rank.HighCard);
    }

    @Test
    void Hand_getRank_Should_Pair() {
        Card[] cards = {
                new Card(CardValue.Two, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Four, CardSuit.Club),
                new Card(CardValue.Six, CardSuit.Club),
                new Card(CardValue.Two, CardSuit.Heart)
        };

        Hand hand = new Hand(Arrays.asList(cards));
        assert(hand.getRank() == Rank.Pair);
    }

    @Test
    void Hand_getRank_Should_TwoPairs() {
        Card[] cards = {
                new Card(CardValue.Two, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Four, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Heart),
                new Card(CardValue.Two, CardSuit.Heart)
        };

        Hand hand = new Hand(Arrays.asList(cards));
        assert(hand.getRank() == Rank.TwoPairs);
    }

    @Test
    void Hand_getRank_Should_ThreeOfAKind() {
        Card[] cards = {
                new Card(CardValue.Two, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Four, CardSuit.Club),
                new Card(CardValue.Two, CardSuit.Diamond),
                new Card(CardValue.Two, CardSuit.Heart)
        };

        Hand hand = new Hand(Arrays.asList(cards));
        assert(hand.getRank() == Rank.ThreeOfAKind);
    }

    @Test
    void Hand_getRank_Should_Straight() {
        Card[] cards = {
                new Card(CardValue.Six, CardSuit.Heart),
                new Card(CardValue.Two, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Four, CardSuit.Club),
                new Card(CardValue.Five, CardSuit.Diamond)
        };

        Hand hand = new Hand(Arrays.asList(cards));
        assert(hand.getRank() == Rank.Straight);
    }

    @Test
    void Hand_getRank_Should_Flush() {
        Card[] cards = {
                new Card(CardValue.Six, CardSuit.Club),
                new Card(CardValue.Two, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Four, CardSuit.Club),
                new Card(CardValue.Ace, CardSuit.Club)
        };

        Hand hand = new Hand(Arrays.asList(cards));
        assert(hand.getRank() == Rank.Flush);
    }

    @Test
    void Hand_getRank_Should_FullHouse() {
        Card[] cards = {
                new Card(CardValue.Six, CardSuit.Club),
                new Card(CardValue.Six, CardSuit.Diamond),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Diamond),
                new Card(CardValue.Three, CardSuit.Spade)
        };

        Hand hand = new Hand(Arrays.asList(cards));
        assert(hand.getRank() == Rank.FullHouse);
    }

    @Test
    void Hand_getRank_Should_FourOfAKind() {
        Card[] cards = {
                new Card(CardValue.Six, CardSuit.Club),
                new Card(CardValue.Six, CardSuit.Diamond),
                new Card(CardValue.Six, CardSuit.Spade),
                new Card(CardValue.Six, CardSuit.Heart),
                new Card(CardValue.Ace, CardSuit.Club)
        };

        Hand hand = new Hand(Arrays.asList(cards));
        assert(hand.getRank() == Rank.FourOfAKind);
    }

    @Test
    void Hand_getRank_Should_StraightFlush() {
        Card[] cards = {
                new Card(CardValue.Six, CardSuit.Club),
                new Card(CardValue.Two, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Four, CardSuit.Club),
                new Card(CardValue.Five, CardSuit.Club)
        };

        Hand hand = new Hand(Arrays.asList(cards));
        assert(hand.getRank() == Rank.StraightFlush);
    }
}