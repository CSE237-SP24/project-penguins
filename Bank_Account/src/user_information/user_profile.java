package user_information;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;
import bank_Account.*;
import Utility.Pair;
public class user_profile {

	// Components of a user_profile (currently used fields)
	private Pair<String,String> loginInformation;

	// Other account fields
	private String dateOfBirth;
	private String securityQuestion;
	private String securityQuestionAnswer;

	private String firstName;
	private String lastName;
	private String profileID;
	private String socialSecurityNumber;

	// characters that separate crucial filing information
	private ArrayList<String> filingCharacters;

	// Users Bank Accounts
	private ArrayList<Bank_Account> allBankAccounts = new ArrayList<Bank_Account>();

	// default userProfile constructor
	public user_profile() {

		this.firstName = "No First Name.";
		this.lastName = "No Last Name.";

		this.loginInformation = new Pair<String, String>("No Username", "No Password");
		
		this.dateOfBirth = "No Date Of Birth has been Inputted.";
		this.securityQuestion = "No Security Question has been Inputted.";
		this.securityQuestionAnswer = "No Security Answer has been Inputted.";

		this.profileID = "No profile number generated.";
		this.socialSecurityNumber = "No social security number entered for this profile.";
		// TODO: Filing characters will be added here as development continues
		this.filingCharacters = new ArrayList<>();
		this.filingCharacters.add("~");
		this.filingCharacters.add("^");

	}

	/*
	 * getLoginInformation
	 * returns login information for a given user (username, password)
	 */
	public Pair<String, String> getLoginInformation() {

		return this.loginInformation;
	}

	/*
	 * ssetLoginInformation
	 * updates login information for a given user (username, password)
	 * 
	 * @params a possible username and password
	 * 
	 * @return boolean, returns true if the username and password were legitimate
	 * values and they were set correctly , false if the value(s) were invalid
	 */
	public boolean setLoginInformation(String userName, String passWord) {

		// validate username
		if (!isValidUsername(userName)) {
			return false;
		}
		// validate password
		if (!isValidPassword(passWord)) {
			return false;
		}
		// updates the username and password field if both are valid
		Pair<String, String> newLoginInformation = new Pair<>(userName, passWord);
		
		this.loginInformation = newLoginInformation;
		System.out.println("Username and Password Successfuly Generated and Recorded");
		return true;

	}

	/*
	 * isNumeric makes sure a given input inputString follows the following
	 * criteria:
	 * 
	 * @params a possible string
	 * 
	 * @return boolean, returns true if the string contains all numerical characters
	 * , false if it contains nonnumeric characters
	 */
	public boolean isNumeric(String inputString) {

		for (char c : inputString.toCharArray()) {
			if (Character.isDigit(c)) {
				return true;
			}
		}
		return false;

	}

	/*
	 * containsSpaces checks if a string contains spaces
	 * 
	 * @params a possible string
	 * 
	 * @return boolean, returns true if the string contains a space , false if it
	 * contains no spaces
	 */
	public boolean containsSpaces(String inputString) {

		return inputString.contains(" ");
	}

	/*
	 * isValidUsername makes sure a given input username follows the following
	 * criteria: - does not start with a number - does not contain spaces - TODO:
	 * check to make sure a username is not already used in the filing system
	 * 
	 * @params a possible valid username
	 * 
	 * @return boolean, returns true if the username is valid , false if is not
	 * valid
	 */
	public boolean isValidUsername(String sampleUsername) {
		if (isNumeric(sampleUsername.substring(0, 1))) {
			System.out.println("\n***Your username must not start with a number.***\n");
			return false;
		}

		if (containsSpaces(sampleUsername)) {
			System.out.println("\n***Your username must not contain spaces.***\n");
			return false;
		}
		// checks to make sure the username does not contain filing characters
		for (int i = 0; i < sampleUsername.length(); i++) {
			if (filingCharacters.contains(sampleUsername.substring(i, i + 1)) || containsSpaces(sampleUsername)) {
				System.out.println("\n***Your username must not contain " + this.filingCharacters
						+ "characters due to their use in filing, It also can not contain spaces.***\n");
				return false;
			}
		}
		return true;
	}

	public boolean isValidPassword(String samplePassword) {
		return isValidPasswordPreliminaryVerifier(samplePassword) && isValidPasswordSecondaryVerifier(samplePassword);
	}

	/*
	 * isValidPasswordPreliminaryVerifier makes sure a given input password follows
	 * the following
	 * criteria: - contains a special character
	 * - does not contain spaces
	 * 
	 * @params a possible valid password
	 * 
	 * @return boolean, returns true if the password is valid , false if is not
	 * valid
	 */
	public boolean isValidPasswordPreliminaryVerifier(String samplePassword) {
		ArrayList<String> specialCharsList = new ArrayList<>(Arrays.asList("@", "#", "$", "%", "&", "*", "(", ")", "`",
				":", "<", ">", "/", "?", "[", "]", "{", "}", "|", "=", "-", "_"));
		boolean validPassword = false;
		for (int i = 0; i < samplePassword.length(); i++) {
			if (filingCharacters.contains(samplePassword.substring(i, i + 1)) || containsSpaces(samplePassword)) {
				System.out.println("\n***Your password must not contain " + this.filingCharacters
						+ " characters due to their use in filing, It also can not contain spaces.***\n");
				return false;
			}
			if (specialCharsList.contains(samplePassword.substring(i, i + 1))) {
				validPassword = true;
			}
		}
		if (!validPassword) {
			System.out.println("Your Password must contain a special character:" + specialCharsList);
		}
		return validPassword;
	}

	/*
	 * isValidPasswordSecondaryVerifier makes sure a given input password follows
	 * the following
	 * criteria: - does not start with a number
	 * - is longer than 8 characters
	 * 
	 * @params a possible valid password
	 * 
	 * @return boolean, returns true if the password is valid , false if is not
	 * valid
	 */
	public boolean isValidPasswordSecondaryVerifier(String samplePassword) {

		if (samplePassword.length() < 8) {// checks if the password is at least 8 characters long
			System.out.println("\n***Your password must be at least 8 characters long.***\n");
			return false;
		}
		if (isNumeric(samplePassword.substring(0, 1))) {
			System.out.println("\n***Your password must not start with a number.***\n");
			return false;
		} // checks to make sure the password has a special character

		return true;
	}

	public ArrayList<Bank_Account> getAllBankAccounts() {
		return allBankAccounts;
	}

	public void setAllBankAccounts(ArrayList<Bank_Account> allBankAccounts) {
		this.allBankAccounts = allBankAccounts;
	}

}
