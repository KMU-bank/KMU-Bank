package pictest;

import static org.junit.Assert.*;

import org.junit.Test;

import bank.Bank;
import bank.KBBank;

public class BankTest {

	@Test
	public void test1() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertTrue(bank.deposit(accountNumber, 5000000));
		assertFalse(bank.withdraw(accountNumber, -1));
		
		assertFalse(bank.transfer(accountNumber, accountNumber, 0));
		
		assertTrue(bank.loan(accountNumber, 100));
		assertFalse(bank.repay(accountNumber, 5000000));
		assertFalse(bank.repayOnAccount(accountNumber, -1));
	}
	
	@Test
	public void test2() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertFalse(bank.deposit(accountNumber, -1));
		assertFalse(bank.withdraw(accountNumber, 5000000));
		
//		assertFalse(bank.transfer(accountNumber, "111111", 5000000));
		
		assertTrue(bank.loan(accountNumber, 100));
		assertFalse(bank.repay(accountNumber, 0));
		assertFalse(bank.repayOnAccount(accountNumber, 0));
	}
	
	@Test
	public void test3() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertFalse(bank.deposit(accountNumber, -1));
		assertFalse(bank.withdraw(accountNumber, 100));
		
		assertFalse(bank.transfer(accountNumber, accountNumber, -1));
		
		assertFalse(bank.loan(accountNumber, 0));
		assertFalse(bank.repay(accountNumber, 100));
		assertFalse(bank.repayOnAccount(accountNumber, 100));
	}
	
	@Test
	public void test4() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertTrue(bank.deposit(accountNumber, 100));
		assertFalse(bank.withdraw(accountNumber, 0));
		
//		assertFalse(bank.transfer(accountNumber, "111111", -1));
		
		assertTrue(bank.loan(accountNumber, 5000000));
		assertTrue(bank.repay(accountNumber, 5000000));
		assertFalse(bank.repayOnAccount(accountNumber, 0));
	}
	
	@Test
	public void test5() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertTrue(bank.deposit(accountNumber, 100));
		assertTrue(bank.withdraw(accountNumber, 100));
		
//		assertFalse(bank.transfer(accountNumber, "111111", 0));
		
		assertFalse(bank.loan(accountNumber, -1));
		assertFalse(bank.repay(accountNumber, 100));
		assertFalse(bank.repayOnAccount(accountNumber, 5000000));
	}
	
	@Test
	public void test6() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertFalse(bank.deposit(accountNumber, 0));
		assertFalse(bank.withdraw(accountNumber, 100));
		
		assertFalse(bank.transfer(accountNumber, accountNumber, 50000));
		
		assertTrue(bank.loan(accountNumber, 5000000));
		assertFalse(bank.repay(accountNumber, -1));
		assertFalse(bank.repayOnAccount(accountNumber, -1));
	}
	
	@Test
	public void test7() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertTrue(bank.deposit(accountNumber, 5000000));
		assertFalse(bank.withdraw(accountNumber, 0));
		
		assertFalse(bank.transfer(accountNumber, accountNumber, -1));
		
		assertFalse(bank.loan(accountNumber, 0));
		assertFalse(bank.repay(accountNumber, -1));
		assertFalse(bank.repayOnAccount(accountNumber, 500000));
	}
	
	@Test
	public void test8() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertFalse(bank.deposit(accountNumber, 0));
		assertFalse(bank.withdraw(accountNumber, 5000000));
		
		assertFalse(bank.transfer(accountNumber, accountNumber, -1));
		
		assertFalse(bank.loan(accountNumber, -1));
		assertFalse(bank.repay(accountNumber, 0));
		assertFalse(bank.repayOnAccount(accountNumber, 100));
	}
	
	@Test
	public void test9() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertTrue(bank.deposit(accountNumber, 100));
		assertFalse(bank.withdraw(accountNumber, 5000000));
		
//		assertFalse(bank.transfer(accountNumber, "111111", 100));
		
		assertFalse(bank.loan(accountNumber, 0));
		assertFalse(bank.repay(accountNumber, -1));
		assertFalse(bank.repayOnAccount(accountNumber, -1));
	}
	
	@Test
	public void test10() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertTrue(bank.deposit(accountNumber, 5000000));
		assertTrue(bank.withdraw(accountNumber, 5000000));
		
		assertFalse(bank.transfer(accountNumber, accountNumber, 0));
		
		assertTrue(bank.loan(accountNumber, 5000000));
		assertTrue(bank.repay(accountNumber, 100));
		assertFalse(bank.repayOnAccount(accountNumber, 0));
	}
	
	@Test
	public void test11() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertFalse(bank.deposit(accountNumber, 0));
		assertFalse(bank.withdraw(accountNumber, 5000000));
		
//		assertFalse(bank.transfer(accountNumber, "111111", 5000000));
		
		assertFalse(bank.loan(accountNumber, -1));
		assertFalse(bank.repay(accountNumber, 5000000));
		assertFalse(bank.repayOnAccount(accountNumber, 5000000));
	}
	
	@Test
	public void test12() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertTrue(bank.deposit(accountNumber, 5000000));
		assertTrue(bank.withdraw(accountNumber, 100));
		
