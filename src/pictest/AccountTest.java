/*********************************************************
 * BanKMU - AccountTest class
 * Description: pairwise에 근거한 Junit test.
 * 하나의 테스트 케이스마다 하나의 테스트 함수가 존재한다.
 **********************************************************/
/**********************************************************
 * pairwise testing(pict)
 * 
 * 계좌번호	이름	state	입금액	출금액	이체계좌	이체금액	대출	대출상환	계좌상환
 * 111111	한글이름	s1	-1	0	111111	5000000	-1	100	0
 * 111111	한글이름	s1	100	-1	222222	100	0	-1	-1
 * 111111	한글이름	s1	5000000	100	222222	5000000	100	0	100
 * 111111	한글이름	s1	5000000	5000000	111111	0	-1	-1	0
 * 111111	한글이름	s1	0	-1	111111	0	0	100	100
 * 111111	한글이름	s1	0	0	222222	100	-1	0	100
 * 111111	한글이름	s1	0	100	111111	-1	100	100	-1
 * 111111	한글이름	s1	5000000	-1	222222	-1	0	0	0
 * 111111	한글이름	s1	100	5000000	111111	5000000	0	0	-1
 * 111111	한글이름	s1	-1	100	111111	100	0	-1	0
 * 111111	한글이름	s1	-1	5000000	222222	100	100	100	100
 * 111111	한글이름	s1	5000000	0	111111	100	-1	100	-1
 * 111111	한글이름	s1	0	-1	111111	5000000	100	-1	0
 * 111111	한글이름	s1	0	5000000	111111	-1	100	-1	100
 * 111111	한글이름	s1	-1	-1	111111	-1	-1	0	-1
 * 111111	한글이름	s1	100	100	222222	-1	-1	100	0
 * 111111	한글이름	s1	100	0	222222	-1	100	-1	100
 * 111111	한글이름	s1	-1	100	222222	0	100	0	-1
 * 111111	한글이름	s1	100	0	111111	0	0	0	-1
 *********************************************************/
package pictest;

import static org.junit.Assert.*;
import org.junit.Test;
import account.Account;

public class AccountTest {

	@Test
	public void test1() {
		Account account = new Account("111111", "한글이름");
		account.addStateList("s1");
		assertFalse(account.deposit(-1));
		assertFalse(account.withdraw(0));
		
		assertFalse(account.transfer("111111", 5000000));
		
		assertFalse(account.loan(-1));
		assertFalse(account.repay(100));
		assertFalse(account.repayOnAccount(0));
		
		assertEquals("111111", account.getAccountNumber());
		assertEquals("한글이름", account.getName());
		assertEquals("s1", account.getStateList().get(0));
		assertEquals(0, account.getBalance());
		assertEquals(0, account.getDebt());
	}
	
	@Test
	public void test2() {
		Account account = new Account("111111", "한글이름");
		account.addStateList("s1");
		assertTrue(account.deposit(100));
		assertFalse(account.withdraw(-1));
		
		assertTrue(account.transfer("222222", 100));
		
		assertFalse(account.loan(0));
		assertFalse(account.repay(-1));
		assertFalse(account.repayOnAccount(-1));
		
		assertEquals(0, account.getBalance());
		assertEquals(0, account.getDebt());
	}
	
	@Test
	public void test3() {
		Account account = new Account("111111", "한글이름");
		account.addStateList("s1");
		assertTrue(account.deposit(5000000));
		assertTrue(account.withdraw(100));
		
		assertFalse(account.transfer("222222", 5000000));
		
		assertTrue(account.loan(100));
		assertFalse(account.repay(0));
		assertTrue(account.repayOnAccount(100));
		
		assertEquals(4999800, account.getBalance());
		assertEquals(0, account.getDebt());
	}
	
