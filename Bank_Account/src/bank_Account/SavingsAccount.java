package bank_Account;

public class SavingsAccount extends Bank_Account {

	private double interest;
	private double withdraw_limit;

	public SavingsAccount(double interest) {
		super();
		this.interest = interest;
		this.withdraw_limit = -1;

	}

	public SavingsAccount(double interest, double withdraw_limit) {
		super();
		this.interest = interest;
		this.withdraw_limit = withdraw_limit;

	}

	public SavingsAccount(String name, double interest, double withdraw_limit) {
		super(name);
		this.interest = interest;
		this.withdraw_limit = withdraw_limit;
	}

	// deposits a double to the bank account balance
	public double deposit(double amount) {

		return super.deposit(amount);

	}

	public double getBalance() {
		return super.getBalance();
	}

	// withdraws a double from the bank account balance
	public double withdraw(double amount) {

		if (amount > this.withdraw_limit) {

			throw new IllegalArgumentException("Amount must be below withdraw limit");

		}

		return super.withdraw(amount);
	}

	public double applyInterest() {

		balance *= (1 + this.interest);

		return this.balance;

	}

	public double getWithdraw_Limit() {
		return this.withdraw_limit;
	}

	public void setWithdraw_Limit(double withdraw_limit) {
		this.withdraw_limit = withdraw_limit;
	}

}
