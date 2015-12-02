package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import account.Account;
import account.Accounts;

public class testAccount {

	@Test
	public void testAccount1(){
		Account account = new Account("123", "abc");
		assertEquals("abc", account.getName());
		assertEquals(0, account.getBalance());
		
		account.deposit(10000);
		assertEquals(10000, account.getBalance());
		
		account.withdraw(5000);
		assertEquals(5000, account.getBalance());
		
		account.loan(100000);
		assertEquals(100000, account.getDebt());
		
		account.repay(50000);
		assertEquals(50000, account.getDebt());
		
		account.repayOnAccount(1000);
		assertEquals(49000, account.getDebt());
		assertEquals(4000, account.getBalance());
		
		account.transfer("234", 3000);
		assertEquals(1000, account.getBalance());
		
		account.makeCard();
		account.useCard("술값", 500);
		assertEquals(500, account.getBalance());
		
		account.lostReport();
		account.useCard("밥값", 300);
		assertEquals(500, account.getBalance());
		
		account.findReport();
		account.useCard("껌값", 100);
		assertEquals(400, account.getBalance());
	}
	
	public void testAccounts1(){
		Accounts acs = Accounts.getInstance();
		assertEquals(acs, acs.getInstance());
	}
}
