package bankapp;

import java.util.Scanner;
//SavingsAccounts 
public class SavingsAccount extends BankAccount {
	
	//static Scanner in = new Scanner(System.in);
	private double interest;

	public SavingsAccount(double interest) {
		super();
        this.interest = interest;
	}

	public SavingsAccount(String name, double interest) {
		super(name);
        this.interest = interest;
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
		return super.getBalance();
	}
	
	//withdraws a double from the bank account balance
	public double withdraw(double amount) {
		
		if (amount < 0) {
			throw new IllegalArgumentException("Amount must be positive");
		}
		
		this.balance -= amount;
		
		return this.balance;
	}

	
}
