package bankapp;

import java.util.Scanner;

public class BankAccount {
	
	static Scanner in = new Scanner(System.in);
	protected double balance;
	protected String name;
	
	public BankAccount() {
		this.balance = 0;
		this.name = "new_account";
		
	}

	public BankAccount(String name) {
		this.balance = 0;
		this.name = name;
		
	}

	//deposits a double to the bank account balance
	public double deposit(double amount) {
		
		if (amount < 0) {
			throw new IllegalArgumentException("Amount must be positive");
		}
		
		this.balance += amount;
		return this.balance;
	}


	public double getBalance() {
		return this.balance;
	}

	public String getName() {
		return this.name;
	}
	
	//withdraws a double from the bank account balance
	public double withdraw(double amount) {
		
		if (amount < 0) {
			throw new IllegalArgumentException("Amount must be positive");
		}
		
		this.balance -= amount;
		
		return this.balance;
	}

	// //takes user input to extract name
	// static String prompt() {
		
	// 	String inputName = "s";
		
	// 	while(inputName.length()<5){
	// 		System.out.println("Please enter a name for your bank account, \n"+
	// 			"ensuring the name is at least 5 characters.");
			
	// 		inputName = in.nextLine();
	// 	}
	// 	return inputName;
		
	// }
	
}
