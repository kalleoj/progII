
public class Main {

	public static void main(String[] args) {
		
		BlackJack bg = new BlackJack();
		bg.initializeGame();
		boolean playerWon = bg.playOneRound();

		if (playerWon) {
			System.out.println("Player won");
			return;
		}

		System.out.println("Dealer won");

	}

}
