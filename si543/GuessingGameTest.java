package com.raynaldmirville.si543;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GuessingGameTest {
	
	private GuessingGame game;
	private GuessingGame multiGame;
	private int solutions[];
	
	@Before
	public void setUp() {
		game = new GuessingGame(0,100,3);
		
		solutions = new int[] {50,30,75};
		
		multiGame = new GuessingGame (1,100,solutions[0]);
	}
	
	private void reset(int iteration) {
		multiGame.reset(1,100,solutions[iteration]);
	}

	/* testCorrectGuess
	 * If the user guesses the right number,
	 * the guess will be considered correct.
	 */
	@Test
	public void testCorrectGuess() {
		assertEquals('c',game.testMatch(3));
	}
	
	/*testHighGuess
	 * If the user guesses a number that is too high,
	 * the class will give the code for "too high."
	 */
	@Test
	public void testHighGuess() {
		assertEquals('h',game.testMatch(4));
	}
	
	/*testLowGuess
	 * If the user guesses a number that is too low,
	 * the class will give the code for "too low."
	 */
	@Test
	public void testLowGuess() {
		assertEquals('l',game.testMatch(2));
	}
	
	/*testOutOfBoundsGuess
	 * If the user guesses a number out of bounds,
	 * the class will give the code for "out of bounds."
	 */
	@Test
	public void testOutOfBoundsGuess() {
		assertEquals('o',game.testMatch(-1));
	}
	
	/*testGuessCounter
	 * The guess counter matches the number of in bounds
	 * guesses the user makes.
	 */
	@Test
	public void testGuessCounter() {
		GuessingGame countGame = new GuessingGame(1,100,50);
		countGame.testMatch(-1);
		countGame.testMatch(2);
		countGame.testMatch(50);
		countGame.testMatch(60);
		countGame.testMatch(120);
		assertEquals(3,countGame.getNumGuesses());
	}
	
	/*testReset
	 * Need to be able to play a game multiple times.
	 * If I reset the game with a different solution,
	 * The first correct guess should not work for the new game
	 */
	@Test
	public void testReset() {
		assertEquals('c',multiGame.testMatch(solutions[0]));
		
		reset(2);
		assertNotEquals('c',multiGame.testMatch(solutions[0]));
	}
	
	/* testBestScore
	 * If a user plays multiple games,
	 * the game should track the best guessing score overall.
	 */
	@Test
	public void testHighScore() {
		multiGame.testMatch(3);
		multiGame.testMatch(5);
		multiGame.testMatch(4);
		multiGame.testMatch(solutions[0]);
		
		assertEquals(4,multiGame.getBestScore());
		
		reset(1);
		multiGame.testMatch(2);
		multiGame.testMatch(89);
		multiGame.testMatch(solutions[1]);
		
		assertEquals(3,multiGame.getBestScore());
		
		reset(2);
		multiGame.testMatch(3);
		multiGame.testMatch(89);
		multiGame.testMatch(49);
		multiGame.testMatch(23);
		multiGame.testMatch(solutions[2]);
		
		assertEquals(3,multiGame.getBestScore());
	}
}
