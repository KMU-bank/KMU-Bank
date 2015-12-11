/*********************************************************
 * BanKMU - ClientTest class
 * Description: pairwise에 근거한 Junit test.
 * 하나의 테스트 케이스마다 하나의 테스트 함수가 존재한다.
 **********************************************************/
/**********************************************************
 * pairwise testing(pict)
 * 
 * 이름	은행	입금	출금	대출	상환	계좌상환	이체계좌	이체금액
 * 한글	KB	5000000	-1	0	100	100	내계좌	-1
 * 한글	KB	-1	5000000	5000000	100	0	111111	0
 * 한글	KB	-1	100	100	0	5000000	내계좌	100
 * 한글	KB	100	0	-1	5000000	5000000	111111	0
 * 한글	KB	0	0	100	-1	100	111111	5000000
 * 한글	KB	0	5000000	5000000	5000000	-1	내계좌	-1
 * 한글	KB	5000000	-1	100	0	-1	111111	0
 * 한글	KB	100	100	100	100	0	내계좌	5000000
 * 한글	KB	-1	100	0	-1	-1	111111	0
 * 한글	KB	100	-1	5000000	-1	0	111111	100
 * 한글	KB	5000000	-1	-1	-1	5000000	내계좌	5000000
 * 한글	KB	0	-1	0	5000000	0	111111	100
 * 한글	KB	-1	-1	-1	0	100	111111	-1
 * 한글	KB	100	0	0	0	5000000	내계좌	-1
 * 한글	KB	100	5000000	-1	100	-1	내계좌	100
 * 한글	KB	5000000	5000000	100	5000000	100	내계좌	5000000
 * 한글	KB	0	5000000	5000000	0	5000000	내계좌	500000
 * 한글	KB	-1	5000000	0	-1	-1	내계좌	5000000
 * 한글	KB	5000000	0	5000000	100	0	내계좌	100
 * 한글	KB	0	100	-1	-1	0	111111	-1
 * 한글	KB	0	100	5000000	100	5000000	내계좌	0
 * 한글	KB	100	100	5000000	5000000	100	111111	100
 * 한글	KB	5000000	100	100	100	100	111111	0
 * 한글	KB	-1	0	100	5000000	-1	111111	-1
 * 한글	KB	5000000	100	0	0	0	111111	0
 *********************************************************/
package pictest;

import static org.junit.Assert.*;

import org.junit.Test;

import bank.KBBank;
import client.Client;

public class ClientTest {

	@Test
	public void test1() {
		Client client = new Client("한글");
		String accountNumber = client.openAccount(new KBBank());
		
		assertEquals(1, client.deposit(new KBBank(), 5000000));
		assertEquals(2, client.withdraw(new KBBank(), -1));
		
		assertFalse(client.loan(new KBBank(), 0));
		assertFalse(client.repay(new KBBank(), 100));
		assertFalse(client.repayOnAccount(new KBBank(), 100));
		
		assertFalse(client.transfer(new KBBank(), accountNumber, -1));
	}
	
	@Test
	public void test2() {
		Client client = new Client("한글");
		client.openAccount(new KBBank());
		
		assertEquals(2, client.deposit(new KBBank(), -1));
		assertEquals(1, client.withdraw(new KBBank(), 5000000));
		
		assertTrue(client.loan(new KBBank(), 5000000));
		assertTrue(client.repay(new KBBank(), 100));
		assertFalse(client.repayOnAccount(new KBBank(), 100));
		
		assertFalse(client.transfer(new KBBank(), "111111", -1));
	}
	
	@Test
	public void test3() {
		Client client = new Client("한글");
		String accountNumber = client.openAccount(new KBBank());
		
		assertEquals(2, client.deposit(new KBBank(), -1));
		assertEquals(1, client.withdraw(new KBBank(), 100));
		
		assertTrue(client.loan(new KBBank(), 100));
		assertFalse(client.repay(new KBBank(), 5000000));
		assertFalse(client.repayOnAccount(new KBBank(), 5000000));
		
		assertFalse(client.transfer(new KBBank(), accountNumber, 100));
	}
	
