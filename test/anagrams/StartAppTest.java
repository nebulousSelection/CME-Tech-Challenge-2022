/**
 * 
 */
package anagrams;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Jonathan Foster
 */
class StartAppTest {

	// declare variables
	String validString, invalidStringNumber, invalidStringWhitespace, invalidStringSpecialCharacter, expected;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		validString = "abc";
		invalidStringNumber = "a1c";
		invalidStringWhitespace = "a c";
		invalidStringSpecialCharacter = "a&c";
		
		StartApp.username = "username";
		StartApp.string1 = "PLAYERS";
		StartApp.string2 = "parsley";
		
		expected = "username,PLAYERS,parsley,false";
	}

	/**
	 * Test method for testIsValid() method.
	 */
	@Test
	final void testIsValid() {

		assertTrue(StartApp.isValid(validString));
		assertFalse(StartApp.isValid(invalidStringNumber));
		assertFalse(StartApp.isValid(invalidStringWhitespace));
		assertFalse(StartApp.isValid(invalidStringSpecialCharacter));

	}

	/**
	 * Test method for testCheckForAnagram() method with a valid input.
	 */
	@Test
	final void testCheckForAnagramValid() {

		StartApp.checkForAnagram();
		assertTrue(StartApp.result);

	}

	/**
	 * Test method for testCheckForAnagram() method with an invalid input.
	 */
	@Test
	final void testCheckForAnagramInvalid() {

		StartApp.string1 = "abc";
		StartApp.string2 = "def";
		StartApp.checkForAnagram();
		assertFalse(StartApp.result);

	}

	/**
	 * Test method for testWriteData() method.
	 */
	@Test
	final void testWriteData() {
		
		// invoke method under test
		StartApp.writeData();
		
		// read last line of results.txt
		String line, actual;
		line = actual = "";
		File file = new File("results.csv");
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			line = bufferedReader.readLine();
			while (line != null) {
				line = bufferedReader.readLine();
				if (line != null) {
					actual = line;
				}
			}
			fileReader.close();
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// compare the expected and actual line written to file
		assertEquals(expected, actual);
	}

}