//		assertFalse(bank.transfer(accountNumber, "111111", 5000000));
		
		assertTrue(bank.loan(accountNumber, 100));
		assertFalse(bank.repay(accountNumber, 5000000));
		assertTrue(bank.repayOnAccount(accountNumber, 100));
	}
	
	@Test
	public void test13() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertTrue(bank.deposit(accountNumber, 100));
		assertFalse(bank.withdraw(accountNumber, 0));
		
		assertTrue(bank.transfer(accountNumber, accountNumber, 100));
		
		assertTrue(bank.loan(accountNumber, 100));
		assertFalse(bank.repay(accountNumber, 0));
		assertFalse(bank.repayOnAccount(accountNumber, 0));
	}
	
	@Test
	public void test14() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertFalse(bank.deposit(accountNumber, -1));
		assertFalse(bank.withdraw(accountNumber, 0));
		
		assertFalse(bank.transfer(accountNumber, accountNumber, 5000000));
		
		assertFalse(bank.loan(accountNumber, -1));
		assertFalse(bank.repay(accountNumber, 100));
		assertFalse(bank.repayOnAccount(accountNumber, 5000000));
	}
	
	@Test
	public void test15() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertFalse(bank.deposit(accountNumber, 0));
		assertFalse(bank.withdraw(accountNumber, -1));
		
		assertFalse(bank.transfer(accountNumber, "111111", 0));
		
		assertFalse(bank.loan(accountNumber, -1));
		assertFalse(bank.repay(accountNumber, -1));
		assertFalse(bank.repayOnAccount(accountNumber, 0));
	}
	
	@Test
	public void test16() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertFalse(bank.deposit(accountNumber, -1));
		assertFalse(bank.withdraw(accountNumber, -1));
		
		assertFalse(bank.transfer(accountNumber, accountNumber, 100));
		
		assertTrue(bank.loan(accountNumber, 5000000));
		assertFalse(bank.repay(accountNumber, 0));
		assertFalse(bank.repayOnAccount(accountNumber, 5000000));
	}
	
	@Test
	public void test17() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertFalse(bank.deposit(accountNumber, 0));
		assertFalse(bank.withdraw(accountNumber, -1));
		
		assertFalse(bank.transfer(accountNumber, "111111", -1));
		
		assertTrue(bank.loan(accountNumber, 100));
		assertTrue(bank.repay(accountNumber, 100));
		assertFalse(bank.repayOnAccount(accountNumber, -1));
	}
	
	@Test
	public void test18() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertFalse(bank.deposit(accountNumber, -1));
		assertFalse(bank.withdraw(accountNumber, 0));
		
		assertFalse(bank.transfer(accountNumber, accountNumber, 0));
		
		assertTrue(bank.loan(accountNumber, 100));
		assertFalse(bank.repay(accountNumber, -1));
		assertFalse(bank.repayOnAccount(accountNumber, 100));
	}
	
	@Test
	public void test19() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertTrue(bank.deposit(accountNumber, 5000000));
		assertTrue(bank.withdraw(accountNumber, 100));
		
		assertFalse(bank.transfer(accountNumber, accountNumber, 0));
		
		assertFalse(bank.loan(accountNumber, 0));
		assertFalse(bank.repay(accountNumber, 0));
		assertFalse(bank.repayOnAccount(accountNumber, 0));
	}
	
	@Test
	public void test20() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertFalse(bank.deposit(accountNumber, -1));
		assertFalse(bank.withdraw(accountNumber, 100));
		
		assertFalse(bank.transfer(accountNumber, "111111", -1));
		
		assertTrue(bank.loan(accountNumber, 100));
		assertFalse(bank.repay(accountNumber, 5000000));
		assertFalse(bank.repayOnAccount(accountNumber, 5000000));
	}
	
	@Test
	public void test21() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertTrue(bank.deposit(accountNumber, 100));
		assertFalse(bank.withdraw(accountNumber, -1));
		
		assertFalse(bank.transfer(accountNumber, accountNumber, 5000000));
		
		assertFalse(bank.loan(accountNumber, -1));
		assertFalse(bank.repay(accountNumber, 0));
		assertFalse(bank.repayOnAccount(accountNumber, -1));
	}
	
	@Test
	public void test22() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertFalse(bank.deposit(accountNumber, 0));
		assertFalse(bank.withdraw(accountNumber, 0));
		
		assertFalse(bank.transfer(accountNumber, "111111", 5000000));
		
		assertFalse(bank.loan(accountNumber, 0));
		assertFalse(bank.repay(accountNumber, 5000000));
		assertFalse(bank.repayOnAccount(accountNumber, -1));
	}
	
	@Test
	public void test23() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertTrue(bank.deposit(accountNumber, 100));
		assertFalse(bank.withdraw(accountNumber, -1));
		
		assertFalse(bank.transfer(accountNumber, accountNumber, 0));
		
		assertTrue(bank.loan(accountNumber, 5000000));
		assertTrue(bank.repay(accountNumber, 100));
		assertTrue(bank.repayOnAccount(accountNumber, 100));
	}
	
	@Test
	public void test24() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertFalse(bank.deposit(accountNumber, -1));
		assertFalse(bank.withdraw(accountNumber, -1));
		
		assertFalse(bank.transfer(accountNumber, accountNumber, 100));
		
		assertFalse(bank.loan(accountNumber, 0));
		assertFalse(bank.repay(accountNumber, 5000000));
		assertFalse(bank.repayOnAccount(accountNumber, -1));
	}
	
	@Test
	public void test25() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertTrue(bank.deposit(accountNumber, 5000000));
		assertTrue(bank.withdraw(accountNumber, 100));
		
		assertTrue(bank.transfer(accountNumber, accountNumber, 100));
		
		assertFalse(bank.loan(accountNumber, -1));
		assertFalse(bank.repay(accountNumber, 0));
		assertFalse(bank.repayOnAccount(accountNumber, -1));
	}
}