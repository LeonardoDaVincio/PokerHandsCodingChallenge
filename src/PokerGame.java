import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PokerGame {
    private Hand playerOne;
    private Hand playerTwo;
    private final Stack<Card> cardStack;
    
    public PokerGame() {
        List<Card> tempList = new ArrayList<>();
        for (CardSuit suit : CardSuit.values()) {
            for (CardValue value : CardValue.values()) {
                tempList.add(new Card(value, suit));
            }
        }
        Collections.shuffle(tempList);
        cardStack = new Stack<>();
        cardStack.addAll(tempList);
    }

    public void assignHands() {
        List<Card> tempListOne = new ArrayList<>();
        List<Card> tempListTwo = new ArrayList<>();
        for (int i=0; i < 5; i++) {
            tempListOne.add(cardStack.pop());
            tempListTwo.add(cardStack.pop());
        }
        playerOne = new Hand(tempListOne);
        playerTwo = new Hand(tempListTwo);
    }

    public void assignHands(List<Card> handOne, List<Card> handTwo) {
        for (Card card : Stream.concat(handOne.stream(), handTwo.stream()).collect(Collectors.toList())) {
            if (!cardStack.remove(card)){
                throw new IllegalArgumentException("Unzulässige Kartenverteilung.");
            }
        }
        playerOne = new Hand(handOne);
        playerTwo = new Hand(handTwo);
    }
}
