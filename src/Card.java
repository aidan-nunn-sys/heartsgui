
/**
 * Card class for project 4
 * @author Aidan Nunn
 */
public class Card implements Comparable<Card> {

    /**
     * Sets clubs to c
     */
    public static final char CLUBS = 'c';

    /**
     * sets diamonds to d
     */
    public static final char DIAMONDS = 'd';

    /**
     * sets hearts to h
     */
    public static final char HEARTS = 'h';

    /**
     * sets spades to s
     */
    public static final char SPADES = 's';

    /**
     * sets lowest_value to 2
     */
    public static final int LOWEST_VALUE = 2;

    /**
     * sets highest_value to 14
     */
    public static final int HIGHEST_VALUE = 14;

    /**
     * sets queen value to 12
     */
    public static final int QUEEN_VALUE = 12;

    /**
     * private suit for card
     */
    private char suit;

    /**
     * Declares value
    */
    private int value;
    
    /**
     * Declares boolean if its been played
     */
    private boolean hasBeenPlayed;
    
    /**
     * Creates a card with type suit and int value
     * 
     * @param suit Sets the card for specific suit
     * @param value Sets the card for a specific value
     * @throws IllegalArgumentException Throws if invalid suit
     * @throws IllegalArgumentException Throws if invalid value
     */
    public Card(char suit, int value) {
        if (suit != DIAMONDS && suit != CLUBS && suit != SPADES && suit != HEARTS) {
            throw new IllegalArgumentException("Invalid suit");
        }
        if (value > HIGHEST_VALUE || value < LOWEST_VALUE) {
            throw new IllegalArgumentException("Invalid value");
        }

        this.suit = suit;
        this.value = value;
        this.hasBeenPlayed = false;
    }

    /**
     * The compareTo function compares two Card objects based on their suit and value, returning -1
     * if the calling card is less than the othercard,1 if it is greater, and 0 if they are equal in
     * value.
     * 
     * @param other The compareTo methodyou provided is used to compare two `Card` objects based on
     * their suit and value. The method returns a negativeinteger,zero, or a positive integer if the
     * current card is less than, equal to, or greater than the other card, respectively.
     * @return The `compareTo` method is used to compare two `Card` objects based on their suit and
     * value. The method returns an integer value based on the comparison result:
     */
    public int compareTo(Card other) {
        if (getSuit() == other.getSuit()) {
            if (getValue() < other.getValue()) {
                return -1;
            } else if (getValue() > other.getValue()) {
                return 1;
            }
            else {
                return 0;
            }
        } 
        else {
            switch(getSuit()) {
                case HEARTS:
                    return 1;
                case SPADES:
                    if (other.getSuit() == HEARTS) {
                        return -1;
                    } 
                    else {
                        return 1;
                    }
                case DIAMONDS:
                    if (other.getSuit() == HEARTS || other.getSuit() == SPADES) {
                        return -1;
                    } 
                    else {
                        return 1;
                    }
                //CLUBS
                default:
                    return -1;
            }
        }
    }

    /**
     * The function `getSuit` returns the suit of a card as a character.
     * 
     * @return The `suit` variable of type `char` is being returned.
     */
    public char getSuit() {
        return suit;
    }

    /**
     * The getValue() function in Java returns the value stored in a variable.
     * 
     * @return The `value` variable is being returned.
     */
    public int getValue() {
        return value;
    }

    /**
     * The function `hasBeenPlayed()` returns a boolean value indicating whether an item has been
     * played.
     * 
     * @return The method hasBeenPlayed() returns the value of the variable hasBeenPlayed, which is
     * a boolean indicating whether the item has been played or not.
     */
    public boolean hasBeenPlayed() {
        return hasBeenPlayed;
    }

    /**
     * This function sets the hasBeenPlayed boolean variable to the value of the input parameter
     * "played".
     * 
     * @param played The parameter played in the setPlayed method is a boolean value that indicates
     * whether a certain action has been performed or not. In this case, it is used to set the
     * hasBeenPlayed variable to either `true` or `false` based on the value passed to the
     */
    public void setPlayed(boolean played) {
        hasBeenPlayed = played;
    }

    /**
     * The function `isHeart()` returns true if the suit is 'h' (heart) and false otherwise.
     * 
     * @return The method`isHeart()` returns a boolean value based on whether the `suit` variable is
     * equal to 'h'. If the `suit` is 'h', the method returns `true`, indicating that the card is a
     * heart. Otherwise, it returns `false`, indicating that the card is not a heart.
     */
    public boolean isHeart() {
        if (suit == 'h') {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * The function `isQueenOfSpades` checks if a card is the Queen of Spades.
     * 
     * @return The method `isQueenOfSpades()` returns a 
     * boolean value - `true` if the card is the Queen
     * of Spades, and `false` otherwise.
     */
    public boolean isQueenOfSpades() {
        if (suit == SPADES && value == QUEEN_VALUE) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * The function `isHigherThan` compares the value of two cards 
     * based on their suits and returns
     * true if the calling card has a higher value.
     * 
     * @param other The `other` parameter in the `isHigherThan` 
     * method represents another `Card` object
     * that you want to compare with the current `Card` 
     * object (referred to as `this`). The method
     * compares the `suit` and `value` of the two cards 
     * to determine if the current card
     * @return The method is returning a boolean value. 
     * It returns true if the current card's value is
     * higher than the other card's value and they have 
     * the same suit. Otherwise, it returns false.
     */
    public boolean isHigherThan(Card other) {

        if (this.suit == other.suit)
            if (this.value > other.value) 
                return true;
            else
                return false;
        else 
            return false;
    }

    
    /**
     * The `equals` function in Java checks if the given 
     * object is a Card and if its suit and value
     * match the current Card object.
     * 
     * @param o The parameter `o` is of type `Object`, 
     * which means it can refer to any object in Java.
     * In the `equals` method implementation provided, it is being cast to a `Card` object and then
     * checked for equality with the current `Card` object based on the `suit` and
     * @return The `equals` method is returning a boolean value. 
     * It returns `true` if the object `o` is
     * an instance of `Card` and has the same `suit` and `value` as the current `Card` object.
     * Otherwise, it returns `false`.
     */
    public boolean equals(Object o) {
        Card card = (Card) o;
        if (o instanceof Card)
            if (card.suit == this.suit && card.value == this.value)
                return true;
            else
                return false;
        else
            return false;
    }

    
    /**
     * The `toString` function concatenates the `suit` and `value` variables and returns the result 
     * as a string.
     * 
     * @return The`toString()` method is returning a string that concatenates the `suit` and `value`
     * variables together.
     */
    public String toString() {
        String result = suit + "" + value;
        return result;
    }
}
