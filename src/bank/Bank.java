package bank;

import java.util.HashMap;
import java.util.Random;

public class Bank {
	
	private static Bank bank;
	private HashMap<String, Account> account = new HashMap<String, Account>();
	
	final private String INIT = "7";	//initial account number
	private double interest = 0.1;
	
	public static Bank getinstance(){
		if(bank == null)
			bank = new Bank();
		return bank;
	}
	
	public String openAccount(String name){
		String accountNumber = INIT + new Random().nextInt(99999);
		account.put(accountNumber, new Account(accountNumber, name));
		return accountNumber;
	}
	
	public int closeAccount(String accountNumber){
		if(account.get(accountNumber) != null){
			int restBalance = account.get(accountNumber).getBalance();
			account.remove(accountNumber);
			return restBalance;
		}
		return 0; // if no account number
	}
	
	public void deposit(String accountNumber, int money){
		account.get(accountNumber).deposit(money);
	}
	
	public boolean withdraw(String accountNumber, int money){
		return account.get(accountNumber).withdraw(money);
	}//false -> stay client money & print out on console
	
	public void transfer(String from, String to, int money){
		account.get(from).withdraw(money);
		account.get(to).deposit(money);
	}
	
	public void loan(String accountNumber, int money){
		account.get(accountNumber).loan(money);
	}
}