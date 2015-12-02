package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import bank.KBBank;
import client.Client;

public class testClient {

	@Test
	public void testClient1(){
		Client cl = new Client("hanul");
		cl.selectBank(new KBBank());
		cl.openAccount();
		
		assertEquals(2000000, cl.getAsset());
		
		cl.deposit(50000);
		assertEquals(50000, cl.getBalance());
		assertEquals(1950000, cl.getAsset());
		
		cl.withdraw(10000);
		assertEquals(40000, cl.getBalance());
		assertEquals(1960000, cl.getAsset());
		
		cl.loan(20000);
		assertEquals(20000, cl.getDebt());
		assertEquals(1980000, cl.getAsset());
		
		cl.repay(10000);
		assertEquals(10000, cl.getDebt());
		assertEquals(1970000, cl.getAsset());
		
		cl.repayOnAccount(10000);
		assertEquals(0, cl.getDebt());
		assertEquals(30000, cl.getBalance());
		
		assertEquals("hanul", cl.getName());
	}
	
}
