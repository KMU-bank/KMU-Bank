package bank;

import java.util.LinkedList;

public class Account {
	private String accountNumber;
	private String name;
	private int balance;
	private boolean haveCard = false;
	LinkedList<String> stateList = new LinkedList<String>();
	private boolean stolen = false;
	private int debt = 0;

	public Account(String accountNumber, String name){
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = 0;
	}
	
	public Account(String accountNumber, String name, int balance){
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = balance;
	}
	
	public String getName(String name){
		return name;
	}
	
	public int getBalance(){
		return balance;
	}
	
	public void deposit(int money){
		balance += money;
	}
	
	public boolean withdraw(int money){
		if(balance < money)
			return false;
		balance -= money;
		return true;
	}
	
	public void loan(int money){
		debt += money;
	}
	
	public boolean useCard(String accountNumber, String state, int money){
		if(haveCard == false)
			return false;
		balance -= money;
		stateList.add(state);
		return true;
	}
}
