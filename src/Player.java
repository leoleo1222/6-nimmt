import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Player {
    /**
     * The cards held on a player hand
     */
    private Card[] hand;
    /**
     * The number of card held by the player. This variable should be maintained
     * to match array hand.
     */
    private int handCount;
    /**
     * A dynamic array that holds the score pile.
     */
    private Card[] pile;
    /**
     * The name of the player
     */
    private String name;
    /**
     * A static variable that tells how many player has been initialized
     */
    private static int count = 1;

    /**
     * Other constructor that specify the name of the player.
     * 
     * You need to initialize your data member properly.
     */
    public Player(String name) {
        this.name = name;
        this.hand = new Card[10];
        this.handCount = 0;
        this.pile = new Card[0];
    }

    /**
     * Default constructor that set the name of the player as "Computer #1",
     * "Computer #2", "Computer #3"...
     * The number grows when there are more computer players being created.
     * 
     * You need to initialize your data member properly.
     */
    public Player() {
        this.name = "Computer #" + count;
        this.hand = new Card[0];
        this.handCount = 0;
        this.pile = new Card[0];
        count++;
    }

    /**
     * Getter of name
     * 
     * @return - the name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method is called when a player is required to take the card from a stack
     * to his score pile. The variable pile should be treated as a dynamic array so
     * that the array will auto-resize to hold enough number of cards. The length of
     * pile should properly record the total number of cards taken by a player.
     * 
     * Important: at the end of this method, you should also help removing all cards
     * from the parameter array "cards".
     * 
     * 
     * 
     * @param cards - an array of cards taken from a stack
     * @param count - number of cards taken from the stack
     */
    public void moveToPile(Card[] cards, int count) {
        // create a new array to hold the cards
        Card[] newPile = new Card[this.pile.length + count];
        // copy the cards from the old pile to the new pile
        for (int i = 0; i < this.pile.length; i++) {
            newPile[i] = this.pile[i];
        }
        // copy the cards from the parameter to the new pile
        for (int i = 0; i < count; i++) {
            newPile[this.pile.length + i] = cards[i];
        }
        // update the pile
        this.pile = newPile;
        // remove the cards from the parameter
        for (int i = 0; i < count; i++) {
            cards[i] = null;
        }

    }

    /**
     * This method prompts a human player to play a card. It first print
     * all cards on his hand. Then the player will need to select a card
     * to play by entering the INDEX of the card.
     * 
     * @return - the card to play
     */
    public Card playCard() {
        // print all cards on the hand
        for (int i = 0; i < this.handCount; i++) {
            System.out.print(i + ": ");
            this.hand[i].print();
            System.out.println();
        }
        // prompt the player to select a card
        System.out.print(this.name + ", please select a card to play: ");
        Scanner sc = new Scanner(System.in);
        int index = sc.nextInt();
        // check if the index is valid and the input should not be a string
        while (index < 0 || index >= this.handCount) {
            try{
                index = sc.nextInt();
            }catch(Exception e){
                sc.next();
            }
        }
        // remove the card from the hand
        Card card = this.hand[index];
        for (int i = index; i < this.handCount - 1; i++) {
            this.hand[i] = this.hand[i + 1];
        }
        this.handCount--;
        return card;
    }

    /**
     * This method let a computer player to play a card randomly. The computer
     * player will pick any available card to play in a random fashion.
     * 
     * @return - card to play
     */
    public Card playCardRandomly() {
        // randomly pick a card to play
        int index = ThreadLocalRandom.current().nextInt(0, this.handCount);
        // remove the card from the hand
        Card card = this.hand[index];
        for (int i = index; i < this.handCount - 1; i++) {
            this.hand[i] = this.hand[i + 1];
        }
        this.handCount--;
        return card;

    }

    /**
     * Deal a card to a player. This should add a card to the variable hand and
     * update the variable handCount. During this method, you do not need to resize
     * the array. You can assume that a player will be dealt with at most 10 cards.
     * That is, the method will only be called 10 times on a player.
     * 
     * After each call of this method, the hand should be sorted properly according
     * to the number of the card.
     * 
     * @param card - a card to be dealt
     */
    public void dealCard(Card card) {
        // put card to the hand
        this.hand[this.handCount] = card;
        // update the hand count
        this.handCount++;
    }

    /**
     * Get the score of the player by counting the total number of Bull Head in the
     * score pile.
     * 
     * @return - score, 0 or a positive integer
     */
    public int getScore() {
       // Get the score of the player by counting the total number of Bull Head in the score pile..
        int score = 0;
        for (Card card : this.pile) {
            score += card.getBullHead();
        }
        return score;
    }

    /**
     * To print the score pile. This method has completed for you.
     * 
     * You don't need to modify it and you should not modify it.
     */
    public void printPile() {
        for (Card c : pile) {
            c.print();
        }
        System.out.println();
    }

    /**
     * This is a getter of hand's card. This method has been completed for you
     *
     * You don't need to modify it and you should not modify it.
     * 
     * @param index - the index of card to take
     * @return - the card from the hand or null
     */
    public Card getHandCard(int index) {
        if (index < 0 || index >= handCount)
            return null;
        return hand[index];
    }
}