	@Test
	public void test4() {
		Account account = new Account("111111", "한글이름");
		account.addStateList("s1");
		assertTrue(account.deposit(5000000));
		assertTrue(account.withdraw(5000000));
		
		assertFalse(account.transfer("111111", 0));
		
		assertFalse(account.loan(-1));
		assertFalse(account.repay(-1));
		assertFalse(account.repayOnAccount(0));
		
		assertEquals(0, account.getBalance());
		assertEquals(0, account.getDebt());
	}
	
	@Test
	public void test5() {
		Account account = new Account("111111", "한글이름");
		account.addStateList("s1");
		assertFalse(account.deposit(0));
		assertFalse(account.withdraw(-1));
		
		assertFalse(account.transfer("111111", 0));
		
		assertFalse(account.loan(0));
		assertFalse(account.repay(100));
		assertFalse(account.repayOnAccount(100));
		
		assertEquals(0, account.getBalance());
		assertEquals(0, account.getDebt());
	}
	
	@Test
	public void test6() {
		Account account = new Account("111111", "한글이름");
		account.addStateList("s1");
		assertFalse(account.deposit(0));
		assertFalse(account.withdraw(0));
		
		assertFalse(account.transfer("222222", 100));
		
		assertFalse(account.loan(-1));
		assertFalse(account.repay(0));
		assertFalse(account.repayOnAccount(100));
		
		assertEquals(0, account.getBalance());
		assertEquals(0, account.getDebt());
	}
	
	@Test
	public void test7() {
		Account account = new Account("111111", "한글이름");
		account.addStateList("s1");
		assertFalse(account.deposit(0));
		assertFalse(account.withdraw(100));
		
		assertFalse(account.transfer("111111", -1));
		
		assertTrue(account.loan(100));
		assertTrue(account.repay(100));
		assertFalse(account.repayOnAccount(-1));
		
		assertEquals(0, account.getBalance());
		assertEquals(0, account.getDebt());
	}
	
	@Test
	public void test8() {
		Account account = new Account("111111", "한글이름");
		account.addStateList("s1");
		assertTrue(account.deposit(5000000));
		assertFalse(account.withdraw(-1));
		
		assertFalse(account.transfer("222222", -1));
		
		assertFalse(account.loan(0));
		assertFalse(account.repay(0));
		assertFalse(account.repayOnAccount(0));
		
		assertEquals(5000000, account.getBalance());
		assertEquals(0, account.getDebt());
	}
	
	@Test
	public void test9() {
		Account account = new Account("111111", "한글이름");
		account.addStateList("s1");
		assertTrue(account.deposit(100));
		assertFalse(account.withdraw(5000000));
		
		assertFalse(account.transfer("111111", 5000000));
		
		assertFalse(account.loan(0));
		assertFalse(account.repay(0));
		assertFalse(account.repayOnAccount(-1));
		
		assertEquals(100, account.getBalance());
		assertEquals(0, account.getDebt());
	}
	
	@Test
	public void test10() {
		Account account = new Account("111111", "한글이름");
		account.addStateList("s1");
		assertFalse(account.deposit(-1));
		assertFalse(account.withdraw(100));
		
		assertFalse(account.transfer("111111", 100));
		
		assertTrue(account.loan(100));
		assertFalse(account.repay(0));
		assertFalse(account.repayOnAccount(-1));
		
		assertEquals(0, account.getBalance());
		assertEquals(100, account.getDebt());
	}
	
	@Test
	public void test11() {
		Account account = new Account("111111", "한글이름");
		account.addStateList("s1");
		assertFalse(account.deposit(-1));
		assertFalse(account.withdraw(5000000));
		
		assertFalse(account.transfer("222222", 100));
		
		assertTrue(account.loan(100));
		assertTrue(account.repay(100));
		assertFalse(account.repayOnAccount(100));
		
		assertEquals(0, account.getBalance());
		assertEquals(0, account.getDebt());
	}
	
