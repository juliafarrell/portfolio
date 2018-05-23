package malicious;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		
		ArrayList<String> temp;
		ArrayList<Character> guesses;
		boolean play = true, empty1, guessed;
		char guess;
		Hangman hang;
		int l = 0;
		String mysteryWord;
		String status;
		
		// Introduction
		Scanner user = new Scanner(System.in);
		hang = new Hangman();
		hang.read("dictionary.txt");
		
		System.out.println("Welcome to the game of malicious hangman");
		System.out.println();
		System.out.println("You will select a word length, then guess letters one at a time.");
		System.out.println("We will remove all words containing this letter until there are no words left to remove.");
		System.out.println("At this point, we will select a random word that is left. Then you play hangman normally.");
	
		
		// Loop while game is still happening
		while (play == true)
		{
			// Initialize variables (here, so the game can run as many times as the user wants to play)
			temp = new ArrayList<String>();
			guesses = new ArrayList<Character>();
			empty1 = false;
			guessed = false;
			
			// Ask user about word length
			System.out.println();
			System.out.println("Pick a word length to begin.");
			try {
				l = user.nextInt();
			} catch (InputMismatchException e) {
				e.printStackTrace();
				System.out.println("Did you enter a number? :p");
			}
			
			// Create hangman object with input dictionary size
			
			try
			{
				hang.setCurrentDictionary(l);
			} catch (IndexOutOfBoundsException e){
				e.printStackTrace();
				System.out.println("There aren't words of this length in our dictionary, sorry");
			}
			
			// Removing words
			while (empty1 == false)
			{	
				System.out.println("Please enter the letter you wish to guess");
				guess = user.next().charAt(0);
				
				// Delete any words containing this char in the list, 
				// remembering the words if it makes the list empty
				temp = hang.remove(guess);
				if (hang.currentDictionary.size() == 0)
				{
					guesses.add(guess);
					empty1 = true;
				}
			}
			
			// Select random word from the list
			Random rand = new Random();
			mysteryWord = temp.get(rand.nextInt(temp.size()));
			System.out.println("We selected a word: ");
			
			
			// Print the word, with the chars in place
			System.out.println("Your current status with the word is: ");
			System.out.println(Hangman.printStatus(guesses, mysteryWord));
			
			// Once list is selected, play normal hangman
			while (guessed == false)
			{
				// Take in the users next guess
				System.out.println();
				System.out.println("Please guess another letter.");
				guess = user.next().charAt(0);
				
				// If the letter is in the word, reveal it to the user
				// Add to guess char AL
				for (int i = 0; i < mysteryWord.length(); i++)
				{
					if (mysteryWord.charAt(i) == guess)
					{
						System.out.println(guess + " is in the word :)");
						System.out.println();
						guesses.add(guess);
						break;
					}
				}
				
				// Update current "status"
				status = Hangman.printStatus(guesses, mysteryWord);
				
				// if all the letters of the mystery word have NOT been guessed
				// print the current status
				if (Hangman.wordGuessed(status) == false)
				{
					// Print current word status
					System.out.println(status);
				}
				
				// otherwise, the game is complete
				// loop back if they want to play again
				else {
					System.out.println("You guessed the word!");
					System.out.println();
					System.out.println("The word was: " + status);
					guessed = true;
				}
			}
			System.out.println("Would you like to play again? (y/n)");
			guess = user.next().charAt(0);
			if (guess == 'n' || guess == 'N')
			{
				play = false;
				System.out.println("Thanks for playing");
				System.exit(0);
				
			}
			else {
				System.out.println();
				System.out.println("Sweet.");
				
			}
		}
	}

}
