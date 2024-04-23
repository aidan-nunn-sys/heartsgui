import java.util.*;
/**
 * Deck Class
 * @author Aidan Nunn
 */

public class Deck {

    /**
     * Delcare a total number of cards in the deck
     */
    public static final int CARDS_IN_DECK = 52;

    /**
     * declare the cards array that holds type card
     */
    private Card[] cards;
    
    /**
     * Delcaration variable next card
     */
    private int next;

    /**
     * Deck constructor that creates a deck with 52 cards
     */
    public Deck() {
        cards = new Card[CARDS_IN_DECK];
        int index = 0;

        for (char suit : new char[] {'c', 'd', 's', 'h'}) {
            for (int value = Card.LOWEST_VALUE; value <= Card.HIGHEST_VALUE; value++) {
                cards[index++] = new Card(suit, value);
            }
        }
    
        next = 0;
    }

    /**
     * The getNext function in Java returns the value of the variable next.
     * 
     * @return The `next` variable is being returned.
     */
    public int getNext() {
        return next;
    }

    /**
     * The function `getCards()` returns an array of Card objects.
     * 
     * @return An array of Card objects is being returned.
     */
    public Card[] getCards() {
        return cards;
    }

    /**
     * The `shuffle` function randomly shuffles the cards in a deck.
     */
    public void shuffle() {
        Random rand = new Random();
        
        for (int i = CARDS_IN_DECK - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);

            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

    /**
     * The initialize function resets all cards in a deck to not played and sets the next card index
     * to 0.
     */
    public void initialize() {
        for (int i = 0; i < CARDS_IN_DECK; i++) {
            cards[i].setPlayed(false);
        }

        next = 0;
    }

    
    /**
     * The `nextCard` function returns the next card in a deck of cards and throws an exception if
     * there are no more cards available.
     * 
     * @return The method `nextCard()` returns the next card in the deck.
     * @throws IllegalStateException when there are no more cards
     */
    public Card nextCard() {
        if (next >= CARDS_IN_DECK) {
            throw new IllegalStateException("No more cards");
        }
        
        Card nextCard = cards[next];
        next++;
        
        return nextCard;
    }

    /**
     * The equals method in Java checks if two Deck objects are equal based on their cards array and
     * next pointer.
     * 
     * @param o The parameter `o` in the `equals` method represents the object being compared for
     * equality with the current `Deck` object. The method first checks if the object `o` is an
     * instance of the `Deck` class. If it is, it then compares the `cards` array of the
     * @return The equals method is being used to compare two Deck objects for equality. It first
     * checks if the object being compared is an instance of Deck. If it is not, it returns false.
     * Then it compares the `cards` array of the two `Deck` objects using `Arrays.equals`. If the
     * `cards` arrays are not equal, it returns `false`. Finally,
     */
    public boolean equals(Object o) {
        if (!(o instanceof Deck)) {
            return false;
        }

        Deck otherDeck = (Deck) o;

        if (!Arrays.equals(this.cards, otherDeck.cards)) {
            return false;
        }

        return this.next == otherDeck.next;
    }

    /**
     * The `toString` function iterates through an array of cards and appends their string
     * representations to a `StringBuilder`.
     * 
     * @return The `toString` method is returning a string representation of a deck of cards. It
     * iterates through each card in the deck, appends the card index and its string representation
     * to a `StringBuilder`, and finally returns the concatenated string.
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        
        for (int i = 0; i < CARDS_IN_DECK; i++) {
            stringBuilder.append("card ").append(i)
                .append(": ").append(cards[i].toString()).append("\n");
        }
        
        return stringBuilder.toString();
    }    

}