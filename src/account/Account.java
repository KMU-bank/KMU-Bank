package account;

import java.util.LinkedList;

public class Account {
	private String accountNumber;
	private String name;
	private int balance;
	private boolean haveCard = false;
	private LinkedList<String> stateList = new LinkedList<String>();
	private int debt = 0;
	private boolean stolen = false;

	public Account(String accountNumber, String name){
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = 0;
	}
	
	public String getName(String name){
		return name;
	}
	
	public int getBalance(){
		return balance;
	}
	
	public LinkedList<String> getStateList(){
		return stateList;
	}
	
	public void deposit(int money){
		balance += money;
		stateList.add("deposit : " +  money + " balance : " + balance);
	}
	
	public boolean withdraw(int money){
		if(balance < money)
			return false;
		balance -= money;
		stateList.add("withdraw : " +  money + " balance : " + balance);
		return true;
	}
	
	public void transfer(String accountNumber, int money){
		withdraw(money);
		stateList.add("account Number : " + accountNumber + "  " + money + " balance : " + balance);
	}
	
	public void loan(int money){
		debt += money;
	}
	
	public void repay(int money){
		if(withdraw(money))
			debt -= money;
	}
	
	public void repayOnAccount(int money){
		debt -= money;
	}
	
	public boolean makeCard(){
		if(haveCard)
			return false;	//	if already have card
		
		haveCard = true;
		return true;
	}
	
	public void lostReport(){
		stolen = true;
	}
	
	public boolean useCard(String accountNumber, String state, int money){
		if(!haveCard || !stolen)
			return false;
		
		balance -= money;
		stateList.add(state + " : " + money);
		return true;
	}
}
