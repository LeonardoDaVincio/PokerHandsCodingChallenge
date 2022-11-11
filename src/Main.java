import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println("PokerGame startet...");
        PokerGame game = new PokerGame();
        game.initialize("Player 1", "Player 2");
        Optional<Player> winner = game.getWinner();
        if (winner.isPresent()) {
            System.out.println(winner.get().getName() + " gewinnt!");
        } else {
            System.out.println("Die 0.0087% Wahrscheinlichkeit eines tatsächlichen Gleichstands ist eingetreten.");
        }
    }
}

