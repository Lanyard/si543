package com.raynaldmirville.si543;
/* Name: Raynald Mirville
 * Class Name: GuessingGame
 * 
 * Notes for instructor (if any):
 * 
 * Received assistance from: No one
 * Expected score: 100 -- Applied object-oriented approach
 * to separate functionality from the interface.
 * Developed through test-driven development (see GuessingGameTest.java)
 */

import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
	protected int solution;
	protected int min;
	protected int max;
	protected int numGuesses;
	protected int bestScore;
	
	public GuessingGame(int low, int hi, int num) {
		min = low;
		max = hi;
		solution = num;
		numGuesses = 0;
		
		// bestScore is set to a negative number since the initial number of guesses can technically be infinite.
		bestScore = -1;
	}
	
	public int getSolution() {
		return solution;
	}
	
	public int getNumGuesses() {
		return numGuesses;
	}
	
	public int getBestScore() {
		return bestScore;
	}
	
	/* reset
	 * Resets the game with a new solution, and range of numbers.
	 * The best guess counter is preserved.
	 */
	public void reset(int low, int hi, int num) {
		min = low;
		max = hi;
		solution = num;
		numGuesses = 0;
	}
	
	/* testMatch
	 * returns a code corresponding to whether the guess is a match
	 * 'c' -> correct
	 * 'l' -> too low
	 * 'h' -> too high
	 * 'o' -> out of bounds
	 */
	public char testMatch(int guess) {
		// First test if the guess is out of bounds.
		if ((guess < min) || (guess > max)) {
			return 'o';
		}
		
		else {
			numGuesses++;
			
			if (guess < solution) {
				return 'l';
			}
			
			else if (guess > solution) {
				return 'h';
			}
			
			// If it's not out of bounds or different from the solution, consider it a match.
			if ((bestScore == -1) || (numGuesses < bestScore)) {
				bestScore = numGuesses;
			}
			return 'c';
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner keyboard = new Scanner(System.in);
		
		Random solutionGenerator = new Random();
		int min = 1;
		int max = 100;
		int guess = -1;
		char result = '\u0000';
		boolean repeat = true;
		String repeatResponse = "";
		
		
		// Initialize the first game
		int solution = solutionGenerator.nextInt(max - min) + min;
		
		GuessingGame game = new GuessingGame(1,100,solution);
		
		// Play a game. Keep repeating the game until the user exits.
		do {
			
			// Print the solution
			System.out.println(solution);
		
			// Request a guess. Keep requesting guesses until it's correct.
			do {
				// Request a guess
				System.out.print("Enter a guess: ");
				guess = keyboard.nextInt();
				
				// Compare the response to the solution
				result = game.testMatch(guess);
				
				switch (result) {
				case 'o':
					System.out.print("Number must be between 1 and 100!!! ");
					break;
				case 'h':
					System.out.print("Too high! ");
					break;
				case 'l':
					System.out.print("Too low! ");
					break;
				case 'c':
					System.out.print("Correct! ");
					break;
				default:
					System.out.print("Something unexpected happened... ");
				}
				// Generate a response based on the comparison
			} while (result != 'c');
			
			// Print the results of the game.
			System.out.println("You took " + game.getNumGuesses() + " guesses. Your best so far is " + game.getBestScore() + ".");
						
			// Ask if the user wants to play again.
			System.out.print("Play again? ");
			
			// Decipher the user's response.
			repeatResponse = keyboard.next();
			
			repeat = (repeatResponse.equalsIgnoreCase("yes") || repeatResponse.equalsIgnoreCase("y")) ? true : false;
			// If the user wants to play a game, reset the game.
			if (repeat) {
				solution = solutionGenerator.nextInt(max - min) + min;
				game.reset(min, max, solution);
			}
		} while (repeat);
		
		keyboard.close();
		return;
	}
}