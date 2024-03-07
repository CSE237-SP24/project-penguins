package bankapp;

import java.util.Scanner;

public class Menu {

	private Scanner in;
	private BankAccount account;

	public static void main(String[] args) {
		Menu mainMenu = new Menu();
		mainMenu.displayingOptions();
		double amount = mainMenu.getValidUserInput();
		mainMenu.processingUserSelection(amount);
	}

	public Menu() {
		this.in = new Scanner(System.in);
		this.account = new BankAccount();
	}

	public void displayingOptions() {
		System.out.println("How much would you like to deposit? ");
	}

	public double getValidUserInput() {
		double amount = in.nextDouble();

		while (amount < 0) {
			System.out.println("How much would you like to deposit (must be positive)? ");
			amount = in.nextDouble();
		}

		return amount;
	}

	public void processingUserSelection(double amount) {
		account.deposit(amount);

		System.out.println("Your balance is now: $" + account.getBalance());
	}

	public BankAccount getAccount() {
		return account;
	}
}
