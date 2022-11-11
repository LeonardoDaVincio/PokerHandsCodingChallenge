public enum Rank {
    HighCard(0), Pair(1), TwoPairs(2), ThreeOfAKind(3), Straight(4), Flush(5), FullHouse(6), FourOfAKind(7), StraightFlush(8);

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
