package spil;
import java.util.Scanner;

public class Game {
	//turn: true = player1, false = player2.
	private boolean turn = Math.random() < 0.5;
	private boolean gameFinished;
	private int player1Points = 0;
	private int player2Points = 0;
	private int turns = 0;
	private boolean almostWon = false;
	private Dice d1 = new Dice();
	private Dice d2 = new Dice();
	static Scanner input = new Scanner(System.in);
	public Game() {
	}
	//method that 'runs' the game. 
	public void runGame() {
		//Only run until the game is over
		while(!gameFinished) {
			turns++;
			//Check whose turn it is to throw the dice.
			//If 'turn' is true, it's player 1's turn
			//else its player 2's turn.
			if(turn) {
				System.out.println("It is now player 1's turn.");
				takeTurn();
				nullifyScore();
				//If the player has a score of 40, and rolls two of the same values
				//the game is over.
				if(player1Points >= 40) {
					player1Points = 40;
					if(checkSame()) {
						gameOver();
					}
				}
				//Checks wether or not the player gets another turn
				if(!checkSame()) {
					turn = false;	
				}else {
					//if the player rolls two 6's two turns in a row
					//he or she wins the game.
					if(d1.getFaceValue() == 6 && !almostWon) {
						almostWon = true;
					} else if(d1.getFaceValue() == 6 && almostWon) {
						gameOver();
					} else {
						almostWon = false;
					}
				}
				//If the game isn't over, prints the current player's current score.
				if(!gameFinished) {
					System.out.println("Player 1 now has " + player1Points + " points.");
				}

			}else {
				//Same logic as player 1, but this part changes different variables.
				//Player 2's turn:
				System.out.println("It is now player 2's turn.");

				takeTurn();
				nullifyScore();
				if(player2Points >= 40) {
					player2Points = 40;
					if(checkSame()) {
						gameOver();
					}
				}
				if(!checkSame()) {
					turn = true;	
				}else {
					if(d1.getFaceValue() == 6 && !almostWon) {
						almostWon = true;
					} else if(d1.getFaceValue() == 6 && almostWon) {
						gameOver();
					} else {
						almostWon = false;
					}
				}
				if(!gameFinished) {
					System.out.println("Player 2 now has " + player2Points + " points.");
				}
			}
		}
	}
	
	//Method which rolls two dice, and adds the points to a players score.
	public void takeTurn() {
		int roll1, roll2;
		System.out.println("Press enter to roll your dice!");
		input.nextLine();
		roll1 = d1.roll();
		roll2 = d2.roll();
		System.out.println("You have rolled " + roll1 + " and " + roll2);
		if(turn) {
			player1Points += roll1 + roll2;
		}else {
			player2Points += roll1 + roll2;
		}
	}
	
	//checks if the two dice show the same value.
	public boolean checkSame() {
		return (d1.getFaceValue() == d2.getFaceValue());
	}

	//if the player rolls two 1's his or her score is reset.
	public void nullifyScore() {
		if(d1.getFaceValue() == 1 && d2.getFaceValue() == 1) {
			if(turn) {
				player1Points = 0;
			}else {
				player2Points = 0;
			}
		}
	}
	
	//When the game is over, a finish message is printed,
	//displaying the winning player, and prints the losing players score.
	//Also shows the amount of turns taken.
	public void gameOver() {
		gameFinished = true;
		System.out.println("The game has finished!");
		if(turn) {
			System.out.println("Player 1 has won!");
			System.out.println("Player 2 had a score of " + player2Points +".");
		} else {
			System.out.println("Player 2 has won!");
			System.out.println("Player 1 had a score of " + player1Points +".");
		}
		System.out.println("The game lasted " + turns + " turns.");
	}

}
