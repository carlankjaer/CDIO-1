package spil;

public class Dice {
	//This class describes a Dice object. It contains the value of the last roll
	//and methods to roll a new value, and return the previous rolled value.
	int currentRoll;
	public Dice() {
	}
	
	public int roll() {
		currentRoll = (int)(Math.random() * 6 + 1);
		return currentRoll;
	}
	
	public int getFaceValue() {
		return currentRoll;
	}
}
