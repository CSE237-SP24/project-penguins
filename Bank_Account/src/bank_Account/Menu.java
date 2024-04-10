
package bank_Account;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import user_information.user_profile;

public class Menu extends Thread{

	private Scanner in;
	private user_profile currentProfile;
	private ArrayList<user_profile> profileList;



	public static void main(String[] args) {
		Menu mainMenu = new Menu();
		distributeThreads();

		mainMenu.makeProfile();

		// The program's main loop: continually re-prompts the user unless they quit
		mainMenu.runMainLoop();

		//ends both the song and bank account threads
		System.exit(0);

	}

	public Menu() {
		this.in = new Scanner(System.in);
		this.currentProfile = null;
		this.profileList = new ArrayList<user_profile>();
	}

	private void addProfile(user_profile profile) {
		this.profileList.add(profile);
		this.currentProfile = profile;
	}

	//used to start the song thread and the menu thread
	public static void distributeThreads(){
		System.out.println("Please turn on your sound if you wish to hear our background track");
		Menu main = new Menu();
		main.start();

	}
	//song playing thread
	public void run(){
		songPlayerCycler();
	}
	public void songPlayerCycler(){

//play the song during program execution
		String chosenSong = "soundFiles/battletheme10.wav";

		File songFile = new File(chosenSong);
			if (songFile.exists()) {
				AudioInputStream musicPlayerSystem = getAudioInputStreamFromFile(songFile);
				if(musicPlayerSystem != null) {
					Clip audioClip = getClipFromFile();
					processSong(audioClip,musicPlayerSystem);
				}
				else {
					return;
				}
			} else {
				System.out.println("The music file can't be found!!");
				System.out.println(System.getProperty("user.dir"));
			}
	}

	/* processSong
	 * @params an AudioClip System and an AudioInputStream
	 * preps the program for playing the song and starts the clip
	 */
	public void processSong(Clip audioClip,AudioInputStream musicPlayerSystem ){
		try {
			audioClip.open(musicPlayerSystem);
		} catch (LineUnavailableException clipUnableToBeRendered) {
			System.out.println("The required audio system java libraries were unable to be rendered in the program.");
			return;
		} catch (IOException fileNotPresent) {
			System.out.println("The required audio files were unable to be rendered in the program.");
			return;
		}

		while(true) {
			//start the song
			audioClip.start();
			audioClip.loop(audioClip.LOOP_CONTINUOUSLY);
		}

	}


	/* getAudioInputStreamFromFile
	 * @params none
	 * preps the AudioInputStream for playing the song
	 */
	public AudioInputStream getAudioInputStreamFromFile(File songFile){
		AudioInputStream musicPlayerSystem = null;// song
		
		
	
		try {
			musicPlayerSystem = AudioSystem.getAudioInputStream(songFile);
			return musicPlayerSystem;
		} catch (UnsupportedAudioFileException sampleAudioException) {
			System.out.println("Your audio system is incompatible with our application, apologies for the inconvenience.");
			return null;

		} catch (IOException fileNotFound) {
			System.out.println("The required audio files were unable to be rendered in the program.");
			return null;

		}

	}

	/* getClipFromFile
	 * @params none
	 * preps the clip for playing the song
	 */
	public Clip getClipFromFile(){
		Clip audioClip= null;
		try {
			audioClip = AudioSystem.getClip();
			return audioClip;
		} catch (LineUnavailableException clipUnableToBeCreated) {
			System.out.println("The required audio system java libraries were unable to be rendered in the program.");
			return null;
		}


	}

	//bank account functionality thread

	private void makeProfile() {
		user_profile newProfile = new user_profile();
		String username = getUsername(newProfile);
		String password = getPassword(newProfile);
		newProfile.setLoginInformation(username, password);
		this.addProfile(newProfile);
		System.out.println("Logged in as " + username + ".");
	}

	private String getUsername(user_profile newProfile) {
		System.out.println("Enter a username for your new account:");
		String username = in.next();
		while (!newProfile.isValidUsername(username)) {
			username = in.nextLine();
		}
		return username;
	}

	private String getPassword(user_profile newProfile) {
		System.out.println("Enter a password for your new account:");
		String password = in.next();
		while (!newProfile.isValidPassword(password)) {
			password = in.nextLine();
		}
		return password;
	}

	public void runMainLoop() {
		// Prompt user for a number (1 = view account, 2 = create account, 3 = logout, 4 = quit)
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
		System.out.println("Type 1 to log into an existing account:\nType 2 to create a new account:");
		int selection = getValidInt();
		while (selection != 1 && selection != 2) {
			System.out.println("Invalid input. Please type 1 or 2:");
			selection = getValidInt();
		}
		if (selection == 1) {
			user_profile attemptLogin = this.logIn();
			this.attemptLogin(attemptLogin);
		} else if (selection == 2) {
			this.makeProfile();
		}
	}
	
