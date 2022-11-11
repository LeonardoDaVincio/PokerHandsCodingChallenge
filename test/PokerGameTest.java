import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PokerGameTest {

    @Test
    void PokerGame_initialize_CardStackSizeShouldDecrease() {
        PokerGame game = new PokerGame();
        assert(game.getCardStackSize() == 52);
        game.initialize("test1", "test2");
        assert(game.getCardStackSize() == 42);
    }

    @Test
    void PokerGame_initialize_InvalidCardAssignment() {
        PokerGame game = new PokerGame();
        Card[] cards1 = {
                new Card(CardValue.Six, CardSuit.Heart),
                new Card(CardValue.Two, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Four, CardSuit.Club),
                new Card(CardValue.Five, CardSuit.Diamond)
        };

        Card[] cards2 = {
                new Card(CardValue.Six, CardSuit.Heart),
                new Card(CardValue.Two, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Four, CardSuit.Club),
                new Card(CardValue.Five, CardSuit.Diamond)
        };

        assertThrows(IllegalArgumentException.class, () -> {
            game.initialize(Arrays.asList(cards1), Arrays.asList(cards2), "test1", "test2");
        });
    }

    @Test
    void PokerGame_getWinner_PlayerTwo_ShouldWin() {
        PokerGame game = new PokerGame();
        Card[] cards1 = {
                new Card(CardValue.Six, CardSuit.Heart),
                new Card(CardValue.Two, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Heart),
                new Card(CardValue.Four, CardSuit.Club),
                new Card(CardValue.Five, CardSuit.Diamond)
        };

        Card[] cards2 = {
                new Card(CardValue.Five, CardSuit.Heart),
                new Card(CardValue.Five, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Spade),
                new Card(CardValue.Three, CardSuit.Diamond)
        };

        game.initialize(Arrays.asList(cards1), Arrays.asList(cards2), "test1", "test2");
        assert (game.getWinner().isPresent());
        assert (game.getWinner().get().getName() == "test2");
    }

    @Test
    void PokerGame_getWinner_PlayerOne_ShouldWin() {
        PokerGame game = new PokerGame();
        Card[] cards1 = {
                new Card(CardValue.Six, CardSuit.Heart),
                new Card(CardValue.Two, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Heart),
                new Card(CardValue.Four, CardSuit.Club),
                new Card(CardValue.Five, CardSuit.Diamond)
        };

        Card[] cards2 = {
                new Card(CardValue.Two, CardSuit.Heart),
                new Card(CardValue.Five, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Spade),
                new Card(CardValue.Three, CardSuit.Diamond)
        };

        game.initialize(Arrays.asList(cards1), Arrays.asList(cards2), "test1", "test2");
        assert (game.getWinner().isPresent());
        assert (game.getWinner().get().getName() == "test1");
    }

    @Test
    void PokerGame_getWinner_Tie() {
        PokerGame game = new PokerGame();
        Card[] cards1 = {
                new Card(CardValue.Two, CardSuit.Heart),
                new Card(CardValue.Three, CardSuit.Heart),
                new Card(CardValue.Four, CardSuit.Heart),
                new Card(CardValue.Five, CardSuit.Heart),
                new Card(CardValue.Six, CardSuit.Heart)
        };

        Card[] cards2 = {
                new Card(CardValue.Two, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Four, CardSuit.Club),
                new Card(CardValue.Five, CardSuit.Club),
                new Card(CardValue.Six, CardSuit.Club)
        };

        game.initialize(Arrays.asList(cards1), Arrays.asList(cards2), "test1", "test2");
        assert (!game.getWinner().isPresent());
    }
}