package bank;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import account.*;

public class Bank {
	
	private HashMap<String, Account> account = Accounts.getInstance().accountList;
	public String INIT = "";	//initial account number
	public double positiveInterest = 0.0;
	public double negativeInterest = 0.0;
	
	protected Bank(String INIT, double positiveInterest, double negativeInterest){
		this.INIT = INIT;
		this.positiveInterest = positiveInterest;
		this.negativeInterest = negativeInterest;
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
	
	public int getBalance(String accountNumber){
		return account.get(accountNumber).getBalance();
	}
	
	public int getDebt(String accountNumber){
		return account.get(accountNumber).getDebt();
	}
	
	public void repayOnAccount(String accountNumber, int money){
		account.get(accountNumber).repayOnAccount(money);
	}
	
	public void repay(String accountNumber, int money){
		account.get(accountNumber).repay(money);
	}
	
	public LinkedList<String> getStateList(String accountNumber){
		return account.get(accountNumber).getStateList();
	}
	
	public void timeLeapYear(){
		for(int i=0; i<account.size(); i++){
			account.get(i).deposit((int)(account.get(i).getBalance() * positiveInterest));
			account.get(i).loan((int)(account.get(i).getDebt() * negativeInterest));
		}
	}
}
