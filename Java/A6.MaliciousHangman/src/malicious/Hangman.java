package malicious;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Hangman {
	
	HashMap<Integer, ArrayList<String>> dictionary;
	ArrayList<ArrayList<String>> words;
	ArrayList<String> currentDictionary;
	int length;

	
	public Hangman()
	{
		dictionary = new HashMap<Integer, ArrayList<String>>();
		words = new ArrayList<ArrayList<String>>();
		currentDictionary = new ArrayList<String>();
	}
	
	
	// method to read dictionary in, separated by length
	// returns hash map with the full dictionary, separated by word length
	public HashMap<Integer, ArrayList<String>> read(String fileName)
	{
		String input;
		try 
		{
			FileInputStream fis = new FileInputStream(fileName);
			Scanner readFrom = new Scanner(fis);
			
			// Initialize ArrayLists for possible word lengths
			for (int i = 0; i < 41; i++)
			{
				words.add(new ArrayList<String>());
			}
			
			// Read words into ALs by their length
			while (readFrom.hasNext())
			{
				input = readFrom.next();
				length = input.length();
				// AL chosen by length, so 
				// words.get(2) corresponds to 2 letter words
				// words.get(3) corresponds to 3 letter words
				// and so on
				words.get(length).add(input);
			}
			readFrom.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			e.getMessage();
		}
		
		// Finally, read words into the dictionary map
		for (int i = 0; i < 41; i++)
		{
			dictionary.put(i, words.get(i));
		}
		return dictionary;
	}
	
	
	// Returns an Array List of the dictionary, containing words of a given length
	// What is returned is a copy, so deleting doesn't affect the original Dictionary
	// Allowing the same dictionary to be used for as many games as the user wants
	public ArrayList<String> setCurrentDictionary(int wordLength)
	{
		ArrayList<String> copy = new ArrayList<String>();
		for (int i = 0; i < dictionary.get(wordLength).size(); i++)
		{
			copy.add(dictionary.get(wordLength).get(i));
		}
		length = wordLength;
		currentDictionary = copy;
		return copy;
	}
	
	
	// Removes all words that contain the char specified
	// Returns an ArrayList of the words removed
	public ArrayList<String> remove(char toRemove)
	{
		Iterator<String> it = currentDictionary.iterator();
		String checking;
		ArrayList<String> removed = new ArrayList<String>();
		// loop through all the words
		while (it.hasNext())
		{
			checking = it.next();
			// loop through all the letters in each word
			for (int j = 0; j < length; j++)
			{
				// if the char to be removed is in the word, remove from currentDictionary
				// and put into the removed ArrayList
				if (checking.charAt(j) == toRemove)
				{
					removed.add(checking);
					it.remove();
					break;
				}
			}
		}
		return removed;
	}
	
	
	// Static method to print the word with guessed letters in the correct location
	// ArrayList of chars contains all the guesses after the word was selected
	// No repeats allowed in the char arrayList (not handled in this method)
	// String is the word being guessed
	public static String printStatus(ArrayList<Character> guesses, String word)
	{
		String ret = "";
		for (int i = 0; i < word.length(); i++)
		{
			for (Character c : guesses)
			{
				if (word.charAt(i) == c)
				{
					ret += c;
					break;
				}
			}
			if (ret.length() == i)
			{
				ret += "_";
			}
		}	
		return ret + " ";
	}
	
	public static boolean wordGuessed(String printedStatus)
	{
		for (int i = 0; i < printedStatus.length(); i++)
		{
			if (printedStatus.substring(i, i+1).equals("_")) return false;
		}
		return true;
	}

}
