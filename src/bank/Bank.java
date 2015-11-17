package bank;

import java.util.HashMap;

public class Bank {
	
	private static Bank bank;
	private HashMap<String, Account> account = new HashMap<String, Account>();
	private int balance;
	final private double interest = 0.1;
	
	public static Bank getinstance(){
		if(bank == null)
			bank = new Bank();
		return bank;
	}
	
	public boolean openAccount(String accountNumber, String name){//random!!!!!!!
		if(account.get(accountNumber) != null)
			return false;
		account.put(accountNumber, new Account(accountNumber, name));
		return true;
	}
	
	public boolean closeAccount(String accountNumber){
		if(account.get(accountNumber) == null)
			return false;
		account.remove(accountNumber);
		return true;
	}
	
	public void deposit(String accountNumber, int money){
		account.get(accountNumber).deposit(money);
	}
	
	public void withdraw(String accountNumber, int money){
		account.get(accountNumber).withdraw(money);
	}
	
}
