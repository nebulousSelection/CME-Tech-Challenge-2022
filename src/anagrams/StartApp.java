/**
 * 
 */
package anagrams;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Submission for CME Tech Challenge 2022.  
 * 
 * Program prompts users to select option via menu, prompts them to enter inputs, 
 * determines whether their input strings are anagrams and records the input in a 
 * CSV file.  
 * 
 * @author Jonathan Foster
 */
public class StartApp {

	// declare variables
	static String username, string1, string2;
	static char[] array1, array2;
	static Boolean result;

	// define what characters are allowed
	static Pattern p = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
	
	// initialise Scanner object
	static Scanner scanner = new Scanner(System.in);

	/**
	 * This is the starting point for the app.  
	 */
	public static void main(String[] args) {
		startApp();
	}

	/**
	 * Displays a menu of options for the user to select from.
	 */
	public static void startApp() {

		int userChoice;

		// loop until user ends program
		do {
			// display menu
			System.out.println("-- MENU --------------------------------");
			System.out.println("1. Check for anagram");
			System.out.println("2. End program");
			System.out.println("\nEnter option:");

			// wait for user input
			userChoice = scanner.nextInt();
			// consume \n character
			scanner.nextLine(); 

			// menu options processing
			switch (userChoice) {
			case 1:
				fetchUserInput();
				checkForAnagram();
				writeData();
				break;
			case 2:
				System.out.println("Ending program");
				break;
			default:
				System.out.println("Try options again...");
			}
		} while (userChoice != 2);

		// close Scanner object to prevent resource leak
		scanner.close();
	}

	/**
	 * Prompts user for text inputs, checking they are valid before continuing.  
	 */
	public static void fetchUserInput() {

		do {
			System.out.println("Please enter your username:");
			username = scanner.nextLine();
		} while (!isValid(username));
		
		do {
			System.out.println("Please enter the first string:");
			string1 = scanner.nextLine();
		} while (!isValid(string1));
		
		do {
			System.out.println("Please enter the second string:");
			string2 = scanner.nextLine();
		} while (!isValid(string2));		
	}

	/**
	 * Determines whether the user input strings contain only allowed characters.
	 * 
	 * @param string - the string to be verified
	 * @return boolean
	 */
	public static Boolean isValid(String string) {
		
		Matcher m = p.matcher(string);
		return !m.find();

	}

	/**
	 * Checks whether strings are anagrams by stripping case, converting to char 
	 * arrays, sorting, and comparing.  
	 */
	public static void checkForAnagram() {

		array1 = string1.toLowerCase().toCharArray();
		array2 = string2.toLowerCase().toCharArray();
		Arrays.sort(array1);
		Arrays.sort(array2);
		result = Arrays.equals(array1, array2);
		
	}

	/**
	 * Appends results to CSV file. 
	 */
	public static void writeData() {

		File file = new File("results.csv");

		try {
			// initialise required objects
			FileWriter fileWriter = new FileWriter(file, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			// check file exists and create it if not
			while (!file.exists()) {
				file.createNewFile();
			}

			// variables to write to file
			bufferedWriter.write("\n" + username + "," + string1 + "," + string2 + "," + result);

			// close objects to prevent resource leak
			bufferedWriter.close();
			fileWriter.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}