public class Player {
    public Hand getHand() {
        return hand;
    }

    private final Hand hand;

    public String getName() {
        return name;
    }

    private final String name;

    public Player(Hand hand, String name) {
        this.hand = hand;
        this.name = name;
    }
}
