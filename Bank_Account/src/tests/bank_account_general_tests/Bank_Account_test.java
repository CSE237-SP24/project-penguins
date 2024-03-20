package tests.bank_account_general_tests;

import bank_Account.Bank_Account;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Bank_Account_test {
	
	Bank_Account testAccount;
	
	@BeforeEach
	void setUp() {
		 testAccount = new Bank_Account();
	}
	
	@Test
	void testSimpleDeposit() {
		// 1. Setup objects
		

		// 2. Call the method being tested
		testAccount.deposit(25);

		// 3. Use assertions to verify results
		assertEquals(25.0, testAccount.getBalance(), 0.01);

	}

	@Test
	void testNegativeDeposit() {
		

		try {
			testAccount.deposit(-25);
			fail();
		} catch (IllegalArgumentException e) {
			// We expect this, cannot deposit negative amount
			assertTrue(true);
		}

	}
	
	@Test
	void testAboveMaxWithdrawl() {
		
		testAccount.deposit(10);
		try {
			testAccount.withdraw(11);
			fail();
		} catch (IllegalArgumentException e) {
			// We expect this, cannot deposit negative amount
			assertTrue(true);
		}

	}

	@Test
	void testNoMoneyDeposit() {
		
		testAccount.deposit(0);

		assertEquals(0, testAccount.getBalance(), 0.01);

	}
	
	@Test
	void testSetName() {
		
		Bank_Account namedAccount = new Bank_Account("legitname");
		
		assertEquals(namedAccount.getName(),"legitname");
	}
	
	@Test
	void testNoName() {
		assertEquals(testAccount.getName(),"new_account");
	}
	
	@Test //more developed history tests in classes that manipulate history
	void testFreshHistory() {
		assertEquals(testAccount.getHistory(),"");
	}
	
	@Test 
	void testAppendedHistory() {
		testAccount.AppendHistory("5e-32 dogecoin");
		assertEquals("\n"+"5e-32 dogecoin",testAccount.getHistory());
	}
	

}
