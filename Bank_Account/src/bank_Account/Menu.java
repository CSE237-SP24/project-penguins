package bank_Account;

import java.util.ArrayList;
import java.util.Scanner;
import user_information.user_profile;

public class Menu {

	private Scanner in;
	private user_profile profile;

	public static void main(String[] args) {
		Menu mainMenu = new Menu();
		
		user_profile profile = mainMenu.makeProfile();
		mainMenu.addProfile(profile);
		
		//The program's main loop: continually re-prompts the user unless they quit
		mainMenu.runMainLoop();
		
		System.out.println("User has quit. Goodbye!");
		
	}
	
	
	public Menu() {
		this.in = new Scanner(System.in);
		this.profile = null;
	}
	
	private void addProfile(user_profile profile) {
		this.profile = profile;
	}

	
	private user_profile makeProfile() {
		user_profile newProfile = new user_profile();
		String username = getUsername(newProfile);
		String password = getPassword(newProfile);
		newProfile.setLoginInformation(username, password);
		System.out.println("Logged in as " + username + ".");
		return newProfile;
	}
	
	private String getUsername (user_profile newProfile) {
		System.out.println("Enter a username for your new account:");
		String username = in.nextLine();
		while (!newProfile.isValidUsername(username)) {
			username = in.nextLine();
		}
		return username;
	}
	
	private String getPassword (user_profile newProfile) {
		System.out.println("Enter a password for your new account:");
		String password = in.nextLine();
		while (!newProfile.isValidPassword(password)) {
			password = in.nextLine();
		}
		return password;
	}
	
	public void runMainLoop() {
		//Prompt user for a number (1 = view account, 2 = create account, 3 = quit)
		int createOrViewSelection = this.getUserSelectionToCreateOrView();
		
		while (createOrViewSelection != 3) {
			
			//Prompts the user for a number (1 = deposit, 2 = withdraw, 3 = view balance)
			if (createOrViewSelection == 1) {
				//View existing bank account
				this.viewExisting();
				
			} else if (createOrViewSelection == 2) {
				//Create a new bank account
				this.createNew();
			
			}
			
			//After desired action is completed, re-prompt user
			createOrViewSelection = this.getUserSelectionToCreateOrView();
		}
	}
 
	private void viewExisting() {
		Bank_Account currentAccount = this.getUserToSelectBankAccount();
		int userAction = this.getUserSelectionToDepositWithdrawOrView();
		if (userAction == 1) {
			//Deposit
			double deposit = this.getDepositAmount();
			currentAccount.deposit(deposit);
			System.out.println("Balance is now: " + currentAccount.getBalance());
		} else if (userAction == 2) {
			//Withdraw
			if (currentAccount.getBalance() == 0.0) {
				System.out.println("Account has no money to withdraw.");
			} else {
				double withdraw = this.getWithdrawAmount(currentAccount);
				currentAccount.withdraw(withdraw);
				System.out.println("Balance is now: $" + currentAccount.getBalance());
			}
		} else if (userAction == 3){
			//View balance
			System.out.println("Current balance: $" + currentAccount.getBalance());
		}
	}
	
	private void createNew() {
		//Prompts the user for a number (1 = checking, 2 = savings)
		int accountType = this.getNewAccountType();
		String name = this.getNewAccountName();
		this.createNewAccount(name, accountType);
	}
	
	private int getNewAccountType() {
		System.out.println("Type 1 to create a checking account:\nType 2 to create a savings account:");
		int selection = in.nextInt();
		while (selection != 1 && selection != 2) {
			System.out.println("Invalid input. Please type 1 or 2:");
			selection = in.nextInt();
		}
		return selection;
	}

	
	public void displayingOptions() {
		System.out.println("Type 1 to view account(s): \nType 2 to create an account:");
	}

	public String getNewAccountName() {
		System.out.println("Type the name of the account to be created:");
		String name = in.next();
		return name;
	}
	
	public void createNewAccount(String name, int accountType) {
		if (accountType == 1) {
			//Checkings account
			Bank_Account account = new Bank_Account(name);
			profile.getAllBankAccounts().add(account);
		} else {
			//Savings account
			SavingsAccount account = new SavingsAccount(name, 0.1, 10000);
			profile.getAllBankAccounts().add(account);
		}
	
	
	}
	
	public int getUserSelectionToCreateOrView() {
		System.out.println("Type 1 to view existing accounts:\nType 2 to create an account:\nType 3 to quit the program:");
		int selection = in.nextInt();
		while (selection != 1 && selection != 2 && selection != 3) {
			System.out.println("Invalid input. Please type 1, 2, or 3:");
			selection = in.nextInt();
		}
		if (selection == 1 && profile.getAllBankAccounts().size() == 0) {
			System.out.println("There are no accounts to view. Please create an account first.");
			return 2;
		}
		return selection;
	}
	
	public Bank_Account getUserToSelectBankAccount() {
		System.out.println("Select a bank account by typing the associated number:");
		int counter = 0;
		for (Bank_Account account : profile.getAllBankAccounts()) {
			counter++;
			System.out.println(counter + " " + account.name);
		}
		int selection = in.nextInt();
		while (selection <= 0 || selection > counter) {
			System.out.println("Invalid number. Try again.");
			selection = in.nextInt();
		}
		return profile.getAllBankAccounts().get(selection-1);
	}
	
	private int getUserSelectionToDepositWithdrawOrView() {
		System.out.println("Type 1 to deposit into the account:\nType 2 to withdraw:\nType 3 to view the balance:");
		int selection = in.nextInt();
		while (selection != 1 && selection != 2 && selection != 3) {
			System.out.println("Invalid input. Please type 1, 2, or 3:");
			selection = in.nextInt();
		}
		return selection;
	}
	
	public double getDepositAmount() {
		System.out.println("How much would you like to deposit?:");
		double amount = in.nextDouble();

		while (amount < 0) {
			System.out.println("How much would you like to deposit (must be positive)?:");
			amount = in.nextDouble();
		}

		return amount;
	}

	
	public double getWithdrawAmount(Bank_Account account) {
		System.out.println("How much would you like to withdraw?:");
		double amount = in.nextDouble();

		while (amount < 0 || amount > account.getBalance()) {
			System.out.println("How much would you like to deposit (must be positive and less than current balance)?:");
			amount = in.nextDouble();
		}

		return amount;
	}

}