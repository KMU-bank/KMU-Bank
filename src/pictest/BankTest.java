/*********************************************************
 * BanKMU - BankTest class
 * Description: pairwise에 근거한 Junit test.
 * 하나의 테스트 케이스마다 하나의 테스트 함수가 존재한다.
 **********************************************************/
/**********************************************************
 * pairwise testing(pict)
 * 
 * 이름	입금	출금	이체계좌	이체금액	대출	상환	계좌상환
 * 한글	5000000	-1	내계좌	0	100	5000000	-1
 * 한글	-1	5000000	111111	5000000	100	0	0
 * 한글	-1	100	내계좌	100	0	100	100
 * 한글	100	0	111111	-1	5000000	5000000	0
 * 한글	100	100	111111	0	-1	100	5000000
 * 한글	0	100	내계좌	5000000	5000000	-1	-1
 * 한글	5000000	0	내계좌	-1	0	-1	5000000
 * 한글	0	5000000	내계좌	-1	-1	0	100
 * 한글	100	5000000	111111	100	0	-1	-1
 * 한글	5000000	5000000	내계좌	0	5000000	100	0
 * 한글	0	5000000	111111	100	-1	5000000	5000000
 * 한글	5000000	100	111111	5000000	100	5000000	100
 * 한글	100	0	내계좌	100	100	0	0
 * 한글	-1	0	내계좌	5000000	-1	100	5000000
 * 한글	0	-1	111111	0	-1	-1	0
 * 한글	-1	-1	내계좌	100	5000000	0	5000000
 * 한글	0	-1	111111	-1	100	100	-1
 * 한글	-1	0	내계좌	0	100	-1	100
 * 한글	5000000	100	내계좌	0	0	0	0
 * 한글	-1	100	111111	-1	100	5000000	5000000
 * 한글	100	-1	내계좌	5000000	-1	0	-1
 * 한글	0	0	111111	5000000	0	5000000	-1
 * 한글	100	-1	내계좌	0	5000000	100	100
 * 한글	-1	-1	내계좌	100	0	5000000	-1
 * 한글	5000000	100	내계좌	100	-1	0	-1
 *********************************************************/
package pictest;

import static org.junit.Assert.*;

import org.junit.Test;

import bank.Bank;
import bank.KBBank;

public class BankTest {

	@Test
	public void test0() {
		Bank bank = new KBBank();
		String accountNumber = bank.openAccount("한글");
		
		assertTrue(bank.deposit(accountNumber, 5000000));
		assertTrue(bank.loan(accountNumber, 1000000));
		
		assertEquals(5000000, bank.getBalance(accountNumber));
		assertEquals(1000000, bank.getDebt(accountNumber));
		
		bank.timeLeapYear();
		assertEquals(5000000 + 1050000, bank.getBalance(accountNumber));
		assertEquals(1000000 + 410000, bank.getDebt(accountNumber));
		assertEquals(4640000, bank.closeAccount(accountNumber));
	}
	
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