package bankapp;

public class BankAccount {

	private double balance;

	public BankAccount() {
		this.balance = 0;
	}

	public void deposit(double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Amount must to positive");
		}
		this.balance += amount;
	}

	public double getBalance() {
		return balance;
	}
}