	private void attemptLogin(user_profile attemptLogin) {
		System.out.println("Enter the password for this account:");
		String password = in.next();
		while (!this.isActualPassword(attemptLogin, password)) {
			System.out.println("Not the correct password. Try again:");
			password = in.next();
		}
		this.currentProfile = attemptLogin;
	}
	
	private boolean isActualPassword(user_profile attemptLogin, String enteredPassword) {
		for (String p : attemptLogin.getLoginInformation().values()) {
		    if (p.equals(enteredPassword)) {
		    	return true;
		    }
		}
		return false;

	}

	private user_profile logIn() {
		System.out.println("Select an account to login by typing the associated number:");
		int counter = 0;
		for (user_profile account : profileList) {
			counter++;
			System.out.print(counter + " ");
			for (String username : account.getLoginInformation().keySet()) {
			    System.out.println(username);
			}
		}
		int selection = getValidInt();
		while (selection <= 0 || selection > counter) {
			System.out.println("Invalid number. Try again.");
			selection = getValidInt();
		}
		return profileList.get(selection - 1);
		
	}
	
	private boolean isExistingUsername(String username) {
		for (user_profile profile : profileList) {
			if (profile.getLoginInformation().containsKey(username)) {
				return true;
			}
		}
		System.out.println("Username does not exist. Try again:");
		return false;
	}


	private void viewExisting() {
		Bank_Account currentAccount = this.getUserToSelectBankAccount();
		int userAction = this.getUserSelectionToDepositWithdrawOrView();
		if (userAction == 1) {
			// Deposit
			double deposit = this.getDepositAmount();
			currentAccount.deposit(deposit);
			System.out.println("Balance is now: $" + currentAccount.getBalance());
		} else if (userAction == 2) {
			// Withdraw
			if (currentAccount.getBalance() == 0.0) {
				System.out.println("Bank account has no money to withdraw.");
			} else {
				double withdraw = this.getWithdrawAmount(currentAccount);
				currentAccount.withdraw(withdraw);
				System.out.println("Balance is now: $" + currentAccount.getBalance());
			}
		} else if (userAction == 3) {
			// View balance
			System.out.println("Current balance: $" + currentAccount.getBalance());
		}
	}

	private void createNew() {
		// Prompts the user for a number (1 = checking, 2 = savings)
		int accountType = this.getNewAccountType();
		String name = this.getNewAccountName();
		this.createNewAccount(name, accountType);
	}

	private int getNewAccountType() {
		System.out.println("Type 1 to create a checking account:\nType 2 to create a savings account:");
		int selection = getValidInt();
		while (selection != 1 && selection != 2) {
			System.out.println("Invalid input. Please type 1 or 2:");
			selection = getValidInt();
		}
		return selection;
	}

	private void displayingOptions() {
		System.out.println("Type 1 to view bank account(s): \nType 2 to create an bank account:");
	}

	private String getNewAccountName() {
		System.out.println("Type the name of the bank account to be created:");
		String name = in.next();
		return name;
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
		System.out.println("Type 1 to view existing bank accounts:\nType 2 to create a bank account:\nType 3 to log out:\nType 4 to quit the program:");
		int selection = getValidInt();
		while (selection != 1 && selection != 2 && selection != 3 && selection != 4) {
			System.out.println("Invalid input. Please type 1, 2, 3, or 4:");
			selection = getValidInt();
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
		int selection = getValidInt();
		while (selection <= 0 || selection > counter) {
			System.out.println("Invalid number. Try again.");
			selection = getValidInt();
		}
		return currentProfile.getAllBankAccounts().get(selection - 1);
	}

	private int getUserSelectionToDepositWithdrawOrView() {
		System.out.println("Type 1 to deposit into the bank account:\nType 2 to withdraw:\nType 3 to view the balance:");
		int selection = getValidInt();
		while (selection != 1 && selection != 2 && selection != 3) {
			System.out.println("Invalid input. Please type 1, 2, or 3:");
			selection = getValidInt();
		}
		return selection;
	}

	private double getDepositAmount() {
		System.out.println("How much would you like to deposit?");
		double amount = getValidDouble();

		while (amount < 0) {
			System.out.println("How much would you like to deposit (must be positive)?");
			amount = getValidDouble();
		}

		return amount;
	}

	private double getWithdrawAmount(Bank_Account account) {
		System.out.println("How much would you like to withdraw?:");
		double amount = getValidDouble();

		while (amount < 0 || amount > account.getBalance()) {
			System.out.println("How much would you like to deposit (must be positive and less than current balance)?:");
			amount = getValidDouble();
		}

		return amount;
	}

	private int getValidInt() {
		int selection;
		while (true) {
			String input = in.next();
			try {
				selection = Integer.parseInt(input);
				return selection;
			} catch (NumberFormatException ne) {
				System.out.println("Invalid input. Please enter a number:");
			}
		}
	}

	private double getValidDouble() {
		double selection;
		while (true) {
			String input = in.next();
			try {
				selection = Double.parseDouble(input);
				return selection;
			} catch (NumberFormatException ne) {
				System.out.println("Invalid input. Please enter a number:");
			}
		}
	}

}
