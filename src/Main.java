import java.util.Arrays;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        //Start eines Pokerspiels mit zufällig verteilten Karten
        System.out.println("PokerGame startet...");
        PokerGame game = new PokerGame();
        game.initialize("Player 1", "Player 2");
        Optional<Player> winner = game.getWinner();
        if (winner.isPresent()) {
            System.out.println(winner.get().getName() + " gewinnt!");
        } else {
            System.out.println("Die 0.0087% Wahrscheinlichkeit eines tatsächlichen Gleichstands ist eingetreten.");
        }

        //Start eines Pokerspiels mit definierten Karten
        System.out.println("PokerGame startet...");
        PokerGame game2 = new PokerGame();
        Card[] cards1 = {
                new Card(CardValue.Six, CardSuit.Heart),
                new Card(CardValue.Two, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Heart),
                new Card(CardValue.Four, CardSuit.Club),
                new Card(CardValue.Five, CardSuit.Diamond)
        }; // Straight

        Card[] cards2 = {
                new Card(CardValue.Two, CardSuit.Heart),
                new Card(CardValue.Five, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Spade),
                new Card(CardValue.Three, CardSuit.Diamond)
        }; // Three of a kind
        game2.initialize(Arrays.asList(cards1), Arrays.asList(cards2),"Player 1", "Player 2");
        Optional<Player> winner2 = game2.getWinner();
        if (winner2.isPresent()) {
            System.out.println(winner2.get().getName() + " gewinnt!");
        } else {
            System.out.println("Die 0.0087% Wahrscheinlichkeit eines tatsächlichen Gleichstands ist eingetreten.");
        }

        //Start eines Pokerspiels mit definierten Karten (Gleichstand)
        System.out.println("PokerGame startet...");
        PokerGame game3 = new PokerGame();
        Card[] cards3 = {
                new Card(CardValue.Two, CardSuit.Heart),
                new Card(CardValue.Three, CardSuit.Heart),
                new Card(CardValue.Four, CardSuit.Heart),
                new Card(CardValue.Five, CardSuit.Heart),
                new Card(CardValue.Six, CardSuit.Heart)
        };

        Card[] cards4 = {
                new Card(CardValue.Two, CardSuit.Club),
                new Card(CardValue.Three, CardSuit.Club),
                new Card(CardValue.Four, CardSuit.Club),
                new Card(CardValue.Five, CardSuit.Club),
                new Card(CardValue.Six, CardSuit.Club)
        };

        game3.initialize(Arrays.asList(cards3), Arrays.asList(cards4), "Player 1", "Player 2");
        Optional<Player> winner3 = game3.getWinner();
        if (!winner3.isPresent()) {
            System.out.println("Die 0.0087% Wahrscheinlichkeit (Quelle: Google) eines tatsächlichen Gleichstands ist eingetreten.");
        }

    }
}

