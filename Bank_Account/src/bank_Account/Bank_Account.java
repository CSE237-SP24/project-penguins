package bank_Account;

public class Bank_Account {

	protected double balance;
	protected String name;
	protected String history;

	public Bank_Account() {
		this.balance = 0;
		this.name = "new_account";
		this.history = "";
	}

	public Bank_Account(String name) {
		this.balance = 0;
		this.name = name;
		this.history = "no_history";

	}

	// deposits a double to the bank account balance
	public double deposit(double amount) throws IllegalArgumentException{

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

	// withdraws a double from the bank account balance, throws exception if amount
	// is
	// non-positive or more than currently held in balance

	public double withdraw(double amount) throws IllegalArgumentException{

		if (amount < 0) {
			throw new IllegalArgumentException("Amount must be positive");
		}

		if (amount > this.balance) {
			throw new IllegalArgumentException("You cannot withdraw more money than you currently have");
		}

		this.balance -= amount;

		return this.balance;
	}

	// returns history of user transactions
	public String getHistory() {

		return this.history;

	}

	// can only append history, not modify it, adds newline.
	public String AppendHistory(String newHistory) {
		this.history += "\n" + newHistory;
		return this.history;
	}
}
