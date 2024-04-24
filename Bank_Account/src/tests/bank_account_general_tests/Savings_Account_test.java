package tests.bank_account_general_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bank_Account.*;

class Savings_Account_test {

	SavingsAccount testAccount;

	@BeforeEach
	void setUp() {
		testAccount = new SavingsAccount(0.10);
	}

	@Test
	void testInterest() {
		testAccount.deposit(25);
		testAccount.applyInterest();
		// 3. Use assertions to verify results
		assertEquals(25.0 * (1 + 0.10), testAccount.getBalance(), 0.01);

	}

	@Test
	void testWithdrawLimit() {

		testAccount.setWithdraw_Limit(10);
		assertEquals(testAccount.getWithdraw_Limit(), 10);

	}

	@Test
	void testOverWithdraw() {

		testAccount.setWithdraw_Limit(10);

		try {
			testAccount.withdraw(11);
			fail();
		} catch (IllegalArgumentException e) {
			// We expect this, cannot deposit negative amount
			assertTrue(true);
		}

	}

}
