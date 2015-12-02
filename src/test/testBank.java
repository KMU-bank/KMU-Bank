package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import bank.Bank;
import bank.KBBank;

public class testBank {

	@Test
	public void testBank1(){
		Bank bank = new KBBank();
		
		String accountNumber = bank.openAccount("hanul");
		
		bank.deposit(accountNumber, 100000);
		assertEquals(100000, bank.getBalance(accountNumber));
		
		bank.withdraw(accountNumber, 10000);
		assertEquals(90000, bank.getBalance(accountNumber));
		
		bank.loan(accountNumber, 5000);
		assertEquals(5000, bank.getDebt(accountNumber));
		
		bank.repay(accountNumber, 2000);
		assertEquals(3000, bank.getDebt(accountNumber));
		
		bank.repayOnAccount(accountNumber, 2000);
		assertEquals(1000, bank.getDebt(accountNumber));
		assertEquals(88000, bank.getBalance(accountNumber));
		
		String subAccountNumber = bank.openAccount("chae");
		bank.transfer(accountNumber, subAccountNumber, 50000);
		assertEquals(50000, bank.getBalance(subAccountNumber));
		assertEquals(38000, bank.getBalance(accountNumber));
		
		bank.timeLeapYear();
		assertEquals((int)(50000 * 1.21), bank.getBalance(accountNumber));
	}
}
