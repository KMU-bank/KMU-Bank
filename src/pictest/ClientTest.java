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
	