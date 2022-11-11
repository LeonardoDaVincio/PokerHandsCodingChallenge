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
        if (playerOne.getHand().getRank().getValue() > playerTwo.getHand().getRank().getValue()) {
            return playerOne;
        } else if (playerTwo.getHand().getRank().getValue() > playerOne.getHand().getRank().getValue()) {
            return playerTwo;
        } else {
            Hand winningHand = resolveTie(playerOne.getHand(), playerTwo.getHand());
            if (winningHand == playerOne.getHand()) {
                return playerOne;
            } else if (winningHand == playerTwo.getHand()) {
                return playerTwo;
            } else {
                throw new RuntimeException("Die 0.0087% Wahrscheinlichkeit eines tatsächlichen Gleichstands ist eingetreten.");
            }
        }
    }

    private Hand resolveTie(Hand handOne, Hand handTwo) {
        for (int i = 0; i < handOne.getTieCompareOrder().size(); i++){
            if (handOne.getTieCompareOrder().get(i).getValue() > handTwo.getTieCompareOrder().get(i).getValue()) {
                return handOne;
            } else if (handOne.getTieCompareOrder().get(i).getValue() < handTwo.getTieCompareOrder().get(i).getValue()) {
                return handTwo;
            }
        }
        throw new RuntimeException("Die 0.0087% Wahrscheinlichkeit eines tatsächlichen Gleichstands ist eingetreten.");
    }
}
