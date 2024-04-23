import java.util.*;

/**
 * Player class for the hearts game CSC 216 Project
 * @author Aidan Nunn
 */
public class Player {

    /**
     * Instance variable cards in hand
     */
    public static final int CARDS_IN_HAND = 13;

    /**
     * instance variable name of player
     */
    private String name;

    /**
     * intializes overallpoints
     */
    private int overallPoints;

    /**
     * intializes handpoints
     */
    private int handPoints;
    
    /**
     * intializes an array of cards called hand
     */
    private Card[] hand;

    /**
     * intializes integer next
     */
    private int next;

    /**
     * constructor for the player
     * @param name a given players name
     */
    public Player(String name) {
        this.name = name;
        this.overallPoints = 0;
        this.handPoints = 0;
        this.hand = new Card[CARDS_IN_HAND];
        this.next = 0;
    }

    /**
     * gettor method for name
     * @return returns the name
     */
    public String getName() {
        return name;
    }

    /**
     * setter method for card
     * @param card passes in a given card to add
     * @throws IllegalStateException when hand is full
     */
    public void addCard(Card card) {
        if (next >= CARDS_IN_HAND)
            throw new IllegalStateException("Full hand");
    
        hand[next] = card;
        next++;
    
        Card[] temp = Arrays.copyOf(hand, next);
        Arrays.sort(temp);
        System.arraycopy(temp, 0, hand, 0, next);
    }    

    /**
     * gettor method for handpoints
     * @return returns handpoints
     */
    public int getHandPoints() {
        return handPoints;
    }

    /**
     * gettor method for overall points
     * @return returns overall points
     */
    public int getOverallPoints() {
        return overallPoints;
    }

    /**
     * settor method for hand points
     * @param points passes in the desired amount of points to be given
     */
    public void addToHandPoints(int points) {
        handPoints += points;
        overallPoints += points;
    }

    /**
     * gettor method for a card in the hand array
     * @param index desired index for a choosen card
     * @return returns the card from the hand array
     * @throws IllegalArgumentException when the index is invalid
     */
    public Card getCard(int index) {
        if (index < 0 || index >= CARDS_IN_HAND)
            throw new IllegalArgumentException("Invalid index");
    
        return hand[index];
    }

    /**
     * gettor for active card
     * @param suit passes in the suit that was played
     * @return returns a boolean value if the card played does not match with the card in hand
     * @throws IllegalStateException when hand is not full
     */
    public boolean hasActiveCardOfSuit(char suit) {
        if (next < CARDS_IN_HAND)
            throw new IllegalStateException("Hand not full");
    
        boolean hasActiveCard = false;
    
        for (int i = 0; i < CARDS_IN_HAND; i++) {
            if (hand[i] != null && hand[i].getSuit() == suit && !hand[i].hasBeenPlayed()) {
                hasActiveCard = true;
                break;
            }
        }
    
        return hasActiveCard;
    }
    
    /**
     * determines if a hand only have hearts
     * @return returns true if a player only has hearts, returns false otherwise
     * @throws IllegalStateException when hand is not full
     */
    public boolean onlyHasHearts() {
        if (next < CARDS_IN_HAND)
            throw new IllegalStateException("Hand not full");
        
        for (int i = 0; i < CARDS_IN_HAND; i++) {
            if (hand[i] != null && hand[i].getSuit() != 'h')
                return false;
        }
        return true;
    }

    /**
     * gets the card name from a players hand
     * @return returns the cardnames array
     * @throws IllegalStateException when the hand is not full
     */
    public String[] getCardNames() {
        if (next < CARDS_IN_HAND)
            throw new IllegalStateException("Hand not full");
        
        String[] cardNames = new String[CARDS_IN_HAND];
        for (int i = 0; i < CARDS_IN_HAND; i++) {
            if (hand[i] != null)
                cardNames[i] = hand[i].toString();
        }
        return cardNames;
    }

    /**
     * a method that returns a string representation
     * @return a string representation
     */
    public String toString() {
        return name + ": " + handPoints;
    }

    /**
     * refreshes the players hand for the next round
     */
    public void dumpCards() {
        Arrays.fill(hand, null);
        next = 0;
    }

