public class Main {
    public static void main(String[] args) {
        System.out.println("PokerGame startet...");
        PokerGame game = new PokerGame();
        game.initialize("Player 1", "Player 2");
        Player winner = game.getWinner();
    }
}

