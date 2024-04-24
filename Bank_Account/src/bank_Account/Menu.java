
package bank_Account;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import user_information.UserProfile;
import Utility.Pair;
import Utility.ParserUtils;

public class Menu extends Thread {

	private Scanner in;
	private UserProfile currentProfile;
	private Map<Pair<String, String>, UserProfile> profileList;
	private ParserUtils parse;

	public static void main(String[] args) {
		Menu mainMenu = new Menu();

		mainMenu.makeProfile();

		// The program's main loop: continually re-prompts the user unless they quit
		mainMenu.runMainLoop();

	}

	public Menu() {
		this.in = new Scanner(System.in);
		this.currentProfile = null;
		this.profileList = new HashMap<Pair<String, String>, UserProfile>();
		this.parse = new ParserUtils();
	}

	private void addProfile(UserProfile profile) {

		Pair<String, String> userAndPass = profile.getLoginInformation();

		this.profileList.put(new Pair<String, String>(userAndPass.first(),
				userAndPass.second()), profile);

		this.currentProfile = profile;
	}

	// bank account functionality thread

	private void makeProfile() {
		UserProfile newProfile = new UserProfile();
		Pair<String, String> userAndPass = getUserAndPass(newProfile);
		Pair<String, String> firstAndLast = getFirstAndLastName();
		newProfile.setLoginInformation(userAndPass.first(), userAndPass.second());
		newProfile.setFirstName(firstAndLast.first());
		newProfile.setLastName(firstAndLast.second());
		String ssn = parse.getValidSSN();
		newProfile.setSocialSecurityNumber(ssn);
		this.addProfile(newProfile);
		System.out.println("Logged in as " + userAndPass.first() + "!");
	}


	private Pair<String, String> getUserAndPass(UserProfile newProfile) {
		System.out.println("Enter a username for your new user profile:");
		String username = in.nextLine();
		while (!newProfile.isValidUsername(username)) {
			System.out.println("Enter a username for your new user profile:");
			username = in.nextLine();
		}

		System.out.println("Enter a password for your new user profile:");
		String password = in.nextLine();

		while (!newProfile.isValidPassword(password)) {
			System.out.println("Enter a password for your new user profile:");
			password = in.nextLine();
		}

		return new Pair<String, String>(username, password);
	}
	
	private Pair<String, String> getFirstAndLastName() {
		System.out.println("Enter your first name:");
		String first = in.nextLine();

		System.out.println("Enter your last name:");
		String last = in.nextLine();

		return new Pair<String, String>(first, last);
	}

	public void runMainLoop() {
		// Prompt user for a number (1 = view account, 2 = create account, 3 = logout, 4
		// = quit)
		int createOrViewSelection = this.getUserSelectionToCreateOrView();

		while (createOrViewSelection != 4) {

			if (createOrViewSelection == 1) {
				// View existing bank account
				this.viewExisting();

			} else if (createOrViewSelection == 2) {
				// Create a new bank account
				this.createNew();

			} else if (createOrViewSelection == 3) {
				this.logOut();
			}

			// After desired action is completed, re-prompt user
			createOrViewSelection = this.getUserSelectionToCreateOrView();
		}

		System.out.println("User has quit. Goodbye!");
	}

	private void logOut() {
		System.out.println("Type 1 to log into an existing user profile:\nType 2 to create a new user profile:");
		int selection = parse.getValidInt();
		while (selection != 1 && selection != 2) {
			System.out.println("Invalid input. Please type 1 or 2:");
			selection = parse.getValidInt();
		}
		if (selection == 1) {

			this.currentProfile = this.logIn();

		} else if (selection == 2) {
			this.makeProfile();
		}
	}