    /**
     * resets the players hand points for the next round
     */
    public void resetHandPoints() {
        handPoints = 0;

    }

    /**
     * This method determines the card that a computer player will play in the
     * current round (trick). This method assumes the hand has been sorted and
     * is in order by suits - clubs, diamond, spades, hearts - and the values of
     * the cards in each suit are ordered from lowest to highest value.
     * @param startingCard the card that started the round
     * @param isFirstRound whether or not this is the first round of a hand
     * @param heartsStarted whether or not hearts are in play at this point in the hand
     * @return the card that will be played
     * @throws IllegalStateException if there is no unplayed card in the hand
     */
    public Card getMove(Card startingCard, boolean isFirstRound, boolean heartsStarted) {
        
        //If this is the first round (trick) and the computer player has the 2 of Clubs, 
        //they must play it. If the player has the 2 of Clubs, it should be the first 
        //card in their (sorted) hand
        if (isFirstRound && startingCard == null && 
            hand[0].getSuit() == Card.CLUBS && hand[0].getValue() == 2) {
            hand[0].setPlayed(true);
            return hand[0];
        } 
        
        //If an initial card was played, the computer player must follow suit
        //and will play the lowest card in that suit
        if (startingCard != null) {
            char currentSuit = startingCard.getSuit(); 
            for (int i = 0; i < CARDS_IN_HAND; i++) {
                if (!hand[i].hasBeenPlayed() && hand[i].getSuit() == currentSuit) {
                    hand[i].setPlayed(true);
                    return hand[i];
                }
            }
        }

        //If no card with a matching suit was found and it's not the first round(trick)
        //the computer player will play the Queen of Spades if they have it and it
        //hasn't been played yet
        //If not, they will play their highest valued Heart, if they have one and it
        //hasn't been played yet
        if (startingCard != null && !isFirstRound) {
            //Look for the Queen of Spades
            for (int i = 0; i < CARDS_IN_HAND; i++) {
                if (hand[i].getSuit() == Card.SPADES && hand[i].getValue() == Card.QUEEN_VALUE &&
                    !hand[i].hasBeenPlayed()) {
                    hand[i].setPlayed(true);
                    return hand[i];
                }
            }
            for (int i = CARDS_IN_HAND - 1; i >= 0; i--) {
                if (hand[i].getSuit() == Card.HEARTS && !hand[i].hasBeenPlayed()) {
                    hand[i].setPlayed(true);
                    return hand[i];
                }
            }
        }

        //If no card has been found yet, the first card that hasn't been played
        //in the sorted hand will be played
        for (int i = 0; i < hand.length; i++) {
            if (!hand[i].hasBeenPlayed()) {
                
                //A club or diamond is always valid
                if (hand[i].getSuit() == Card.CLUBS ||
                    hand[i].getSuit() == Card.DIAMONDS) {
                    hand[i].setPlayed(true);
                    return hand[i];
                }
                
                //All spades other than the queen are valid
                //The queen of spades can be played if it's not the
                //first round(trick)
                if (hand[i].getSuit() == Card.SPADES) {
                    if (hand[i].getValue() != Card.QUEEN_VALUE) {
                        hand[i].setPlayed(true);
                        return hand[i];
                    }
                    else if (!isFirstRound) {
                        hand[i].setPlayed(true);
                        return hand[i];
                    }
                }
                
                //A heart is valid if it's not the first round 
                //and either hearts have been played previously or
                //the player only has hearts. NOTE: This case would 
                //occur when the player is playing the initial card
                //in the trick
                if (hand[i].getSuit() == Card.HEARTS && !isFirstRound && 
                    (heartsStarted || onlyHasHearts())) {
                    hand[i].setPlayed(true);
                    return hand[i];
                }    
            }
        }
        //No card found so far - this could happen in the unlikely situation
        //that the player's hand contained only hearts or
        //the Queen of spades with the rest of the cards being hearts
        //Return the first unplayed card        
        for (int i = 0; i < hand.length; i++) {
            if (!hand[i].hasBeenPlayed()) {
                hand[i].setPlayed(true);
                return hand[i];
            }
        }
        //No unplayed card in hand
        throw new IllegalStateException("No unplayed card in hand");

    }

}
