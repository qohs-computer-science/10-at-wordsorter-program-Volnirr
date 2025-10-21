/*
 * Liam Gillaspy
 * 10/13
 * Class Period: 6
 * A program that pulls words from an article based off of what the user asks for 
 */ 
 
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList ;

public class WordSorter 
{
	public static void main(String[] args) 
	{
		ArrayList<ArrayList<String>> myArticle = new ArrayList<>();
		for (int i = 0; i < 26; i++) {
			myArticle.add(new ArrayList<>());
		} // create a "dictionary" to store words, and their letters

		Scanner in = new Scanner(System.in); 
		try
		{
			in = new Scanner(new File("article.txt"));	
		}
		catch(Exception e)
		{
			System.out.println("Cannot find file... Exiting Program");
			return;
		}	
		String word = "";
		while(in.hasNext())
		{
			word = in.next();
			word = word.replace(",", "");
			word = word.replace("(tm)", "");
			word = word.replace(")", "");
			word = word.replace("(", "");
			word = word.replace(".", "");
			word = word.replace("'s", "");
			word = word.replace(":", "");
			word = word.replace("!", "");
			word = word.replace("?", "");
			word = word.replace("'", "");
			word = word.toLowerCase() ;

			if (word.length() > 0) {
				char firstLetter = word.charAt(0);
				int index = firstLetter - 'a';
				
				if (!myArticle.get(index).contains(word)) {
					myArticle.get(index).add(word);
				}
			} // if the word is in fact a word, add it to the arraylist
		}
		in.close();

		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		
		while (running) {
			System.out.println("\n1. Print out all words starting with a specific letter");
			System.out.println("2. Print out all words");
			System.out.println("3. Print out the total number of unique words");
			System.out.println("4. Search and determine if a word is in the article");
			System.out.println("5. Remove a word from the data structure");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");
			
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			// option 1
			if (choice == 1) {
				System.out.print("Enter a letter: ");
				String input = scanner.nextLine();
				char letter = Character.toLowerCase(input.charAt(0));
				int index = letter - 'a';
				ArrayList<String> words = myArticle.get(index);
				
				if (words.size() > 0) {
					for (String w : words) {
						System.out.println(w);
					}
				} else {
					System.out.println("No words start with this letter");
				}
			} 
			// option 2
			else if (choice == 2) {
				int total = 0;
				for (int i = 0; i < 26; i++) {
					total += myArticle.get(i).size();
				} //calculate total word size
				
				if (total == 0) {
					System.out.println("Empty List");
				} else {
					for (int i = 0; i < 26; i++) {
						ArrayList<String> words = myArticle.get(i);
						if (words.size() > 0) {
							char letter = (char)('a' + i);
							System.out.println(letter + ":");
							for (String w : words) {
								System.out.println("  " + w);
							}
						}
					}
				} //  if empty, say so, otherwise print words
			} 
			//option 3
			else if (choice == 3) {
				int total = 0;
				for (int i = 0; i < 26; i++) {
					total += myArticle.get(i).size();
				}
				
				if (total == 0) {
					System.out.println("Empty List");
				} else {
					System.out.println("There are " + total + " unique words in the article");
				} // if not empty print the no of unique words
			} 
			// option 4
			else if (choice == 4) {
				System.out.print("Enter a word to search: ");
				String searchWord = scanner.nextLine();
				searchWord = searchWord.toLowerCase();
				char firstLetter = searchWord.charAt(0);
				int index = firstLetter - 'a';
				
				if (myArticle.get(index).contains(searchWord)) {
					System.out.println("Word found in the article");
				} else {
					System.out.println("Word NOT found in the article");
				} // look for word
			} 
			// option 5
			else if (choice == 5) {
				System.out.print("Enter a word to remove: ");
				String removeWord = scanner.nextLine();
				removeWord = removeWord.toLowerCase();
				char firstLetter = removeWord.charAt(0);
				int index = firstLetter - 'a';
				
				if (myArticle.get(index).contains(removeWord)) {
					myArticle.get(index).remove(removeWord);
					System.out.println("Word successfully removed from the list");
				} else {
					System.out.println("Word NOT found in the article");
				} 
			} 
			// exit
			else if (choice == 6) {
				running = false;
				System.out.println("Exiting program...");
			} else {
				System.out.println("Invalid choice. Please try again.");
			}
		} // while loop for the menu
		
		scanner.close();
	}
}