	@Test
	public void test12() {
		Account account = new Account("111111", "한글이름");
		account.addStateList("s1");
		assertTrue(account.deposit(5000000));
		assertFalse(account.withdraw(0));
		
		assertTrue(account.transfer("111111", 100));
		
		assertFalse(account.loan(-1));
		assertFalse(account.repay(100));
		assertFalse(account.repayOnAccount(-1));
		
		assertEquals(4999900, account.getBalance());
		assertEquals(0, account.getDebt());
	}
	
	@Test
	public void test13() {
		Account account = new Account("111111", "한글이름");
		account.addStateList("s1");
		assertFalse(account.deposit(0));
		assertFalse(account.withdraw(-1));
		
		assertFalse(account.transfer("111111", 5000000));
		
		assertTrue(account.loan(100));
		assertFalse(account.repay(-1));
		assertFalse(account.repayOnAccount(0));
		
		assertEquals(0, account.getBalance());
		assertEquals(100, account.getDebt());
	}
	
	@Test
	public void test14() {
		Account account = new Account("111111", "한글이름");
		account.addStateList("s1");
		assertFalse(account.deposit(0));
		assertFalse(account.withdraw(5000000));
		
		assertFalse(account.transfer("111111", -1));
		
		assertTrue(account.loan(100));
		assertFalse(account.repay(-1));
		assertFalse(account.repayOnAccount(100));
		
		assertEquals(0, account.getBalance());
		assertEquals(100, account.getDebt());
	}
	
	@Test
	public void test15() {
		Account account = new Account("111111", "한글이름");
		account.addStateList("s1");
		assertFalse(account.deposit(-1));
		assertFalse(account.withdraw(-1));
		
		assertFalse(account.transfer("111111", -1));
		
		assertFalse(account.loan(-1));
		assertFalse(account.repay(0));
		assertFalse(account.repayOnAccount(-1));
		
		assertEquals(0, account.getBalance());
		assertEquals(0, account.getDebt());
	}
	
	@Test
	public void test16() {
		Account account = new Account("111111", "한글이름");
		account.addStateList("s1");
		assertTrue(account.deposit(100));
		assertTrue(account.withdraw(100));
		
		assertFalse(account.transfer("222222", -1));
		
		assertFalse(account.loan(-1));
		assertFalse(account.repay(-1));
		assertFalse(account.repayOnAccount(100));
		
		assertEquals(0, account.getBalance());
		assertEquals(0, account.getDebt());
	}
	
	@Test
	public void test17() {
		Account account = new Account("111111", "한글이름");
		account.addStateList("s1");
		assertTrue(account.deposit(100));
		assertFalse(account.withdraw(0));
		
		assertFalse(account.transfer("222222", -1));
		
		assertTrue(account.loan(100));
		assertFalse(account.repay(-1));
		assertTrue(account.repayOnAccount(100));
		
		assertEquals(0, account.getBalance());
		assertEquals(0, account.getDebt());
	}
	
	@Test
	public void test18() {
		Account account = new Account("111111", "한글이름");
		account.addStateList("s1");
		assertFalse(account.deposit(-1));
		assertFalse(account.withdraw(100));
		
		assertFalse(account.transfer("222222", 0));
		
		assertTrue(account.loan(100));
		assertFalse(account.repay(0));
		assertFalse(account.repayOnAccount(-1));
		
		assertEquals(0, account.getBalance());
		assertEquals(100, account.getDebt());
	}
	
	@Test
	public void test19() {
		Account account = new Account("111111", "한글이름");
		account.addStateList("s1");
		assertTrue(account.deposit(100));
		assertFalse(account.withdraw(0));
		
		assertFalse(account.transfer("111111", 0));
		
		assertFalse(account.loan(0));
		assertFalse(account.repay(0));
		assertFalse(account.repayOnAccount(-1));
		
		assertEquals(100, account.getBalance());
		assertEquals(0, account.getDebt());
	}
}