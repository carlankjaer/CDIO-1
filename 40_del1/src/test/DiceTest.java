package test;

import static org.junit.Assert.*;

import org.junit.Test;

import spil.Dice;

public class DiceTest {
	
	boolean overskrid = false;
	
	@Test
	//This test check that each face value is hit approximately the statistically predicted amount of times.
	public void testSum() {
		Dice diceGame	= new Dice();
		double maxDev = 0.08;
		//Array that contains amount of hits for each face value.
		int[] values = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		//rolls a die twice, sums the two face values, and increments the corresponding element in values[].
		for(int i=0; i < 22000; i++) {
			values[diceGame.roll()+diceGame.roll()-2]++;
		}
		double level;
		//This for-loop controls that the elements in values[], do not exceed some arbitrary margin of error.
		for(int i = 0; i < 5; i++) {
			level = (double)(i+1)/36.0;
			if((double)values[i] < (22000*level)*(1-maxDev) || values[i] > (22000*level)*(1+maxDev) || (double)values[10-i] < (22000*level)*(1-maxDev) || values[10-i] > (22000*level)*(1+maxDev)) {
				overskrid = true;
			}
		}		
		assertFalse(overskrid);	
	}
	@Test
	//this test check the frequency of two rolls with the same face value.
	public void testMatch() {
		boolean overskrid = false;
		Dice diceGame = new Dice();
		Dice diceGame2 = new Dice();
		double maxDev = 0.08;
		//Array that contains amount of hits for each pair.
		int[] values = {0, 0, 0, 0, 0, 0};
		//Rolls two die, and checks whether they display the same face value, 
		//incrementing the corresponding element in values[], if they do.
		for(int i=0; i < 22000; i++) {
			if(diceGame.roll() == diceGame2.roll()) {
				values[diceGame.getFaceValue()-1]++;
			}
		}
		double level;
		//This for-loop controls that the elements in values[], do not exceed some arbitrary margin of error.
		for(int i = 0; i < 5; i++) {
			level = 1.0/36.0;
			if((double)values[i] < (22000*level)*(1-maxDev) || values[i] > (22000*level)*(1+maxDev)) {
				overskrid = true;
			}
		}
		assertFalse(overskrid);
	}
}
