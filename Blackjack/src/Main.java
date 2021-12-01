import java.io.InvalidObjectException;

public class Main {

	public static void main(String[] args) {
		
		BlackJack bg = new BlackJack();
		bg.initializeGame();

		int playerCardCount = bg.getPlayerHand().getCardCount();
		int dealerCardCount = bg.getDealerHand().getCardCount();

		if (playerCardCount != BlackJack.CARDS_IN_HAND || dealerCardCount != BlackJack.CARDS_IN_HAND) {
			System.out.println("Error: invalid amount of cards.");
			return;
		}

		boolean playerWon = bg.playOneRound();

		if (playerWon) {
			System.out.println("Player won");
			return;
		}

		System.out.println("Dealer won");

	}

}
