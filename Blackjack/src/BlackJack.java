import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;



public class BlackJack {

	private Deck deck;      // The deck used in the game
	private Hand dealer;    // The hand of the dealer
	private Hand player;    // The hand of the player

	public final static int TARGET_VALUE = 31; // the value that is aimed for in the game
	public final static int DEALER_CUTOFF_VALUE = 26; //

	public final static int BIG_ACE_VALUE = 11;
	public final static int CARDS_IN_HAND = 3;
	public final static List<String> CARDS_WITH_VALUE_OF_10 = Arrays.asList("Jack", "Queen", "King");


	/*
	 * Setters and getters. Mainly needed for testing functionality.
	 * 
	 * DO NOT MODIFY THEM!
	 */
	
	public void setPlayerHand(Hand hand) {
		player = hand;
	}
	
	public void setDealerHand(Hand hand) {
		dealer = hand;
	}
	
	public void setDeck(Deck playingDeck) {
		deck = playingDeck;
	}
	
	public Hand getDealerHand() {
		return dealer;
	}
	
	public Hand getPlayerHand() {
		return player;
	}
	
	public Deck getDeck() {
		return deck;
	}
	
	
	/**
	 * Helper method to get user input on whether to
	 * hit (draw one more card) or stand (stop drawing
	 * cards). In order to draw another card, the user should
	 * reply with hit (case insensitive). Any other reply
	 * will be interpreted as stand
	 * 
	 * @return Returns true if the user wants another card, 
	 * otherwise false
	 */
	public boolean hitOrStand() {
		String inputLine = null;
		System.out.print("Hit or stand? ");
		try {
			BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
			inputLine = is.readLine();
		} catch(IOException e) {
			System.out.println("IOException: " + e);
			e.printStackTrace();
		}
		if(inputLine != null && inputLine.toLowerCase().equals("hit")) {
			return true;
		}
		return false;
	}
	
	
	
	
	/*
	 * TODO: Implement the methods below.
	 */
	
	/**
	 * This method should set up the game. This means that
	 * objects for the deck and the hands should be created,
	 * the deck should be shuffled, and both the player and
	 * the dealer should be given their cards. Remember that
	 * one of the dealer cards should be face down initially.
	 * Make sure that the face down card is the first card
	 * given to the dealer, i.e., the card at index 0 in the
	 * hand.
	 */

	public void initializeGame() {
		// create deck
		deck = new Deck();

		// shuffle deck
		deck.shuffle();

		player = new Hand();
		dealer = new Hand();

		// give player and dealer cards that are flipped
		for (Hand hand : new Hand[]{player, dealer}) {
			for ( int i = 0; i < CARDS_IN_HAND; i++ ) {
				Card card = deck.drawCard();
				hand.addCard(card);
			}
		}

		// unflip first card in dealers hand
		dealer.getCard(0).flipCard();
	}
	
	/**
	 * This method should check the value of the given hand
	 * according to the rules of blackjack
	 * 
	 * @param hand the hand whose value will be checked
	 * @return an integer representing the value of the hand
	 */
	public int checkHandValue(Hand hand) {

		List<Card> cards = hand.getCards();

		int numberOfAces = 0; // number of aces found in the hand
		int sum = 0;

		// find the sum of the numbers that are not aces as well as the number of aces
		for ( Card card : cards ) {

			if (card.getValueAsString().equals("Ace")) {
				numberOfAces += 1;
			} else if ( CARDS_WITH_VALUE_OF_10.contains( card.getValueAsString() )) {
				// add 10 if Jack, Queen or King
				sum += 10;
			} else {
				sum += card.getValue();
			}
		}

		// add aces to the sum
		for ( int i = 0; i < numberOfAces; i++ ) {
			if (sum + BIG_ACE_VALUE <= TARGET_VALUE) {
				sum += BIG_ACE_VALUE;
			} else {
				sum += Card.ACE;
			}
		}

		return sum;
	}
	