	@Test
	public void test4() {
		Client client = new Client("한글");
		client.openAccount(new KBBank());
		
		assertEquals(0, client.deposit(new KBBank(), 100));
		assertEquals(2, client.withdraw(new KBBank(), 0));
		
		assertFalse(client.loan(new KBBank(), -1));
		assertFalse(client.repay(new KBBank(), 5000000));
		assertFalse(client.repayOnAccount(new KBBank(), 5000000));
		
		assertFalse(client.transfer(new KBBank(), "111111", 0));
	}
	
	@Test
	public void test5() {
		Client client = new Client("한글");
		client.openAccount(new KBBank());
		
		assertEquals(2, client.deposit(new KBBank(), 0));
		assertEquals(2, client.withdraw(new KBBank(), 0));
		
		assertTrue(client.loan(new KBBank(), 100));
		assertFalse(client.repay(new KBBank(), -1));
		assertFalse(client.repayOnAccount(new KBBank(), 100));
		
		assertFalse(client.transfer(new KBBank(), "111111", 5000000));
	}
	
	@Test
	public void test6() {
		Client client = new Client("한글");
		String accountNumber = client.openAccount(new KBBank());
		
		assertEquals(2, client.deposit(new KBBank(), 0));
		assertEquals(1, client.withdraw(new KBBank(), 5000000));
		
		assertTrue(client.loan(new KBBank(), 5000000));
		assertTrue(client.repay(new KBBank(), 5000000));
		assertFalse(client.repayOnAccount(new KBBank(), -1));
		
		assertFalse(client.transfer(new KBBank(), accountNumber, -1));
	}
	
	@Test
	public void test7() {
		Client client = new Client("한글");
		client.openAccount(new KBBank());
		
		assertEquals(1, client.deposit(new KBBank(), 5000000));
		assertEquals(2, client.withdraw(new KBBank(), -1));
		
		assertTrue(client.loan(new KBBank(), 100));
		assertFalse(client.repay(new KBBank(), 0));
		assertFalse(client.repayOnAccount(new KBBank(), -1));
		
		assertFalse(client.transfer(new KBBank(), "111111", 0));
	}
	
	@Test
	public void test8() {
		Client client = new Client("한글");
		client.openAccount(new KBBank());
		
		assertEquals(0, client.deposit(new KBBank(), 100));
		assertEquals(0, client.withdraw(new KBBank(), 100));
		
		assertTrue(client.loan(new KBBank(), 100));
		assertTrue(client.repay(new KBBank(), 100));
		assertFalse(client.repayOnAccount(new KBBank(), 0));
		
		assertFalse(client.transfer(new KBBank(), "111111", -1));
	}
	
	@Test
	public void test9() {
		Client client = new Client("한글");
		client.openAccount(new KBBank());
		
		assertEquals(2, client.deposit(new KBBank(), -1));
		assertEquals(1, client.withdraw(new KBBank(), 100));
		
		assertFalse(client.loan(new KBBank(), 0));
		assertFalse(client.repay(new KBBank(), -1));
		assertFalse(client.repayOnAccount(new KBBank(), -1));
		
		assertFalse(client.transfer(new KBBank(), "111111", 0));
	}
	
	@Test
	public void test10() {
		Client client = new Client("한글");
		client.openAccount(new KBBank());
		
		assertEquals(0, client.deposit(new KBBank(), 100));
		assertEquals(2, client.withdraw(new KBBank(), -1));
		
		assertTrue(client.loan(new KBBank(), 5000000));
		assertFalse(client.repay(new KBBank(), -1));
		assertFalse(client.repayOnAccount(new KBBank(), 0));
		
		assertFalse(client.transfer(new KBBank(), "111111", 100));
	}
	
