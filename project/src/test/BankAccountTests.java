package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;

class BankAccountTests {

	@Test
	void testSimpleDeposit() {
		// 1. Setup objects
		BankAccount testAccount = new BankAccount();

		// 2. Call the method being tested
		testAccount.deposit(25);

		// 3. Use assertions to verify results
		assertEquals(25.0, testAccount.getBalance(), 0.01);

	}

	@Test
	void testNegativeDeposit() {
		BankAccount testAccount = new BankAccount();

		try {
			testAccount.deposit(-25);
			fail();
		} catch (IllegalArgumentException e) {
			// We expect this, cannot deposit negative amount
			assertTrue(true);
		}

	}

}
