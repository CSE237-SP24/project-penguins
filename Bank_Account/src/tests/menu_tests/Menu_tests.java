package tests.menu_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import bank_Account.Menu;
import user_information.UserProfile;

public class Menu_tests {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    Menu testMenu;
    UserProfile testUser;


    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void testStandardMenuRuOutput() {
        String phaseOne = "JohnDoe\nAValidPassword123#\nJohn\nDoe\n";
        String phaseTwo = "123456789\n4";
        InputStream in = new SequenceInputStream(
            new ByteArrayInputStream(phaseOne.getBytes()), 
            new ByteArrayInputStream(phaseTwo.getBytes()));
        System.setIn(in);

        String expectedOutput = "Enter a username for your new user profile:\nEnter a password for your new user profile:\nEnter your first name:\nEnter your last name:\nEnter your social security number:\nLogged in as JohnDoe!\nType 1 to view existing bank accounts:\nType 2 to create a bank account:\nType 3 to log out:\nType 4 to quit the program:\nUser has quit. Goodbye!\n";
        
        Menu.main(new String[0]);
        
        assertEquals(expectedOutput, outContent.toString());
        
    }

    @Test
    void testMenuRunOutputWithErrorMessages() {
        String phaseOne = "Invalid Name\nValidName\nInvalid Password\nAValidPassword123#\nJohn\nDoe\n";
        String phaseTwo = "123456789\n4";
        InputStream in = new SequenceInputStream(
            new ByteArrayInputStream(phaseOne.getBytes()), 
            new ByteArrayInputStream(phaseTwo.getBytes()));
            System.setIn(in);
            
        String expectedOutput = "Enter a username for your new user profile:\n\n***Your username must not contain spaces.***\n\nEnter a username for your new user profile:\nEnter a password for your new user profile:\n\n***Your password must not contain [~, ^] characters due to their use in filing, It also can not contain spaces.***\n\nEnter a password for your new user profile:\nEnter your first name:\nEnter your last name:\nEnter your social security number:\nLogged in as ValidName!\nType 1 to view existing bank accounts:\nType 2 to create a bank account:\nType 3 to log out:\nType 4 to quit the program:\nUser has quit. Goodbye!\n";
        Menu.main(new String[0]);
        
        assertEquals(expectedOutput, outContent.toString());
        }
        
    @Test

    void testMenuRunOutputDeposit() {
        String phaseOne = "Invalid Name\nValidName\nInvalid Password\nAValidPassword123#\nJohn\nDoe\n";
        String phaseTwo = "123456789\n2\n1\n";
		String phaseThree = "Checking account\n1\n1\n1\n500\n4";
		String phaseFour = "Checking account\n1\n1\n1\n500\n4";

        InputStream inOne = new SequenceInputStream(
            new ByteArrayInputStream(phaseOne.getBytes()), 
            new ByteArrayInputStream(phaseTwo.getBytes()));
		InputStream inTwo  = new SequenceInputStream(
			new ByteArrayInputStream(phaseThree.getBytes()),
			new ByteArrayInputStream(phaseFour.getBytes()));

		InputStream in = new SequenceInputStream(inOne, inTwo);
       
		System.setIn(in);

        String expectedOutput = "Enter a username for your new user profile:\n\n***Your username must not contain spaces.***\n\nEnter a username for your new user profile:\nEnter a password for your new user profile:\n\n***Your password must not contain [~, ^] characters due to their use in filing, It also can not contain spaces.***\n\nEnter a password for your new user profile:\nEnter your first name:\nEnter your last name:\nEnter your social security number:\nLogged in as ValidName!\nType 1 to view existing bank accounts:\nType 2 to create a bank account:\nType 3 to log out:\nType 4 to quit the program:\nType 1 to create a checking account:\nType 2 to create a savings account:\nType the name of the bank account to be created:\nType 1 to view existing bank accounts:\nType 2 to create a bank account:\nType 3 to log out:\nType 4 to quit the program:\nInvalid input. Please enter a number:\nInvalid input. Please enter a number:\nSelect a bank account by typing the associated number:\n1 Checking\nType 1 to deposit into the bank account:\nType 2 to withdraw:\nType 3 to view the balance:\nType 4 to print account history:\nHow much would you like to deposit?\nBalance is now: $500.0\nType 1 to view existing bank accounts:\nType 2 to create a bank account:\nType 3 to log out:\nType 4 to quit the program:\nUser has quit. Goodbye!\n";

        Menu.main(new String[0]);
        
        assertEquals(expectedOutput, outContent.toString());
    }
        
}