	@Test
	public void test11() {
		Client client = new Client("한글");
		String accountNumber = client.openAccount(new KBBank());
		
		assertEquals(1, client.deposit(new KBBank(), 5000000));
		assertEquals(2, client.withdraw(new KBBank(), -1));
		
		assertFalse(client.loan(new KBBank(), -1));
		assertFalse(client.repay(new KBBank(), -1));
		assertFalse(client.repayOnAccount(new KBBank(), 5000000));
		
		assertFalse(client.transfer(new KBBank(), accountNumber, 5000000));
	}
	
	@Test
	public void test12() {
		Client client = new Client("한글");
		client.openAccount(new KBBank());
		
		assertEquals(2, client.deposit(new KBBank(), 0));
		assertEquals(2, client.withdraw(new KBBank(), -1));
		
		assertFalse(client.loan(new KBBank(), 0));
		assertFalse(client.repay(new KBBank(), 5000000));
		assertFalse(client.repayOnAccount(new KBBank(), 0));
		
		assertFalse(client.transfer(new KBBank(), "111111", 100));
	}
	
	@Test
	public void test13() {
		Client client = new Client("한글");
		client.openAccount(new KBBank());
		
		assertEquals(2, client.deposit(new KBBank(), -1));
		assertEquals(2, client.withdraw(new KBBank(), -1));
		
		assertFalse(client.loan(new KBBank(), -1));
		assertFalse(client.repay(new KBBank(), 0));
		assertFalse(client.repayOnAccount(new KBBank(), 100));
		
		assertFalse(client.transfer(new KBBank(), "111111", -1));
	}
	
	@Test
	public void test14() {
		Client client = new Client("한글");
		client.openAccount(new KBBank());
		
		assertEquals(0, client.deposit(new KBBank(), 100));
		assertEquals(2, client.withdraw(new KBBank(), 0));
		
		assertTrue(client.loan(new KBBank(), 5000000));
		assertTrue(client.repay(new KBBank(), 100));
		assertTrue(client.repayOnAccount(new KBBank(), 100));
		
		assertFalse(client.transfer(new KBBank(), "111111", -1));
	}
	
	@Test
	public void test15() {
		Client client = new Client("한글");
		String accountNumber = client.openAccount(new KBBank());
		
		assertEquals(0, client.deposit(new KBBank(), 100));
		assertEquals(1, client.withdraw(new KBBank(), 5000000));
		
		assertFalse(client.loan(new KBBank(), -1));
		assertFalse(client.repay(new KBBank(), 100));
		assertFalse(client.repayOnAccount(new KBBank(), -1));
		
		assertFalse(client.transfer(new KBBank(), accountNumber, -1));
	}
	
	@Test
	public void test16() {
		Client client = new Client("한글");
		String accountNumber = client.openAccount(new KBBank());
		
		assertEquals(1, client.deposit(new KBBank(), 5000000));
		assertEquals(1, client.withdraw(new KBBank(), 5000000));
		
		assertTrue(client.loan(new KBBank(), 100));
		assertFalse(client.repay(new KBBank(), 5000000));
		assertFalse(client.repayOnAccount(new KBBank(), 100));
		
		assertFalse(client.transfer(new KBBank(), accountNumber, -1));
	}
	
	@Test
	public void test17() {
		Client client = new Client("한글");
		String accountNumber = client.openAccount(new KBBank());
		
		assertEquals(2, client.deposit(new KBBank(), 0));
		assertEquals(1, client.withdraw(new KBBank(), 5000000));
		
		assertTrue(client.loan(new KBBank(), 5000000));
		assertFalse(client.repay(new KBBank(), 0));
		assertFalse(client.repayOnAccount(new KBBank(), 5000000));
		
		assertFalse(client.transfer(new KBBank(), accountNumber, 5000000));
	}
	
	@Test
	public void test18() {
		Client client = new Client("한글");
		String accountNumber = client.openAccount(new KBBank());
		
		assertEquals(2, client.deposit(new KBBank(), -1));
		assertEquals(1, client.withdraw(new KBBank(), 5000000));
		
		assertFalse(client.loan(new KBBank(), 0));
		assertFalse(client.repay(new KBBank(), -1));
		assertFalse(client.repayOnAccount(new KBBank(), -1));
		
		assertFalse(client.transfer(new KBBank(), accountNumber, 5000000));
	}
	
