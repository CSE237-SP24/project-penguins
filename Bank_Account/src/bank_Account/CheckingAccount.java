package bank_Account;

//has no different methods from BankAccount, has no test suites for now.
public class CheckingAccount extends Bank_Account {
	
			

			public CheckingAccount(double interest) {
				super();
		        
			}

			public CheckingAccount(String name, double interest) {
				super(name);
		        
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


