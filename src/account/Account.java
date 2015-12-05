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
	
	//		getter & setter...
	public String getName(){
		return name;
	}
	
	public int getBalance(){
		return balance;
	}
	
	public int getDebt(){
		return debt;
	}
	
	public LinkedList<String> getStateList(){
		return stateList;
	}
	
	public void addStateList(String state){
		stateList.add(state);
	}
	
	//		basic function...
	public boolean deposit(int money){
		if(money <= 0)
			return false;
		balance += money;
		return true;
	}
	
	public boolean withdraw(int money){
		if(balance < money)
			return false;
		balance -= money;
		return true;
	}
	
	public boolean transfer(String accountNumber, int money){
		return withdraw(money);
	}
	
	//		loan function
	public void loan(int money){
		debt += money;
	}      
	
	public boolean repay(int money){
		if(debt < money)
			return false;
		debt -= money;
		return true;
	}
	
	public boolean repayOnAccount(int money){
		boolean isDone = withdraw(money);
		if(isDone)
			debt -= money;
		return isDone;
	}
	
	//		card function...
	public void lostReport(){
		stolen = true;
	}
	
	public void findReport(){
		stolen = false;
	}
	
	public boolean makeCard(){
		if(haveCard)
			return false;	//	if already have card
		
		haveCard = true;
		return true;
	}
	
	public boolean useCard(String state, int money){
		if(!haveCard || stolen)
			return false;
		
		balance -= money;
		return true;
	}
}
