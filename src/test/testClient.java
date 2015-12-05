package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import bank.Bank;
import bank.KBBank;
import client.Client;

public class testClient {

	@Test
	public void testClient1() {
		Client cl = new Client("hanul");
		Bank bank = new KBBank();
		cl.openAccount(bank);

		assertEquals(2000000, cl.getAsset());

		cl.deposit(bank, 50000);
		assertEquals(50000, cl.getBalance(bank));
		assertEquals(1950000, cl.getAsset());

		cl.withdraw(bank, 10000);
		assertEquals(40000, cl.getBalance(bank));
		assertEquals(1960000, cl.getAsset());

		cl.loan(bank, 20000);
		assertEquals(20000, cl.getDebt(bank));
		assertEquals(1980000, cl.getAsset());

		cl.repay(bank, 10000);
		assertEquals(10000, cl.getDebt(bank));
		assertEquals(1970000, cl.getAsset());

		cl.repayOnAccount(bank, 10000);
		assertEquals(0, cl.getDebt(bank));
		assertEquals(30000, cl.getBalance(bank));

		assertEquals("hanul", cl.getName());
	}

}