	/**
	 * This method should display the hand in the console. Use
	 * the toString() method in the class Hand for getting
	 * the contents of the hand as a String. If showHandValue is
	 * set to false, the value of the hand shall not be displayed.
	 * If it is set to true, then the value of the hand shall be
	 * displayed. 
	 * 
	 * Important! The hand value, if displayed, must be surrounded by spaces!
	 * Do not display the value next to another character. The tests
	 * for this method will fail if the value isn't surrounded by spaces
	 * 
	 * @param hand The hand to display
	 * @param showHandValue If true, the value of the hand will be displayed.
	 * Otherwise, the value will not be displayed.
	 */
	public void displayHand(Hand hand, boolean showHandValue) {

		// print the first
		System.out.print(hand.getCard(0));

		// print the rest
		for (int i = 1; i < hand.getCardCount(); i++) {
			System.out.print(" | " + hand.getCard(i));
		}
		System.out.println();

		if (showHandValue) {
			System.out.println("Hand value: " + checkHandValue(hand));
		}
		System.out.println();
	}
	
	/**
	 * The method should contain the logic for making the complete
	 * player turn. This involves allowing the player to choose whether
	 * to draw more cards or not, adding newly drawn cards to the hand and
	 * checking whether the hand value has exceeded 31 or not.
	 * 
	 * @return Returns true if the players hand value did not exceed 31, otherwise false
	 */
	public boolean makePlayerTurn() {

		System.out.println("Players turn!");

		while ( true ) {


			displayHand(player, true);
			if (checkHandValue(player) >= TARGET_VALUE) {
				break;
			}

			if (!hitOrStand()) {
				break;
			}

			Card card = deck.drawCard();
			player.addCard(card);
			System.out.println("Player picked up the card " + card);

		}

		System.out.println();

		if (checkHandValue(player) < TARGET_VALUE) {
			return true;
		}

		return false;
	}
	
	/**
	 * The method should contain the logic for making the complete
	 * dealer turn. The dealer follows specific rules on when to draw more cards
	 * and when to stand. This method should implement those rules.
	 * 
	 * @return Returns true if the dealers hand value did not exceed 31, otherwise false
	 */
	public boolean makeDealerTurn() {

		dealer.getCard(0).turnUp(); // lezz giddit

		System.out.println("Dealer's turn!");
		displayHand(dealer, true);

		Card card;
		while (checkHandValue(dealer) <= DEALER_CUTOFF_VALUE) {
			card = deck.drawCard();
			dealer.addCard(card);
			System.out.println("Dealer picked up the card " + card.toString());
			displayHand(dealer, true);
		}

		if (checkHandValue(dealer) < TARGET_VALUE) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * The method should check if the player wins or not. This should be
	 * done by comparing the players hand value with the dealers hand value.
	 * Remember that in case of a tie, the dealer wins.
	 * 
	 * @return Returns true if the player wins, otherwise false
	 */
	public boolean playerWins() {

		int playerPoints = checkHandValue(player);
		int dealerPoints = checkHandValue(dealer);

		if (playerPoints > TARGET_VALUE) {
			return false;
		} else if (dealerPoints > TARGET_VALUE) {
			return true;
		} else if (playerPoints > dealerPoints) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * The method should contain the logic for playing one round of blackjack.
	 * Upon completion, it should return true if the player won, otherwise false.
	 * 
	 * @return Returns true if the player won, otherwise false
	 */
	public boolean playOneRound() {

		System.out.print("Player's cards: ");
		displayHand(player, true);

		System.out.println("Dealer's cards: ");
		displayHand(dealer, false);
		System.out.println();

		// check if either the player or the dealer has exactly 31 in their hand
		if (checkHandValue(dealer) == TARGET_VALUE) {
			return false;
		} else if (checkHandValue(player) == TARGET_VALUE) {
			return true;
		}

		boolean keepPlaying = makePlayerTurn();
		if (keepPlaying) {
			makeDealerTurn();
		}

		if (playerWins()) {
			return true;
		}

		return false;
	}
	
}
