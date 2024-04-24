package Utility;
import bank_Account.Bank_Account;
import user_information.UserProfile;

public class InputHandler {
	public InputHandler() {}
	
	public void handleDeposit(Bank_Account currentAccount,  ParserUtils parse) {
		System.out.println("How much would you like to deposit?");
		double deposit = parse.getValidDouble();

		try {

			currentAccount.deposit(deposit);
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid Amount, please try again");
		}

		System.out.println("Balance is now: $" + currentAccount.getBalance());
		
	}
	
	public void handleWithdrawal(Bank_Account currentAccount,  ParserUtils parse) {
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
	
	}
	
	
}
