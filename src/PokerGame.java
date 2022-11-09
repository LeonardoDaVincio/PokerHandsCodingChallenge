import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PokerGame {
    private Player playerOne;
    private Player playerTwo;
    private Stack<Card> cardStack;
    
    public PokerGame() {
    }

    private void initializeCards() {
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

    private void initializePlayers(Hand playerOneHand, Hand playerTwoHand, String playerOneName, String playerTwoName) {
        playerOne = new Player(playerOneHand, playerOneName);
        playerTwo = new Player(playerTwoHand, playerTwoName);
    }

    public void initialize(String playerOneName, String playerTwoName) {
        initializeCards();
        List<Card> tempListOne = new ArrayList<>();
        List<Card> tempListTwo = new ArrayList<>();
        for (int i=0; i < 5; i++) {
            tempListOne.add(cardStack.pop());
            tempListTwo.add(cardStack.pop());
        }
        Hand playerOneHand = new Hand(tempListOne);
        Hand playerTwoHand = new Hand(tempListTwo);
        initializePlayers(playerOneHand, playerTwoHand, playerOneName, playerTwoName);
    }

    public void initialize(List<Card> handOne, List<Card> handTwo, String playerOneName, String playerTwoName) {
        initializeCards();
        for (Card card : Stream.concat(handOne.stream(), handTwo.stream()).collect(Collectors.toList())) {
            if (!cardStack.remove(card)){
                throw new IllegalArgumentException("Unzulässige Kartenverteilung.");
            }
        }
        Hand playerOneHand = new Hand(handOne);
        Hand playerTwoHand = new Hand(handTwo);
        initializePlayers(playerOneHand, playerTwoHand, playerOneName, playerTwoName);
    }

    public Player getWinner() {
        throw new NotImplementedException();
    }
}