	@Test
	public void test19() {
		Client client = new Client("한글");
		String accountNumber = client.openAccount(new KBBank());
		
		assertEquals(1, client.deposit(new KBBank(), 5000000));
		assertEquals(2, client.withdraw(new KBBank(), 0));
		
		assertTrue(client.loan(new KBBank(), 5000000));
		assertFalse(client.repay(new KBBank(), 0));
		assertFalse(client.repayOnAccount(new KBBank(), 0));
		
		assertFalse(client.transfer(new KBBank(), accountNumber, 100));
	}
	
	@Test
	public void test20() {
		Client client = new Client("한글");
		client.openAccount(new KBBank());
		
		assertEquals(2, client.deposit(new KBBank(), 0));
		assertEquals(1, client.withdraw(new KBBank(), 100));
		
		assertFalse(client.loan(new KBBank(), -1));
		assertFalse(client.repay(new KBBank(), -1));
		assertFalse(client.repayOnAccount(new KBBank(), 0));
		
		assertFalse(client.transfer(new KBBank(), "111111", -1));
	}
	
	@Test
	public void test21() {
		Client client = new Client("한글");
		String accountNumber = client.openAccount(new KBBank());
		
		assertEquals(2, client.deposit(new KBBank(), 0));
		assertEquals(1, client.withdraw(new KBBank(), 100));
		
		assertTrue(client.loan(new KBBank(), 5000000));
		assertTrue(client.repay(new KBBank(), 100));
		assertFalse(client.repayOnAccount(new KBBank(), 5000000));
		
		assertFalse(client.transfer(new KBBank(), accountNumber, 0));
	}
	
	@Test
	public void test22() {
		Client client = new Client("한글");
		client.openAccount(new KBBank());
		
		assertEquals(0, client.deposit(new KBBank(), 100));
		assertEquals(0, client.withdraw(new KBBank(), 100));
		
		assertTrue(client.loan(new KBBank(), 5000000));
		assertTrue(client.repay(new KBBank(), 5000000));
		assertFalse(client.repayOnAccount(new KBBank(), 100));
		
		assertFalse(client.transfer(new KBBank(), "111111", 111));
	}
	
	@Test
	public void test23() {
		Client client = new Client("한글");
		client.openAccount(new KBBank());
		
		assertEquals(1, client.deposit(new KBBank(), 5000000));
		assertEquals(1, client.withdraw(new KBBank(), 100));
		
		assertTrue(client.loan(new KBBank(), 100));
		assertTrue(client.repay(new KBBank(), 100));
		assertFalse(client.repayOnAccount(new KBBank(), 100));
		
		assertFalse(client.transfer(new KBBank(), "111111", 0));
	}
	
	@Test
	public void test24() {
		Client client = new Client("한글");
		client.openAccount(new KBBank());
		
		assertEquals(2, client.deposit(new KBBank(), -1));
		assertEquals(2, client.withdraw(new KBBank(), 0));
		
		assertTrue(client.loan(new KBBank(), 100));
		assertFalse(client.repay(new KBBank(), 5000000));
		assertFalse(client.repayOnAccount(new KBBank(), -1));
		
		assertFalse(client.transfer(new KBBank(), "111111", -1));
	}
	
	@Test
	public void test25() {
		Client client = new Client("한글");
		client.openAccount(new KBBank());
		
		assertEquals(1, client.deposit(new KBBank(), 5000000));
		assertEquals(1, client.withdraw(new KBBank(), 100));
		
		assertFalse(client.loan(new KBBank(), 0));
		assertFalse(client.repay(new KBBank(), 0));
		assertFalse(client.repayOnAccount(new KBBank(), 0));
		
		assertFalse(client.transfer(new KBBank(), "111111", 0));
	}
}
	