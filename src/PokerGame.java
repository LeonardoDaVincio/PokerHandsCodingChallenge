import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PokerGame {
    private Player playerOne;
    private Player playerTwo;
    private Stack<Card> cardStack;
    
    public PokerGame() {
        initializeCards();
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
        for (Card card : Stream.concat(handOne.stream(), handTwo.stream()).collect(Collectors.toList())) {
            if (!cardStack.remove(card)){
                throw new IllegalArgumentException("Unzulässige Kartenverteilung.");
            }
        }
        Hand playerOneHand = new Hand(handOne);
        Hand playerTwoHand = new Hand(handTwo);
        initializePlayers(playerOneHand, playerTwoHand, playerOneName, playerTwoName);
    }

    public Optional<Player> getWinner() {
        if (playerOne.getHand().getRank().getValue() > playerTwo.getHand().getRank().getValue()) {
            return Optional.of(playerOne);
        } else if (playerTwo.getHand().getRank().getValue() > playerOne.getHand().getRank().getValue()) {
            return Optional.of(playerTwo);
        } else {
            Optional<Hand> winningHand = resolveTie(playerOne.getHand(), playerTwo.getHand());
            if (winningHand.isPresent() && winningHand.get() == playerOne.getHand()) {
                return Optional.of(playerOne);
            } else if (winningHand.isPresent() && winningHand.get() == playerTwo.getHand()) {
                return Optional.of(playerTwo);
            } else {
                return Optional.empty();
            }
        }
    }

    private Optional<Hand> resolveTie(Hand handOne, Hand handTwo) {
        for (int i = 0; i < handOne.getTieCompareOrder().size(); i++){
            if (handOne.getTieCompareOrder().get(i).getValue() > handTwo.getTieCompareOrder().get(i).getValue()) {
                return Optional.of(handOne);
            } else if (handOne.getTieCompareOrder().get(i).getValue() < handTwo.getTieCompareOrder().get(i).getValue()) {
                return Optional.of(handTwo);
            }
        }
        return Optional.empty();
    }

    public int getCardStackSize(){
        return cardStack.size();
    }
}