	private UserProfile logIn() {
		System.out.println("Below is a list of all existing user profile usernames:");

		for (UserProfile account : profileList.values()) {

			System.out.print(account.getLoginInformation().first() + " owned by ");
			System.out.println(account.getFirstName() + " " + account.getLastName());

		}

		String user;
		Pair<String, String> userAndPass = new Pair<String, String>("", "");

		while (!this.profileList.keySet().contains(userAndPass)) {

			System.out.println("Enter the username of the user profile you would like to login to:");
			user = in.next();

			System.out.println("Enter the password associated with this username:");
			userAndPass = new Pair<String, String>(user, in.next());

			if (!this.profileList.keySet().contains(userAndPass)) {
				System.out.println("Incorrect username or password. Please try again.");
			} else {
				System.out.println("Successfully logged into " + user + "!");
			}
		}

		return this.profileList.get(userAndPass);

	}

	private void viewExisting() {
		Bank_Account currentAccount = this.getUserToSelectBankAccount();
		int userAction = this.getUserSelectionToDepositWithdrawOrView();
		if (userAction == 1) {
			// Deposit
			System.out.println("How much would you like to deposit?");
			double deposit = parse.getValidDouble();

			try {

				currentAccount.deposit(deposit);
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid Amount, please try again");
			}

			System.out.println("Balance is now: $" + currentAccount.getBalance());
		} else if (userAction == 2) {
			// Withdraw
			if (currentAccount.getBalance() == 0.0) {
				System.out.println("Bank account has no money to withdraw.");
			} else {
				System.out.println("How much would you like to withdraw?:");
				double withdraw = parse.getValidDouble();
				try {
					currentAccount.withdraw(withdraw);
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid Amount, please try again");
				}

				System.out.println("Balance is now: $" + currentAccount.getBalance());
			}
		} else if (userAction == 3) {
			// View balance
			System.out.println("Current balance: $" + currentAccount.getBalance());
		}
	}

	private void createNew() {
		// Prompts the user for a number (1 = checking, 2 = savings)
		System.out.println("Type 1 to create a checking account:\nType 2 to create a savings account:");
		int selection = parse.getValidInt();

		while (selection != 1 && selection != 2) {
			System.out.println("Invalid input. Please type 1 or 2:");
			selection = parse.getValidInt();
		}

		System.out.println("Type the name of the bank account to be created:");
		String name = in.next();

		this.createNewAccount(name, selection);
	}

	private void createNewAccount(String name, int accountType) {
		if (accountType == 1) {
			// Checkings account
			Bank_Account account = new Bank_Account(name);
			currentProfile.getAllBankAccounts().add(account);
		} else {
			// Savings account
			SavingsAccount account = new SavingsAccount(name, 0.1, 10000);
			currentProfile.getAllBankAccounts().add(account);
		}

	}

	private int getUserSelectionToCreateOrView() {
		System.out.println(
				"Type 1 to view existing bank accounts:\nType 2 to create a bank account:\nType 3 to log out:\nType 4 to quit the program:");
		int selection = parse.getValidInt();
		while (selection != 1 && selection != 2 && selection != 3 && selection != 4) {
			System.out.println("Invalid input. Please type 1, 2, 3, or 4:");
			selection = parse.getValidInt();
		}
		if (selection == 1 && currentProfile.getAllBankAccounts().size() == 0) {
			System.out.println("There are no bank accounts to view. Please create a bank account first.");
			return 2;
		}
		return selection;
	}

	private Bank_Account getUserToSelectBankAccount() {
		System.out.println("Select a bank account by typing the associated number:");
		int counter = 0;
		for (Bank_Account account : currentProfile.getAllBankAccounts()) {
			counter++;
			System.out.println(counter + " " + account.name);
		}

		int selection = parse.getValidInt();

		while (selection <= 0 || selection > counter) {

			System.out.println("Invalid number. Try again.");

			selection = parse.getValidInt();

		}

		return currentProfile.getAllBankAccounts().get(selection - 1);
	}

	private int getUserSelectionToDepositWithdrawOrView() {
		System.out
				.println("Type 1 to deposit into the bank account:\nType 2 to withdraw:\nType 3 to view the balance:");
		int selection = parse.getValidInt();
		while (selection != 1 && selection != 2 && selection != 3) {
			System.out.println("Invalid input. Please type 1, 2, or 3:");
			selection = parse.getValidInt();
		}
		return selection;
	}

}
